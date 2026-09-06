package edu.curtin.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Filedata
{ 
    private static final Logger LOG = Logger.getLogger(Filedata.class.getName());
    public List<String> read (String name) throws IOException
    {
        return Files.readAllLines(Path.of(name));
    }

    public String[] split (String line)
    {
        String [] x = line.split(";", -1);

        for (int i = 0; i< x.length; i++)
        {
            x[i] = x[i].trim();
        }
        return x;
        
    }



    public Wbs load (String name) throws IOException, InvalidWbsException
    {
        List<String> lines = read (name);
        LOG.info(() -> "Loading WBS file: " + name);
        Wbs wbs = new Wbs ();

        for (String line: lines)
        {
            String [] x = split (line);

            if (x.length < 3 || x.length > 4)

            {
                throw new InvalidWbsException("Invalid WBS line " + line);
            }
            if (x[1].isEmpty() || x[2].isEmpty())
            {
                throw new InvalidWbsException("Missing ID or description " + line);

            }

            Task task;

            if(isParent(lines,x[1]))
            {
                if(x.length == 4)
                    {
                        throw new InvalidWbsException("Group has effort: " + x[1]);
                    }
                
                task = new Group(x[1], x[2]);
            }
            else if (x.length ==4)
            {
                try
                {
                    int effort = Integer.parseInt(x[3]);

                    if (effort <= 0)
                    {
                        throw new InvalidWbsException("Invalid effort: " + x[3]);
                    }
                
                task = new Item (x[1], x[2],effort);
                }
            catch(NumberFormatException e)
            {
                throw new InvalidWbsException("Invalid effort: " + x[3],e);
            }
            }
            else
            {
                task = new Item(x[1], x[2], null);
            }

            if(wbs.get(x[1]) !=null)
            {
                throw new InvalidWbsException("Duplicate task ID: " + x[1]);
            }
            wbs.add(task);

            if (x[0].isEmpty())
            {
                wbs.addRoot(task);
            }

        }

        for (String line : lines)
        {
            String[] x = split (line);

            if (!x[0].isEmpty())
            {
                Task task = wbs.get(x[1]);
                Task parent = wbs.get(x[0]);

                if(parent == null)
                {
                    throw new InvalidWbsException("Missing parent ID: " + x[0]);
                }
                wbs.join(x[0], task);
            }
        }
        return (wbs);

    }

    private  boolean isParent(List<String> lines, String id)
    {
        for (String line : lines)
        {
            String [] x = split (line);

            if (x[0].equals(id))
            {
                return true;
            }
        }
        return false;

    }



    public void save (String name, Wbs wbs) throws IOException
    {
        List<String> lines = new ArrayList<>();

        for (Task task : wbs.getRoots())
        {
            addLines(lines, task, "");
        }

        Files.write (Path.of(name), lines);
    }

    private void  addLines(List <String> lines, Task task, String parent)
    {
        String line = parent + ";" + task.getId() +";" + task.getText();

        if (task instanceof Item)
        {

        Item item = (Item) task;

        if (item.hasEffort())
        {
            line += ";" + item.getEffort();
        }
        }

        lines.add (line);

        if(task instanceof Group)
        {
            Group group = (Group) task;

            for (Task child : group.getKids())
            {
                addLines(lines, child, task.getId());

            }
        }
    }



    
}

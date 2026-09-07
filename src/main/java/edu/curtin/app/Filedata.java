package edu.curtin.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Filedata
{ 
    //records important file operations
    private static final Logger LOG = Logger.getLogger(Filedata.class.getName());

    public List<String> read (String name) throws IOException
    {
        //read all lines from the wbs
        return Files.readAllLines(Path.of(name));
    }

    public String[] split (String line)
    {
        //split each line using semicolon
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

        //first pass creates all the tasks
        for (String line: lines)
        {
            String [] x = split (line);

            //check the line has the correct number of fields
            if (x.length < 3 || x.length > 4)

            {
                throw new InvalidWbsException("Invalid WBS line " + line);
            }

            //every task must have and Id and description
            if (x[1].isEmpty() || x[2].isEmpty())
            {
                throw new InvalidWbsException("Missing ID or description " + line);

            }

            Task task;

            if(isParent(lines,x[1]))
            {
                //groups cant have an effort value
                if(x.length == 4)
                    {
                        throw new InvalidWbsException("Group has effort: " + x[1]);
                    }
                
                task = new Group(x[1], x[2]);
            }

            else if (x.length ==4)
            {
                if(x[3].isEmpty())
                {
                    //empty effort mean the leaf task is unknown
                    task = new Item (x[1], x[2], null);
                }
                else 
                {
                try
                {
                    int effort = Integer.parseInt(x[3]);

                    //effort must be positive
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
            }
            else
            {
                //a leaf without effort is an unknown task
                task = new Item(x[1], x[2], null);
            }

            //task Ids must be unique
            if(wbs.get(x[1]) !=null)
            {
                throw new InvalidWbsException("Duplicate task ID: " + x[1]);
            }
            wbs.add(task);

            //task without a parent are root tasks
            if (x[0].isEmpty())
            {
                wbs.addRoot(task);
            }

        }

        //second pass connect each task to its parent
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
        //check another task uses this ID as the parent
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

        //save all root task and their children
        for (Task task : wbs.getRoots())
        {
            addLines(lines, task, "");
        }
         LOG.info(() -> "Saving WBS file: " + name);

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

        //recursively save the children of a group
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

package edu.curtin.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Filedata
{
    public List<String> read (String name) throws IOException
    {
        return Files.readAllLines(Path.of(name));
    }

    public String[] split (String line)
    {
        return line.split(";");
    }

    public Task makeTask (String line)
    {
        String [] x = split (line);
        
        String id = x[1];
        String text = x[2];

        if(x.length == 4)
        {
            int effort = Integer.parseInt(x[3]);
            return new Item (id, text, effort);
        }
        return new Group (id, text);

    }

    public Wbs load (String name) throws IOException
    {
        List<String> lines = read (name);
        Wbs wbs = new Wbs ();

        for (String line : lines)
        {
            
        String [] x = split (line);
        Task task = makeTask(line);

        wbs.add(task);
        if(x[0].isEmpty())
        {
            wbs.addRoot(task);
        }

        }

        for (String line: lines)
        {
            String [] x = split (line);

            if (!x[0].isEmpty())
            {
                Task task = wbs.get(x[1]);
                wbs.join(x[0], task);
            }

        }

        return (wbs);

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

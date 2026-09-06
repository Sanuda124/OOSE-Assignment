package edu.curtin.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Wbs 
{
    private final Map<String, Task> tasks = new HashMap<>();
    private final List<Task> roots = new ArrayList<>();

    public void add (Task task)
    {
        tasks.put(task.getId(), task);
    }

    public Task get (String id)
    {
        return tasks.get(id);
    }

    public void addRoot(Task task)
    {
        roots.add(task);
    }

    public List <Task> getRoots()
    {
        return roots;
    }
    public void join (String parentId, Task child)
    {
        Task parent = get (parentId);

        if (parent instanceof Group)
        {
            ((Group) parent).add(child);
        }
    }

     public int getEffort()
    {
        int total = 0 ;
        for (Task task : roots)
        {
            total += task.getEffort();
        }
        return total;
    }

    public int getUnknown()
    {
        int total = 0;
        for (Task task : tasks.values())
        {
            if(task instanceof Item)
            {
                Item item = (Item) task;
                if(!item.hasEffort())
                {
                    total ++;
                }
            }
        }
        return  total;
    }



    
}

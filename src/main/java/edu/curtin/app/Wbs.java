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



    
}

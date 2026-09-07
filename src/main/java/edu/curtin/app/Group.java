package edu.curtin.app;

import  java.util.ArrayList;
import java.util.List;

public class Group extends Task
{
    //stores the child tasks inside the group
    private final List<Task> kids = new ArrayList<>();

    public Group(String id ,String text)
    {
        super(id,text);
    }

    //add a child task to a group
    public void add (Task task)
    {
        kids.add(task);
    }

    public List<Task> getKids()
    {
        return kids;

    }


    @Override 
    public int getEffort()
    {
        int total = 0;

        //add the effort of all child tasks
        for (Task task : kids)
        {
            total += task.getEffort();
        }
        return total;
    }
    
}

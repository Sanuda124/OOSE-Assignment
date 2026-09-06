package edu.curtin.app;

import  java.util.ArrayList;
import java.util.List;

public class Group extends Task
{
    private final List<Task> kids = new ArrayList<>();

    public Group(String id ,String text)
    {
        super(id,text);
    }

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

        for (Task task : kids)
        {
            total += task.getEffort();
        }
        return total;
    }
    
}

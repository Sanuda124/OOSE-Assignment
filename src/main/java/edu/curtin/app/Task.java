package edu.curtin.app;

public abstract class Task 
{
    private final String id;
    private final String text;

    protected Task (String id, String text)
    {
        this.id = id;
        this.text = text;

    }

    public String getId()
    {
        return  id;
    }
    public String getText()
    {
        return  text;
    }
    //each type of task calculates its effort differently
    public abstract int getEffort();
    
    
}

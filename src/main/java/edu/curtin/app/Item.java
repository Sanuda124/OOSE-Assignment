package edu.curtin.app;

public class Item extends Task
{
    private Integer effort;

    public Item (String id, String text, Integer effort)
    {
        super (id, text);
        this.effort = effort;
    }

    @Override 
    public  int getEffort()
    {
        if(effort == null)
        {
            return 0;
        }
        return effort;

    }

    public void setEffort(int effort)
    {
        this.effort = effort;
    }
    public boolean hasEffort()
    {
        return effort != null;
    }
    
}

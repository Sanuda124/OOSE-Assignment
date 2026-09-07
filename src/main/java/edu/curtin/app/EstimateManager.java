package edu.curtin.app;

public class EstimateManager 
{
    private Estimate estimate;

    public EstimateManager(Estimate estimate)
    {
        this.estimate =estimate;
    }
    public void estimate(Wbs wbs, String id, int number)
    {
        Task task = wbs.get(id);
        if(task != null)
        {
            doTask(task, number);
        }
        else
        {
            System.out.println("Task not found.");

        }
    }

    private void doTask(Task task , int number)
    {
        if (task instanceof Item)
        {
            Item item = (Item ) task;

            if (!item.hasEffort())
            {
                int value = estimate.getFinal (number);
                item.setEffort(value);
            }
        }

        if (task instanceof Group)
        {
            Group group = (Group) task;

            for (Task child : group.getKids())
            {
                doTask(child, number);
            }
        }
    }
    
}

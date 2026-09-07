package edu.curtin.app;

public class EstimateManager 
{
    //estimating tasks in the wbs
    private Estimate estimate;

    public EstimateManager(Estimate estimate)
    {
        this.estimate =estimate;
    }
    public void estimate(Wbs wbs, String id, int number)
    {
        //find the task using IDs
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
        //estimate the task if it is unknown leaf task
        if (task instanceof Item)
        {
            Item item = (Item ) task;

            if (!item.hasEffort())
            {
                int value = estimate.getFinal (number);
                item.setEffort(value);
            }
        }

        //recursively estimate all children of a group
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

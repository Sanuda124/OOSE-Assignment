package edu.curtin.app;

public class Screen 
{
    public void show (Wbs wbs)
    {
        //display all root tasks and their children
        for (Task task : wbs.getRoots())
        {
            showTask(task, 0);
        }

        //display the wbs summary
        System.out.println();
        System.out.println("Total effort: "+ wbs.getEffort());
        System.out.println("Unknown tasks: " + wbs.getUnknown());

    }

    private void showTask (Task task, int level)
    {
        String text = task.getText();

        if (task instanceof Item)
        {
            Item item = (Item) task;

            if(item.hasEffort())
            {
                text += " - " + item.getEffort();
            }
        }

        System.out.println(" ".repeat(level) + task.getId() + " - " + text);

        if (task instanceof Group)
        {
            Group group = (Group) task;

            //recursively display all child tasks
            for (Task child : group.getKids())
            {
                showTask (child, level + 1);

            }
        }
    }
}

   

    


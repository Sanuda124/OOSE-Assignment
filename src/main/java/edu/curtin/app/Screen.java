package edu.curtin.app;

public class Screen 
{
    public void show (Wbs wbs)
    {
        for (Task task : wbs.getRoots())
        {
            showTask(task, 0);
        }

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

            for (Task child : group.getKids())
            {
                showTask (child, level + 1);

            }
        }
    }
}

   

    


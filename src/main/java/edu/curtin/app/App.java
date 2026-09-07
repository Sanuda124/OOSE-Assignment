package edu.curtin.app;

import java.io.IOException;
import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        //loading and saving the WBS
        Filedata file = new Filedata();

        try
        {
            
            Wbs wbs = file.load (args[0]);

            //get the input from the user
            try (Scanner input = new Scanner (System.in))
            
            {
                Settings settings = new Settings();

                EstimateRule rule = new TalkRule(input);
                settings.setRule(rule);

                Estimate estimate = new Estimate (settings.getRule(), input);
                EstimateManager manager = new EstimateManager(estimate);

            Screen screen = new Screen ();
            Menu menu = new Menu (input);

            Configure configure = new Configure (input);

            int choice = 0;

            //keep showing the menu until choose quit
            while(choice !=3)
            {
                screen.show(wbs);

                choice = menu.show();

                if(choice ==1)
                    {
                        System.out.print("Task ID: ");
                        String id = input.next();

                        manager.estimate (wbs, id,  settings.getNumber());
                    }
                else if(choice ==2 )
                    {
                        //allow to change the number of estimate
                        configure.change(settings);

                        //update estimate with new selected estimates
                        estimate.setRule(settings.getRule());
                    }    
                
            }
            //save the update Wbs before quitng
            file.save(args[0], wbs);

        
        }
    }
        catch(IOException | InvalidWbsException e)
        {
            System.out.println(e.getMessage());
        }


    }
}
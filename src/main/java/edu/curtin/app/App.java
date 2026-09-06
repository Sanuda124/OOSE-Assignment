package edu.curtin.app;

import java.io.IOException;
import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        Filedata file = new Filedata();

        try
        {
            Wbs wbs = file.load (args[0]);

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
            while(choice !=3)
            {
                screen.show(wbs);

                choice = menu.show();

                if(choice ==1)
                    {
                        manager.estimate (wbs, settings.getNumber());
                    }
                else if(choice ==2 )
                    {
                        configure.change(settings);
                        estimate.setRule(settings.getRule());
                    }    
                
            }

        
        }
    }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }


    }
}
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

            try(Scanner input = new Scanner (System.in))
            {
                Settings settings = new Settings();

                EstimateRule rule = new TalkRule(input);
                settings.setRule(rule);

                Estimate estimate = new Estimate (settings.getRule(), input);
                EstimateManager manager = new EstimateManager(estimate);

                manager.estimate (wbs, settings.getNumber());

            Screen screen = new Screen ();
            screen.show(wbs);

        
        }
    }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }


    }
}
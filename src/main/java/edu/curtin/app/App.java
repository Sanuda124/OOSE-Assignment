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

            EstimateRule rule = new HighestRule();
            Estimate estimate = new Estimate(rule, input);
            EstimateManager manager = new EstimateManager(estimate);

            manager.estimate(wbs,3);

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
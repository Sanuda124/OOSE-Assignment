package edu.curtin.app;

import java.util.Scanner;

public class Configure 
{
    private final Scanner input;

    public Configure (Scanner input)
    {
        this.input = input;
    }

    public void change (Settings settings)
    {
        //get the number of estimators from the user
        System.out.print("Number of estimators: ");
        
        while(!input.hasNextInt())
        {
            System.out.println("Enter a number");
            input.next();
            System.out.print("Choose: ");

        
        }
        
        int number = input.nextInt();

        //The number of estimators must be < 1
        while(number < 1)
        {
            System.out.print("Enter 1 or more: ");
            while (!input.hasNextInt())
            {
                System.out.println("Enter a number.");
                input.next();
                System.out.print("Enter 1 or more");
            }
            number = input.nextInt();
        }

        settings.setNumber(number);
        
        //show available strategies
        System.out.println("1. Highest");
        System.out.println("2. Median");
        System.out.println("3. Discuss");
        System.out.println("Choose: ");

         while(!input.hasNextInt())
        {
            // only choices 1 2 3 are valid
            System.out.println("Enter 1, 2 or 3");
            input.next();
            System.out.print("Choose: ");

        
        }
         int choice = input.nextInt();
        

        while(choice < 1 || choice >3)
        {
            System.out.println("Enter 1, 2 or 3");
            System.out.print("Choose: ");

            while (!input.hasNextInt())
            {
                System.out.println("Enter 1, 2 or 3");
                input.next();
                System.out.print("Choose: ");

            }
            choice = input.nextInt();

            
        }
         
        //select the strategy based on the users choice
        if(choice ==1)
        {
            settings.setRule(new HighestRule());
        }
        else if (choice ==2)
        {
            settings.setRule(new MedianRule());
        }
        else 
        {
            settings.setRule(new TalkRule(input));
        }

    }
    
}

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
        System.out.print("Number of estimators: ");
        int number = input.nextInt();

        while(number < 1)
        {
            System.out.print("Enter 1 or more: ");
            number = input.nextInt();
        }

        settings.setNumber(number);

        System.out.println("1. Highest");
        System.out.println("2. Median");
        System.out.println("3. Discuss");
        System.out.println("Choose: ");

        int choice = input.nextInt();

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

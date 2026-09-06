package edu.curtin.app;

import java.util.Scanner;

public class Menu 
{
    private final Scanner input;

    public Menu (Scanner input)
    {
        this.input = input;
    }
    public int show()
    {
        System.out.println();
        System.out.println("1. Estimate effort");
        System.out.println("2. Configure");
        System.out.println("3. Quit");
        System.out.println("Choose: ");

        while(!input.hasNextInt())
        {
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
        return choice;


    }
    
}

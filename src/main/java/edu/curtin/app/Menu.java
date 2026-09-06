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

        return input.nextInt();
    }
    
}

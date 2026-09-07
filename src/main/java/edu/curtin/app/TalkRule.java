package edu.curtin.app;

import java.util.List;
import java.util.Scanner;

public class TalkRule implements EstimateRule 
{
    // get the final estimate from the user
    private final Scanner input;

    public TalkRule(Scanner input)
    {
        this.input = input;
    }

    @Override
    public int choose (List <Integer> estimates)
    {
        //ask the user to enter the estimate
        System.out.print("Final Estimate: ");
        return input.nextInt();
    }
    
}

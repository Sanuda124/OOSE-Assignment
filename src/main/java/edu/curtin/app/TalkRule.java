package edu.curtin.app;

import java.util.List;
import java.util.Scanner;

public class TalkRule implements EstimateRule 
{
    private final Scanner input;

    public TalkRule(Scanner input)
    {
        this.input = input;
    }

    @Override
    public int choose (List <Integer> estimates)
    {
        System.out.print("Final Estimate: ");
        return input.nextInt();
    }
    
}

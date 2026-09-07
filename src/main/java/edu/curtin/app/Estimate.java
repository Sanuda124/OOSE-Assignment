package edu.curtin.app;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Estimate 
{
    //stores the current reconciliation strategy
    private EstimateRule rule;
    private Scanner input;

    public Estimate(EstimateRule rule, Scanner input)
    {
        this.rule = rule;
        this.input = input;
    }

    public int choose (List<Integer> estimates)
    {
        //use the selected strategy to chose the final estimate
        return rule.choose(estimates);
    }
    
    public void setRule(EstimateRule rule)
    {
        //change the strategy
        this.rule = rule;
    }

    public List<Integer> getEstimates(int number)
    {
        List<Integer> estimates = new ArrayList<>();

        //get the required number of estimated from the user
        for (int i = 0; i< number; i++)
        {
            System.out.print("Estimate: ") ;

            //check the user enters a number
            while(!input.hasNextInt())
            {
                System.out.println("Enter a number");
                input.next();
                System.out.print("Estimate: ");
            }
            estimates.add(input.nextInt());
            
        }
        return estimates;
    }

    public int getFinal (int number)
    {
        List<Integer> estimates = getEstimates(number);
        System.out.println("Estimates: " + estimates);

        boolean same = true;

        //check all estimates are the same
        for (int i =1; i < estimates.size(); i++)
        {
            if(!estimates.get(i).equals(estimates.get(0)))
            {
                same = false;
            }
        }
        if(same)
        {
            return estimates.get(0);
        }
        
        return choose (estimates);
    }
}

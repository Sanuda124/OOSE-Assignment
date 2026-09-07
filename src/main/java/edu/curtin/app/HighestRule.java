package edu.curtin.app;

import java.util.List;

//choose the highest estimate
public class HighestRule implements EstimateRule 
{
    @Override 
    public int choose (List<Integer> estimates)
    {
        int high = estimates.get(0);

        //find the highest value in the list
        for (int estimate : estimates)
        {
            if(estimate > high)
            {
                high = estimate;
            }
        }
        return high;
    }
    
}

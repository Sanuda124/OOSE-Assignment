package edu.curtin.app;

import java.util.List;

public class HighestRule implements EstimateRule 
{
    @Override 
    public int choose (List<Integer> estimates)
    {
        int high = estimates.get(0);

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

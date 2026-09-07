package edu.curtin.app;

import java.util.List;

public interface EstimateRule
{
   //choose the final estimate from given estimates
   int choose (List<Integer> estimates);
    
}

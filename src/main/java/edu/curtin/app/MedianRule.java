package edu.curtin.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// calculate the median estimate
public class MedianRule implements EstimateRule
{
    @Override 
    public int choose (List <Integer> estimates)
    {
        //make a copy so original list not change
        List<Integer> numbers = new ArrayList<>(estimates);

        //sort the estimate before finding the middle value
        Collections.sort(numbers);

        int middle = numbers.size() / 2 ;

        //return the middle value when there is an odd number of estimates
        if (numbers.size() % 2 ==1)
        {
            return  numbers.get(middle);

        }
        return (numbers.get(middle -1) + numbers.get(middle)) / 2 ;
    }
    
}

package edu.curtin.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedianRule implements EstimateRule
{
    @Override 
    public int choose (List <Integer> estimates)
    {
        List<Integer> numbers = new ArrayList<>(estimates);

        Collections.sort(numbers);

        int middle = numbers.size() / 2 ;

        if (numbers.size() % 2 ==1)
        {
            return  numbers.get(middle);

        }
        return (numbers.get(middle -1) + numbers.get(middle)) / 2 ;
    }
    
}

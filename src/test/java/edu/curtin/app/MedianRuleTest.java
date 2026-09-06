package edu.curtin.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MedianRuleTest 
{
    @Test 
    void testMedian()
    {
        MedianRule rule = new MedianRule();

        int result = rule.choose (List.of(10,20,30));

        assertEquals(20,result);
    }
    
    @Test 
    void testEvenMedian()
    {
        MedianRule rule = new MedianRule();

        int result = rule.choose (List.of(10,20,30,40));

        assertEquals(25,result);
    }
}

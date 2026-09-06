package edu.curtin.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;


public class HighestRuleTest 
{
    @Test void testHighest()
    {
        HighestRule rule = new HighestRule();

        int result = rule.choose(List.of(10,15,12));

        assertEquals(15, result);
    }
    
}

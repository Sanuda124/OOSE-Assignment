package edu.curtin.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Test;


public class TalkRuleTest
{
    @Test 
    void testTalk()
    {
       try( Scanner input = new Scanner ("20\n"))
       {
        TalkRule rule = new TalkRule (input);

        int result = rule. choose (List.of(10,15,12));

        assertEquals(20, result);
       }
    }
    
}

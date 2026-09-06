package edu.curtin.app;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class GroupTest 
{
    @Test void testGroupEffort()
    {
        Group group = new Group ("1", "Project");

        Item item1 = new Item ("2", "Task1", 10);
        Item item2 = new Item ("3", "Task2", 20);

        group.add(item1);
        group.add(item2);

        assertEquals(30, group.getEffort());
    }

     @Test void testNestedGroupEffort()
    {
             Group project = new Group ("1", "Project");
             Group website = new Group ("2", "Website");

             Item item = new Item ("3", "Home Page",10);

             website.add(item);
             project.add(website);

             assertEquals(10, project.getEffort());


    }
    
}

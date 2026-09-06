package edu.curtin.app;

/**
 * Entry point into the application. To change the package, and/or the name of this class, make
 * sure to update the 'mainClass = ...' line in build.gradle.kts.
 */
import java.io.IOException;

public class App
{
    public static void main(String[] args)
    {
        Filedata file = new Filedata();

        try{
            Wbs wbs = file.load (args[0]);
            System.out.println(wbs.getRoots().size());
        }
        catch(java.io.IOException e)
        {
            System.out.println(e.getMessage());
        }


    }
}
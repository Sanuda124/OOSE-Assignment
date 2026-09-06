package edu.curtin.app;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Filedata
{
    public List<String> read (String name) throws IOException
    {
        return Files.readAllLines(Path.of(name));
    }

    public String[] split (String line)
    {
        return line.split(";");
    }

    public Task makeTask (String line)
    {
        String [] x = split (line);
        
        String id = x[1];
        String text = x[2];

        if(x.length == 4)
        {
            int effort = Integer.parseInt(x[3]);
            return new Item (id, text, effort);
        }
        return new Group (id, text);
        
    }



    
}

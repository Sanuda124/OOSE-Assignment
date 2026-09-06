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

    
}

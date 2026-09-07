package edu.curtin.app;
import java.util.logging.Logger;

public class Hello
{
    //record a message
    private static final Logger log = Logger.getLogger(Hello.class.getName());

    public static String getHello()
    {
        //log a message before returning the result
        log.info("About to return 'Hello world!'");
        return "Hello world!";
    }
}

package edu.curtin.app;

public class InvalidWbsException extends Exception 
{
    public InvalidWbsException(String message)
    {
        super(message);
    }
    
    public InvalidWbsException (String message, Throwable cause)
    {
        super(message,cause);
    }
}

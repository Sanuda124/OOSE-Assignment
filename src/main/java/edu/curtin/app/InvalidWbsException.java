package edu.curtin.app;

//exception for invalid wbs data
public class InvalidWbsException extends Exception 
{
    public InvalidWbsException(String message)
    {
        super(message);
    }
    
    //allows the original error to be included as the cause
    public InvalidWbsException (String message, Throwable cause)
    {
        super(message,cause);
    }
}

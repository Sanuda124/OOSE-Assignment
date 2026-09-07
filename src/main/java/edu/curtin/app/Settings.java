package edu.curtin.app;

public class Settings 
{
    //default number of estimators 3
    private int number = 3;
    private EstimateRule rule;
    public Settings()
    {
        rule = null;
    }

    public int getNumber()
    {
        return number;

    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public EstimateRule getRule()
    {
        return  rule;
    }

    public void setRule(EstimateRule rule)
    {
        //change the current strategy
        this.rule = rule;
    }
    
}

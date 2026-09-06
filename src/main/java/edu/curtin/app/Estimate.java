package edu.curtin.app;

import java.util.List;

public class Estimate 
{
    private EstimateRule rule;

    public Estimate(EstimateRule rule)
    {
        this.rule = rule;
    }

    public int choose (List<Integer> estimates)
    {
        return rule.choose(estimates);
    }
    
    public void setRule(EstimateRule rule)
    {
        this.rule = rule;
    }
}

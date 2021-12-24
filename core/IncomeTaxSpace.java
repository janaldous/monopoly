package core;
import java.util.*;

public class IncomeTaxSpace extends Space
{
    public IncomeTaxSpace(PlayerActionFactory factory)
    {
        super("Income Tax", Arrays.asList(factory.createChargePlayerAction(200)));    
    }
}

package core;
import java.util.List;

public class UtilityCompanySpace extends PropertySpace
{
    public UtilityCompanySpace(String name, 
                                int value, 
                                List<PlayerAction> requiredActions,
                                List<PlayerAction> playerActions)
    {
        super(name, value, playerActions);
    }
}

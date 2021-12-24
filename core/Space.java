package core;
import java.util.*;

public class Space
{
    private final String name;
    private final List<PlayerAction> requiredActions;
    
    protected Space(String name, List<PlayerAction> requiredActions) {
        this.name = name;
        this.requiredActions = requiredActions;
    }
    
    protected Space() {
        this.name = null;
        this.requiredActions = null;
    }
    
    public String getName() {
        return name;
    }
    
    public List<PlayerAction> getPlayerOptions() {
        return Collections.emptyList();
    }
    
    public List<PlayerAction> getRequiredActions() {
        return requiredActions;
    }
}

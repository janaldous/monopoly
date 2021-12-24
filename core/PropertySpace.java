package core;
import java.util.*;

public class PropertySpace extends Space
{
    private Player owner;
    private final int value;
    private final List<PlayerAction> playerActionOptions;
    
    protected PropertySpace(String name, 
                            int value,
                            List<PlayerAction> requiredActions,
                            List<PlayerAction> playerActionOptions)
    {
        super(name, requiredActions);
        this.playerActionOptions = playerActionOptions;
        this.value = value;
    }
    
    protected PropertySpace(String name, 
                            int value,
                            List<PlayerAction> playerActionOptions)
    {
        this(name, value, Collections.emptyList(), playerActionOptions);
    }
    
    public void setOwner(Player owner) {
        this.owner = owner;
    }
    
    public Player getOwner() {
        return owner;
    }
    
    public int getValue() {
        return value;
    }
    
    @Override
    public List<PlayerAction> getPlayerOptions() {
        return playerActionOptions;
    }
}

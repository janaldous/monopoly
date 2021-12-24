package core;

import core.exceptions.*;
import java.util.*;

public class BuyPropertyPlayerAction implements PlayerAction
{
    private final GameContext context;
    
    public BuyPropertyPlayerAction(GameContext context) {
        this.context = context;
    }
    
    @Override
    public Optional<PlayerAction> act(Player player) throws PlayerActionException {
        Token token = context.getPlayerToken(player);
        Gameboard gameboard = context.getGameboard();
        Space space = gameboard.getSpace(token);
        if (space instanceof PropertySpace) {
            PropertySpace property = (PropertySpace) space;
            
            if (hasOwner(property)) {
                throw new PlayerActionException("Property is already owned by another player");
            }
            
            if (playerCanAfford(player, property)) {
                try
                {
                    player.pay(property.getValue());
                }
                catch (NotEnoughMoneyException neme)
                {
                    throw new PlayerActionException(neme);
                }
                property.setOwner(player);
            }
        }
        
        return Optional.empty();
    }
    
    private boolean playerCanAfford(Player player, PropertySpace property) {
        return player.getBalance() - property.getValue() >= 0;
    }
    
    private boolean hasOwner(PropertySpace property) {
        return property.getOwner() != null;
    }
    
    @Override
    public String getName() {
        return "";
    }
}

package core;

import core.exceptions.*;
import java.util.*;

public class BuyHousePlayerAction implements PlayerAction
{
    private static final int MAX_NUMBER_OF_APARTMENTS = 4;
    
    private final GameContext context;
    
    public BuyHousePlayerAction(GameContext context) {
        this.context = context;
    }
    
    @Override
    public Optional<PlayerAction> act(Player player) throws PlayerActionException {
        Token token = context.getPlayerToken(player);
        Gameboard gameboard = context.getGameboard();
        Space space = gameboard.getSpace(token);
        if (space instanceof ResidentialSpace) {
            ResidentialSpace property = (ResidentialSpace) space;
            
            if (!property.getOwner().equals(player)) {
                throw new PlayerActionException("This property is not owned by the player");
            }
            
            if (property.getHouseQty() >= MAX_NUMBER_OF_APARTMENTS) {
                throw new PlayerActionException("You cannot have more than " + MAX_NUMBER_OF_APARTMENTS + " apartments");
            }
            
            if (playerCanAffordApartment(player, property)) {
                try
                {
                    player.pay(property.getValue());
                }
                catch (NotEnoughMoneyException neme)
                {
                    throw new PlayerActionException(neme);
                }
                property.addHouse();
            }
        }
        
        return Optional.empty();
    }
    
    private boolean playerCanAffordApartment(Player player, ResidentialSpace property) {
        return player.getBalance() - property.getHouseValue() >= 0;
    }
    
    @Override
    public String getName() {
        return "";
    }
}

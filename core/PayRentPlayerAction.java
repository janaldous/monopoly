package core;

import core.exceptions.*;
import java.util.*;

public class PayRentPlayerAction implements PlayerAction
{
    private static final int MAX_NUMBER_OF_APARTMENTS = 4;
    
    private final GameContext context;
    
    public PayRentPlayerAction(GameContext context) {
        this.context = context;
    }
    
    @Override
    public Optional<PlayerAction> act(Player player) throws PlayerActionException {
        Token token = context.getPlayerToken(player);
        Gameboard gameboard = context.getGameboard();
        Space space = gameboard.getSpace(token);
        if (space instanceof ResidentialSpace) {
            ResidentialSpace property = (ResidentialSpace) space;
            int rent = property.getRent();
            Player owner = property.getOwner();
            
            // owner does not pay rent
            if (owner.equals(player)) {
                return Optional.empty();
            }
            
            if (rent > 0) {
                try
                {
                    player.pay(rent);
                }
                catch (NotEnoughMoneyException neme)
                {
                    throw new PlayerActionException(neme);
                }
                owner.addMoney(rent);
            }
        }
        
        return Optional.empty();
    }
    
    @Override
    public String getName() {
        return "";
    }
}

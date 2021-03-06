package core;

import core.exceptions.*;
import java.util.*;

public class PickCommunityChestCardAction implements PlayerAction
{
    private final GameContext context;
    
    public PickCommunityChestCardAction(GameContext context) {
        this.context = context;
    }
    
    @Override
    public Optional<PlayerAction> act(Player player) throws PlayerActionException {
        Card card = context.getGameboard().takeCommunityChestCard();
        
        return Optional.of(card.getPlayerAction());
    }
    
    @Override
    public String getName() {
        return "";
    }
}

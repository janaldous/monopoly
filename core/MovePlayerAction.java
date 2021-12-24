package core;

import core.exceptions.*;
import java.util.*;

public class MovePlayerAction implements PlayerAction
{
    private final String propertyName;
    private final GameContext context;
    
    public MovePlayerAction(String propertyName, GameContext context) {
        this.propertyName = propertyName;
        this.context = context;
    }
    
    @Override
    public Optional<PlayerAction> act(Player player) throws PlayerActionException {
        Token token = context.getPlayerToken(player);
        Gameboard gameboard = context.getGameboard();
        int startPosition = gameboard.getPosition(token);
        int endPosition = gameboard.getPositionBySpaceName(propertyName);
        gameboard.move(token, Math.abs(endPosition - startPosition));
        
        return Optional.empty();
    }
    
    @Override
    public String getName() {
        return "";
    }
}

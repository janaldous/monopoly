package core;

import core.exceptions.*;
import java.util.Optional;

public interface PlayerAction
{
    
    /**
     * Method act
     *
     * @param player player that will have to carry out action
     * @return not null if there are succeeding actions (i.e. Community Chest card needed action) 
     */
    Optional<PlayerAction> act(Player player) throws PlayerActionException;
    String getName();
}

package rules;

import core.Space;
import core.Player;

/**
 * Write a description of interface SpaceActionHandler here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface SpaceActionHandler
{
    
    /**
     * Method handleAction
     *
     * @param player A parameter
     * @param space A parameter
     * @return true if player has one more go
     */
    boolean handleAction(Player player, Space space);
}

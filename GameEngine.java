
import core.*;
import java.util.List;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
public class GameEngine
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class GameEngine
     */
    public GameEngine()
    {
        // initialise instance variables
        x = 0;
    }

    public static void main(String args[]) {
        System.out.println("Starting...");
        
        GameContextImpl gameContext = new GameContextImpl();
        Bank bank = new BankImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(bank, gameContext);
        SpaceFactory spaceFactory = new SpaceFactory(playerActionFactory);
        
        Space[] spaces = new Space[] {
            spaceFactory.createSpace("Go"), 
            spaceFactory.createSpace("IncomeTax")
        };
        
        Token[] playerTokens = new Token[] {new CarToken(), new DogToken()};
        Map<Token, Integer> tokenPositions = new HashMap<>() {{
            put(playerTokens[0], 0);
            put(playerTokens[1], 0);
        }};
        Queue<Card> communityChestCards = new LinkedList<>(Arrays.asList());
        Queue<Card> chanceCards = new LinkedList<>(Arrays.asList());
        Gameboard gameboard = new GameboardImpl(spaces, tokenPositions, gameContext, communityChestCards, chanceCards);
        Dice dice = new DiceImpl(2);
        // pick order of players
        Player[] players = new Player[] {new Player("A", 1500), new Player("B", 1500)};
        
        gameContext.setDice(dice);
        gameContext.setGameboard(gameboard);
        gameContext.setPlayers(players);
        gameContext.setTokens(playerTokens);
        
        // pick first player
        int curPlayerIndex = 0;
        for (int i = 0; i < 2; i++) {
            // roll dice
            Player currentPlayer = players[curPlayerIndex];
            int steps = dice.roll();
            
            // move token
            Space space = gameboard.move(playerTokens[curPlayerIndex], steps);
            
            // based on space, take action
            // player buys space?
            // player picks card? (Community Chest, Chance)
            // player pays (Income Tax, Luxury Tax, etc)
            // player goes to jail
            // player do nothing
            
            List<PlayerAction> requiredActions = space.getRequiredActions();
            for (PlayerAction requiredAction: requiredActions) {
                try
                {
                    requiredAction.act(currentPlayer);
                }
                catch (core.exceptions.PlayerActionException pae)
                {
                    pae.printStackTrace();
                }
            }
            
            List<PlayerAction> playerActions = space.getPlayerOptions();
            
            if (!playerActions.isEmpty()) {
                // always pick first action
                PlayerAction action = playerActions.get(0);
                try
                {
                    action.act(currentPlayer);
                }
                catch (core.exceptions.PlayerActionException e)
                {
                    e.printStackTrace();
                }
                // TODO validate if action is valid (enough money, etc)
            }
            
            // TODO future: player buys property from another player
            
            curPlayerIndex = (curPlayerIndex + 1) % playerTokens.length;
        }
        
        System.out.println("end of game");
        System.out.println("player 0 " + players[0].getBalance());
        System.out.println("player 1 " + players[1].getBalance());
    }
}

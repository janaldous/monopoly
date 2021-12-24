
import core.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class GameEngineTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GameEngineTest
{
    Player[] players;
    Gameboard gameboard;
    Token[] tokens;
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
    
    @Test
    public void tesIncomeTaxSpace() throws core.exceptions.PlayerActionException {
        // given
        GameContextImpl gameContext = new GameContextImpl();
        Bank bank = new BankImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(bank, gameContext);
        SpaceFactory spaceFactory = new SpaceFactory(playerActionFactory);
        
        Space[] spaces = new Space[] {
            spaceFactory.createSpace("Go"), 
            spaceFactory.createSpace("IncomeTax")
        };
        
        tokens = new Token[] {new CarToken(), new DogToken()};
        Map<Token, Integer> tokenPositions = new HashMap<>() {{
            put(tokens[0], 0);
            put(tokens[1], 0);
        }};
        Queue<Card> communityChestCards = new LinkedList<>(Arrays.asList());
        Queue<Card> chanceCards = new LinkedList<>(Arrays.asList());
        gameboard = new GameboardImpl(spaces, tokenPositions, gameContext, communityChestCards, chanceCards);
        Dice dice = new DiceImpl(2);
        // pick order of players
        players = new Player[] {new Player("A", 1500), new Player("B", 1500)};
        
        gameContext.setDice(dice);
        gameContext.setGameboard(gameboard);
        gameContext.setPlayers(players);
        gameContext.setTokens(tokens);
        
        // when
        int curPlayerIndex = 0;
        Player currentPlayer = players[curPlayerIndex];
        Space space = gameboard.move(tokens[curPlayerIndex], 1);
        List<PlayerAction> requiredActions = space.getRequiredActions();
        requiredActions.get(0).act(currentPlayer);
        
        // then
        assertEquals(1300, currentPlayer.getBalance());
        assertEquals(1500, players[1].getBalance());
    }
    
    @Test
    public void tesCommunityChest() throws core.exceptions.PlayerActionException {
        // given
        GameContextImpl gameContext = new GameContextImpl();
        Bank bank = new BankImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(bank, gameContext);
        SpaceFactory spaceFactory = new SpaceFactory(playerActionFactory);
        
        Space[] spaces = new Space[] {
            spaceFactory.createSpace("Go"), 
            spaceFactory.createSpace("CommunityChest"),
            createStCharlesPlace(spaceFactory)
        };
        
        tokens = new Token[] {new CarToken(), new DogToken()};
        Map<Token, Integer> tokenPositions = new HashMap<>() {{
            put(tokens[0], 0);
            put(tokens[1], 0);
        }};
        CardFactory cardFactory = new CardFactory(playerActionFactory);
        Queue<Card> communityChestCards = new LinkedList<>(Arrays.asList(cardFactory.createCommunityChestCard("AdvanceToStCharlesPlace")));
        Queue<Card> chanceCards = new LinkedList<>(Arrays.asList());
        gameboard = new GameboardImpl(spaces, tokenPositions, gameContext, communityChestCards, chanceCards);
        Dice dice = new DiceImpl(2);
        // pick order of players
        players = new Player[] {new Player("A", 1500), new Player("B", 1500)};
        
        gameContext.setDice(dice);
        gameContext.setGameboard(gameboard);
        gameContext.setPlayers(players);
        gameContext.setTokens(tokens);
        
        // when
        int curPlayerIndex = 0;
        Player currentPlayer = players[curPlayerIndex];
        Space space = gameboard.move(tokens[curPlayerIndex], 1);
        List<PlayerAction> requiredActions = space.getRequiredActions();
        // pick card
        Optional<PlayerAction> nextAction = requiredActions.get(0).act(currentPlayer);
        nextAction.get().act(currentPlayer);
        
        // then
        assertEquals(2, gameboard.getPosition(tokens[curPlayerIndex]));
    }
    
    @Test
    public void tesBuyProperty() throws core.exceptions.PlayerActionException {
        // given
        GameContextImpl gameContext = new GameContextImpl();
        Bank bank = new BankImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(bank, gameContext);
        SpaceFactory spaceFactory = new SpaceFactory(playerActionFactory);
                
        Space[] spaces = new Space[] {
            spaceFactory.createSpace("Go"), 
            createStCharlesPlace(spaceFactory)
        };
        
        tokens = new Token[] {new CarToken(), new DogToken()};
        Map<Token, Integer> tokenPositions = new HashMap<>() {{
            put(tokens[0], 0);
            put(tokens[1], 0);
        }};
        CardFactory cardFactory = new CardFactory(playerActionFactory);
        Queue<Card> communityChestCards = new LinkedList<>(Arrays.asList(cardFactory.createCommunityChestCard("AdvanceToStCharlesPlace")));
        Queue<Card> chanceCards = new LinkedList<>(Arrays.asList());
        gameboard = new GameboardImpl(spaces, tokenPositions, gameContext, communityChestCards, chanceCards);
        Dice dice = new DiceImpl(2);
        // pick order of players
        players = new Player[] {new Player("A", 1500), new Player("B", 1500)};
        
        gameContext.setDice(dice);
        gameContext.setGameboard(gameboard);
        gameContext.setPlayers(players);
        gameContext.setTokens(tokens);
        
        // when
        int curPlayerIndex = 0;
        Player currentPlayer = players[curPlayerIndex];
        Space space = gameboard.move(tokens[curPlayerIndex], 1);
        List<PlayerAction> playerOptions = space.getPlayerOptions();
        // buy property
        playerOptions.get(0).act(currentPlayer);
        
        // then
        assertEquals(currentPlayer, ((PropertySpace) gameboard.getSpaceBySpaceName("St Charles Place")).getOwner());
    }
    
    @Test
    public void tesBuyPropertyAndHouse() throws core.exceptions.PlayerActionException {
        // given
        GameContextImpl gameContext = new GameContextImpl();
        Bank bank = new BankImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(bank, gameContext);
        SpaceFactory spaceFactory = new SpaceFactory(playerActionFactory);
        
        Space[] spaces = new Space[] {
            spaceFactory.createSpace("Go"), 
            createStCharlesPlace(spaceFactory)
        };
        
        tokens = new Token[] {new CarToken(), new DogToken()};
        Map<Token, Integer> tokenPositions = new HashMap<>() {{
            put(tokens[0], 0);
            put(tokens[1], 0);
        }};
        CardFactory cardFactory = new CardFactory(playerActionFactory);
        Queue<Card> communityChestCards = new LinkedList<>(Arrays.asList(cardFactory.createCommunityChestCard("AdvanceToStCharlesPlace")));
        Queue<Card> chanceCards = new LinkedList<>(Arrays.asList());
        gameboard = new GameboardImpl(spaces, tokenPositions, gameContext, communityChestCards, chanceCards);
        Dice dice = new DiceImpl(2);
        // pick order of players
        players = new Player[] {new Player("A", 1500), new Player("B", 1500)};
        
        gameContext.setDice(dice);
        gameContext.setGameboard(gameboard);
        gameContext.setPlayers(players);
        gameContext.setTokens(tokens);
        
        // when
        int curPlayerIndex = 0;
        Player currentPlayer = players[curPlayerIndex];
        Space space = gameboard.move(tokens[curPlayerIndex], 1);
        List<PlayerAction> playerOptions = space.getPlayerOptions();
        // buy property
        actAllActions(playerOptions, currentPlayer);
        
        // then
        assertEquals(currentPlayer, ((PropertySpace) gameboard.getSpaceBySpaceName("St Charles Place")).getOwner());
        assertEquals(1, ((ResidentialSpace) gameboard.getSpaceBySpaceName("St Charles Place")).getHouseQty());
    }
    
    private ResidentialSpace createStCharlesPlace(SpaceFactory spaceFactory) {
        return (ResidentialSpace) spaceFactory.createPropertySpace("residential", "St Charles Place", 140, 100, 100, 10, 50, 750, ColorGroup.PINK);
    }
    
    @Test
    public void tesCollectRent() throws core.exceptions.PlayerActionException {
        // given
        GameContextImpl gameContext = new GameContextImpl();
        Bank bank = new BankImpl();
        PlayerActionFactory playerActionFactory = new PlayerActionFactory(bank, gameContext);
        SpaceFactory spaceFactory = new SpaceFactory(playerActionFactory);
        
        ResidentialSpace property = (ResidentialSpace) createStCharlesPlace(spaceFactory);
        
        Space[] spaces = new Space[] {
            spaceFactory.createSpace("Go"), 
            property
        };
        
        tokens = new Token[] {new CarToken(), new DogToken()};
        Map<Token, Integer> tokenPositions = new HashMap<>() {{
            put(tokens[0], 0);
            put(tokens[1], 0);
        }};
        CardFactory cardFactory = new CardFactory(playerActionFactory);
        Queue<Card> communityChestCards = new LinkedList<>(Arrays.asList(cardFactory.createCommunityChestCard("AdvanceToStCharlesPlace")));
        Queue<Card> chanceCards = new LinkedList<>(Arrays.asList());
        gameboard = new GameboardImpl(spaces, tokenPositions, gameContext, communityChestCards, chanceCards);
        Dice dice = new DiceImpl(2);
        // pick order of players
        players = new Player[] {new Player("A", 1500), new Player("B", 1500)};
        property.setOwner(players[0]);
        
        gameContext.setDice(dice);
        gameContext.setGameboard(gameboard);
        gameContext.setPlayers(players);
        gameContext.setTokens(tokens);
        
        // when
        int curPlayerIndex = 1;
        Player currentPlayer = players[curPlayerIndex];
        Space space = gameboard.move(tokens[curPlayerIndex], 1);
        List<PlayerAction> requiredActions = space.getRequiredActions();
        // buy property
        assertEquals(1, requiredActions.size());
        requiredActions.get(0).act(currentPlayer);
        
        
        // then
        assertEquals(1510, players[0].getBalance());
        assertEquals(1490, currentPlayer.getBalance());
        
    }
    
    private void actAllActions(List<PlayerAction> playerActions, Player player) throws core.exceptions.PlayerActionException {
        for (PlayerAction pa: playerActions) {
            pa.act(player);
        }
    }
}

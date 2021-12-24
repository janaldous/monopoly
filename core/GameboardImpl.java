package core;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.Map;

public class GameboardImpl implements Gameboard
{
    private final Space[] spaces;
    private final GameContext context;
    private final Map<String, Space> spaceNameToSpace;
    private final Map<Token, Integer> tokenPositions;
    private final Queue<Card> communityChestCards;
    private final Queue<Card> chanceCards;
    
    public GameboardImpl(Space[] spaces, 
                        Map<Token, Integer> tokenPositions,
                        GameContext context, 
                        Queue<Card> communityChestCards,
                        Queue<Card> chanceCards) {
        assert spaces.length > 0;

        this.spaces = spaces;
        this.context = context;
        this.tokenPositions = tokenPositions;
        this.communityChestCards = communityChestCards;
        this.chanceCards = chanceCards;
        spaceNameToSpace = Arrays.stream(spaces).collect(Collectors.toMap(Space::getName, Function.identity()));
    }
    
    @Override
    public Card takeChanceCard() {
        return chanceCards.poll();
    }
    
    @Override
    public Card takeCommunityChestCard() {
        return communityChestCards.poll();
    }
    
    @Override
    public Space move(Token token, int steps) {
        int startPosition = tokenPositions.get(token);
        int newPosition = (startPosition + steps) % spaces.length;
        tokenPositions.put(token, newPosition);
        
        return spaces[newPosition];
    }
    
    @Override
    public Space getSpace(Token token) {
        return spaces[getPosition(token)];
    }
    
    @Override
    public int getPosition(Token token) {
        return tokenPositions.get(token);
    }
    
    @Override
    public int getPositionBySpaceName(String spaceName) {
        Space space = spaceNameToSpace.get(spaceName);
        
        for (int i = 0; i < spaces.length; i++) {
            Space s = spaces[i];
            if (space == s) {
                return i;
            }
        }
        
        return -1;
    }
    
    @Override
    public Space getSpaceBySpaceName(String spaceName) {
        return spaceNameToSpace.get(spaceName);
    }
}

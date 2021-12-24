package core;


public interface Gameboard
{
    Space move(Token token, int steps);
    Card takeCommunityChestCard();
    Card takeChanceCard();
    int getPosition(Token token);
    Space getSpace(Token token);
    int getPositionBySpaceName(String spaceName);
    Space getSpaceBySpaceName(String spaceName);
}

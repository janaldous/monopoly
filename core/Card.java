package core;

public class Card
{
    private final PlayerAction playerAction;
    
    protected Card(PlayerAction playerAction)
    {
        this.playerAction = playerAction;
    }
    
    public PlayerAction getPlayerAction() {
        return playerAction;
    }
}

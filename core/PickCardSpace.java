package core;

import java.util.*;

public class PickCardSpace extends Space
{
    public PickCardSpace(PlayerActionFactory factory)
    {
        super("Community Chest", Arrays.asList(factory.createPlayerAction("PickCommunityChestCard")));
    }
}

package core;

import java.util.*;

public class GoSpace extends Space
{
    public GoSpace(PlayerActionFactory factory) {
        super("Go", Arrays.asList(factory.createPlayerAction("CollectSalary")));
    }
}

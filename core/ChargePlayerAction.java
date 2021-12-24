package core;

import core.exceptions.*;
import java.util.*;

public class ChargePlayerAction implements PlayerAction
{
    private final int cost;
    private final Bank bank;
    
    public ChargePlayerAction(int cost, Bank bank) {
        this.cost = cost;
        this.bank = bank;
    }
    
    @Override
    public Optional<PlayerAction> act(Player player) throws PlayerActionException {
        try
        {
            player.pay(cost);
            bank.deposit(cost);
        }
        catch (core.exceptions.NotEnoughMoneyException e)
        {
            throw new PlayerActionException(e);
        }
        
        return Optional.empty();
    }
    
    @Override
    public String getName() {
        return "";
    }
}

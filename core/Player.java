package core;

import core.exceptions.*;

public class Player
{
    private final String name;
    private int balance;
    
    public Player(String name, int startingBalance)
    {
        this.name = name;
        this.balance = startingBalance;
    }
    
    public int addMoney(int amount) {
        balance += amount;
        return balance;
    }
    
    public int pay(int amount) throws NotEnoughMoneyException {
        if (balance - amount < 0) {
            throw new NotEnoughMoneyException();
        }
        
        balance -= amount;
        
        return balance;
    }
    
    public int getBalance() {
        return balance;
    }
}

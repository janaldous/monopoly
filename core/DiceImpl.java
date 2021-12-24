package core;

import java.util.Random;

public class DiceImpl implements Dice
{
    private final int numberOfDice;
    
    public DiceImpl(int numberOfDice) {
        this.numberOfDice = numberOfDice;
    }
    
    @Override
    public int roll() {
        return new Random().nextInt(6 * numberOfDice);
    }
}

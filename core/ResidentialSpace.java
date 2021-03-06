package core;

import java.util.*;

public class ResidentialSpace extends PropertySpace
{
    private final ColorGroup colorGroup;
    // implement as priority list? or separate apartments from hotel?
    private int houses;
    private int hotels;
    private boolean isMortgaged;
    private final int housePrice;
    private final int hotelPrice;
    private final int siteOnlyRent;
    private final int houseRent;
    private final int hotelRent;
    private boolean hasHotel;
    
    public ResidentialSpace(String name, 
                            int value, 
                            ColorGroup colorGroup,
                            int housePrice,
                            int hotelPrice,
                            int siteOnlyRent,
                            int houseRent,
                            int hotelRent,
                            List<PlayerAction> requiredActions,
                            List<PlayerAction> playerActions)
    {
        super(name, value, requiredActions, playerActions);
        this.colorGroup = colorGroup;
        this.housePrice = housePrice;
        this.hotelPrice = hotelPrice;
        this.siteOnlyRent = siteOnlyRent;
        this.houseRent = houseRent;
        this.hotelRent = hotelRent;
    }
    
    public void addHouse() {
        houses++;
    }
    
    public void removeHouse() {
        if (houses - 1 >= 0) {
            houses--;
        }
    }
    
    public int getHouseQty() {
        return houses;
    }
    
    public int getHouseValue() {
        return housePrice;
    }
    
    public void mortgage() {
        isMortgaged = true;
    }
    
    public int getRent() {
        if (houses == 0) {
            return siteOnlyRent;
        }
        return (houses * houseRent) + (hotels * hotelRent);
    }
}

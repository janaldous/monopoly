package core;


import java.util.*;

public class SpaceFactory
{
    private final PlayerActionFactory playerActionFactory;
    
    public SpaceFactory(PlayerActionFactory playerActionFactory) {
        this.playerActionFactory = playerActionFactory;
    }

    public Space createSpace(String spaceName) {
        switch (spaceName) {
            case "Go":
                return new GoSpace(playerActionFactory);
            case "IncomeTax":
                return new IncomeTaxSpace(playerActionFactory);
            case "CommunityChest":
                return new PickCardSpace(playerActionFactory);
            default:
                throw new IllegalArgumentException("Do not recognize space name " + spaceName);
        }
    }
    
    public Space createPropertySpace(String propertyType, String propertyName, int value, int houseValue, int hotelValue, 
            int siteOnlyRent, int houseRent, int hotelRent, ColorGroup colorGroup) {
        switch (propertyType) {
            case "residential":
                return new ResidentialSpace(propertyName, value, colorGroup, houseValue, hotelValue, siteOnlyRent,
                    houseRent, hotelRent,
                    Arrays.asList(playerActionFactory.createPayRentAction()),
                    Arrays.asList(playerActionFactory.createBuyPropertyAction(), playerActionFactory.createBuyHouseAction()));
            default:
                throw new IllegalArgumentException("Do not recognize propertyType " + propertyType);
        }
    }
}

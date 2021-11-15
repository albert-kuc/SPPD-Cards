package com.qa.sppd.card;

import com.qa.sppd.exceptions.InvalidClassTypeInputException;
import com.qa.sppd.exceptions.InvalidCostInputException;
import com.qa.sppd.exceptions.InvalidRarityInputException;
import com.qa.sppd.exceptions.InvalidThemeInputException;
import com.qa.sppd.persistence.domain.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CardTest {

    Card object;

    @BeforeEach
    public void setup() {
        object = new Card(1, "ManBearPig", "neutral", "tank", "legendary", 7);
    }

    @Test
    public void constructor_TEST() {
        object = new Card();

        assertTrue(object instanceof Card);
    }

    @Test
    public void constructor_wInput_TEST() {
        assertTrue(object instanceof Card);
    }

    @Test
    public void getName_TEST() {
        assertEquals("ManBearPig", object.getName());
    }

    @Test
    public void setName_TEST() {
        String tempName = "Sixth Element Randy";
        object.setName(tempName);

        assertEquals(tempName, object.getName());
    }

    @Test
    public void getTheme_TEST() {
        assertEquals("neutral", object.getTheme());
    }

    @Test
    public void setTheme_TEST() {
        String tempTheme = "fantasy";
        object.setTheme(tempTheme);

        assertEquals(tempTheme, object.getTheme());
    }

    @Test
    public void setThemeInvalid_TEST() {
        String tempTheme = "non-existing-theme";
        InvalidThemeInputException thrown = assertThrows(InvalidThemeInputException.class,
                () -> object.setTheme(tempTheme),
                "InvalidThemeInputException error was expected");

        assertEquals("Theme value not recognized (" + tempTheme + ")", thrown.getMessage());
    }

    @Test
    public void getClassType_TEST() {
        assertEquals("tank", object.getClassType());
    }

    @Test
    public void setClassType_TEST() {
        String tempClassType = "assassin";
        object.setClassType(tempClassType);

        assertEquals(tempClassType, object.getClassType());
    }

    @Test
    public void setClassTypeInvalid_TEST() {
        String tempClassType = "non-existing-classType";
        InvalidClassTypeInputException thrown = assertThrows(InvalidClassTypeInputException.class,
                () -> object.setClassType(tempClassType),
                "InvalidClassTypeInputException error was expected");

        assertEquals("ClassType value not recognized (" + tempClassType + ")", thrown.getMessage());
    }

    @Test
    public void getRarity_TEST() {
        assertEquals("legendary", object.getRarity());
    }

    @Test
    public void setRarity_TEST() {
        String tempRarity = "epic";
        object.setRarity(tempRarity);

        assertEquals(tempRarity, object.getRarity());
    }

    @Test
    public void setRarityInvalid_TEST() {
        String tempRarity = "non-existing-rarity";
        InvalidRarityInputException thrown = assertThrows(InvalidRarityInputException.class,
                () -> object.setRarity(tempRarity),
                "InvalidClassTypeInputException error was expected");

        assertEquals("Rarity value not recognized (" + tempRarity + ")", thrown.getMessage());
    }

    @Test
    public void getCost_TEST() {
        assertEquals(7, object.getCost());
    }

    @Test
    public void setCost_TEST() {
        Integer tempCost = 6;
        object.setCost(tempCost);

        assertEquals(tempCost, object.getCost());
    }

    @Test
    public void setCostInvalid_TEST() {
        Integer tempCost = 10;
        InvalidCostInputException thrown = assertThrows(InvalidCostInputException.class,
                () -> object.setCost(tempCost),
                "InvalidClassTypeInputException error was expected");

        assertEquals("Cost value not in range(1, 7). Provided value: " + tempCost, thrown.getMessage());
    }


}

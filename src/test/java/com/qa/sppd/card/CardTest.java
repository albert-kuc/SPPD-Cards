package com.qa.sppd.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardTest {

    Card object;

    @BeforeEach
    public void setup() {
        object = new Card(1L, "ManBearPig", "neutral", "tank", "legendary", 7);
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
        String tempTheme = "non-existing theme";
        object.setTheme(tempTheme);

        assertEquals("neutral", object.getTheme());
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
    public void getCost_TEST() {
        assertEquals(7, object.getCost());
    }

    @Test
    public void setCost_TEST() {
        Integer tempCost = 6;
        object.setCost(tempCost);

        assertEquals(tempCost, object.getCost());
    }


}

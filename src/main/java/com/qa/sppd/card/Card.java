package com.qa.sppd.card;

import com.qa.sppd.exceptions.InvalidClassTypeInputException;
import com.qa.sppd.exceptions.InvalidCostInputException;
import com.qa.sppd.exceptions.InvalidRarityInputException;
import com.qa.sppd.exceptions.InvalidThemeInputException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Card {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String theme;
    private String classType;
    private String rarity;
    private Integer cost;

    public Card() {
    }

    public Card(Integer id, String name, String theme, String classType, String rarity, Integer cost) {
        setId(id);
        setName(name);
        setTheme(theme);
        setClassType(classType);
        setRarity(rarity);
        setCost(cost);
    }

    public Card(String name, String theme, String classType, String rarity, Integer cost) {
        setName(name);
        setTheme(theme);
        setClassType(classType);
        setRarity(rarity);
        setCost(cost);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        String[] availableThemesArray = {"neutral", "adventures", "fantasy", "mystical", "sci-fi", "superheroes"};
        List<String> availableThemesList = new ArrayList<>(Arrays.asList(availableThemesArray));
        if (!availableThemesList.contains(theme)) throw new InvalidThemeInputException(
                "Theme value not recognized (" + theme + ")");
        this.theme = theme;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        String[] availableTypesArray = {"fighter", "assassin", "ranged", "tank", "spell", "totem", "trap"};
        List<String> availableTypesList = new ArrayList<>(Arrays.asList(availableTypesArray));
        if (!availableTypesList.contains(classType)) throw new InvalidClassTypeInputException(
                "ClassType value not recognized (" + classType + ")");
        this.classType = classType;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        String[] availableRarityArray = {"common", "rare", "epic", "legendary"};
        List<String> availableRarityList = new ArrayList<>(Arrays.asList(availableRarityArray));

        if (!availableRarityList.contains(rarity)) throw new InvalidRarityInputException(
                "Rarity value not recognized (" + rarity + ")");
        this.rarity = rarity;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        if (cost < 1 || cost > 7) throw new InvalidCostInputException(
                "Cost value not in range(1, 7). Provided value: " + cost);
//        if (cost > 0 && cost < 8) {
        this.cost = cost;
//        } else {
//            this.cost = 0;
//        }
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", theme='" + theme + '\'' +
                ", classType='" + classType + '\'' +
                ", rarity='" + rarity + '\'' +
                ", cost=" + cost +
                '}';
    }
}

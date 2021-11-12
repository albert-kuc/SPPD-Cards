package com.qa.sppd.card;

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
        if (availableThemesList.contains(theme)) {
            this.theme = theme;
        } else {
            this.theme = availableThemesArray[0];
        }
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        String[] availableTypesArray = {"fighter", "assassin", "ranged", "tank", "spell", "totem", "trap"};
        List<String> availableTypesList = new ArrayList<>(Arrays.asList(availableTypesArray));
        if (availableTypesList.contains(classType)) {
            this.classType = classType;
        } else {
            this.classType = availableTypesArray[0];
        }
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        String[] availableRarityArray = {"common", "rare", "epic", "legendary"};
        List<String> availableRarityList = new ArrayList<>(Arrays.asList(availableRarityArray));
        if (availableRarityList.contains(rarity)) {
            this.rarity = rarity;
        } else {
            this.rarity = availableRarityArray[0];
        }

    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        if (cost > 0 && cost < 8) {
            this.cost = cost;
        } else {
            this.cost = 0;
        }
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

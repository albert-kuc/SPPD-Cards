package com.qa.sppd.card;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "card")
public class CardController {

    private final List<Card> cards = new ArrayList<>();

    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello world!";
    }

    @PostMapping(path="create")
    public Card createCard(@RequestBody Card newCard) {

        this.cards.add(newCard);
        return this.cards.get(this.cards.size() - 1);
    }

    @GetMapping(path="getAll")
    public List<Card> getAllCards() {
        return this.cards;
    }

    @GetMapping(path="get/{idx}")
    public Card getCardByIndex(@PathVariable Integer idx) {
        return this.cards.get(idx);
    }

    @PutMapping(path="replace/{idx}")
    public Card replaceCard(@PathVariable Integer idx, @RequestBody Card newCard) {
        this.cards.set(idx, newCard);
        return this.cards.get(idx);
    }

    @DeleteMapping(path="remove/{idx}")
    public void removeCard(@PathVariable int idx) {
        this.cards.remove(idx);
    }
}

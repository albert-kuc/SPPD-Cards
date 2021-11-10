package com.qa.sppd.card;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Card> createCard(@RequestBody Card newCard) {

        this.cards.add(newCard);
        return new ResponseEntity<>(this.cards.get(this.cards.size() - 1), HttpStatus.CREATED);
    }

    @GetMapping(path="getAll")
    public ResponseEntity<List<Card>> getAllCards() {
        return ResponseEntity.ok(this.cards);
    }

    @GetMapping(path="get/{idx}")
    public ResponseEntity<Card> getCardByIndex(@PathVariable Integer idx) {
        return ResponseEntity.ok(this.cards.get(idx));
    }

    @PutMapping(path="replace/{idx}")
    public ResponseEntity<Card> replaceCard(@PathVariable Integer idx, @RequestBody Card newCard) {
        this.cards.set(idx, newCard);
        return new ResponseEntity<>(this.cards.get(idx), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path="remove/{idx}")
    public ResponseEntity<?> removeCard(@PathVariable int idx) {
        this.cards.remove(idx);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

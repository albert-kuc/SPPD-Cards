package com.qa.sppd.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="card")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping(path="create")
    public ResponseEntity<Card> createCard(@RequestBody Card newCard) {
        return this.cardService.createCard(newCard);
    }

    @GetMapping(path="getAll")
    public ResponseEntity<List<Card>> getAllCards() {
        return this.cardService.getAllCards();
    }

    @GetMapping(path="get/{idx}")
    public ResponseEntity<Card> getCardByIndex(@PathVariable Integer idx) {
        return this.cardService.getCardByIndex(idx);
    }

    @PutMapping(path="replace/{idx}")
    public ResponseEntity<Card> replaceCard(@PathVariable Integer idx, @RequestBody Card newCard) {
        return this.cardService.replaceCard(idx, newCard);
    }

    @DeleteMapping(path="remove/{idx}")
    public ResponseEntity<?> removeCard(@PathVariable int idx) {
        return this.cardService.removeCard(idx);
    }
}

package com.qa.sppd.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<Card>(this.cardService.createCard(newCard), HttpStatus.CREATED);
    }

    @GetMapping(path="getAll")
    public ResponseEntity<List<Card>> getAllCards() {
        return ResponseEntity.ok(this.cardService.getAllCards());
    }

    @GetMapping(path="get/{idx}")
    public ResponseEntity<Card> getCardByIndex(@PathVariable Integer idx) {
        return ResponseEntity.ok(this.cardService.getCardByIndex(idx));
    }

    @PutMapping(path="replace/{idx}")
    public ResponseEntity<Card> replaceCard(@PathVariable Integer idx, @RequestBody Card newCard) {
        return new ResponseEntity<Card>(this.cardService.replaceCard(idx, newCard), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path="remove/{idx}")
    public ResponseEntity<?> removeCard(@PathVariable int idx) {
        boolean removed = this.cardService.removeCard(idx);
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

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
    public ResponseEntity<?> getCardByIndex(@PathVariable Integer idx) {
        try {
            return ResponseEntity.ok(this.cardService.getCardByIndex(idx));
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path="replace/{idx}")
    public ResponseEntity<?> replaceCard(@PathVariable Integer idx, @RequestBody Card newCard) {
        try {
            return new ResponseEntity<Card>(this.cardService.replaceCard(idx, newCard), HttpStatus.ACCEPTED);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
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

package com.qa.sppd.card;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    private final List<Card> cards = new ArrayList<>();

    public ResponseEntity<Card> createCard(Card newCard) {

        this.cards.add(newCard);
        return new ResponseEntity<>(this.cards.get(this.cards.size() - 1), HttpStatus.CREATED);
    }

    public ResponseEntity<List<Card>> getAllCards() {
        return ResponseEntity.ok(this.cards);
    }

    public ResponseEntity<Card> getCardByIndex(Integer idx) {
        return ResponseEntity.ok(this.cards.get(idx));
    }

    public ResponseEntity<Card> replaceCard(Integer idx, Card newCard) {
        this.cards.set(idx, newCard);
        return new ResponseEntity<>(this.cards.get(idx), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> removeCard(int idx) {
        this.cards.remove(idx);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

package com.qa.sppd.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerErrorException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card createCard(Card newCard) {
        return this.cardRepository.save(newCard);
    }

    public List<Card> getAllCards() {
        return this.cardRepository.findAll();
    }

    public Card getCardByIndex(Integer idx) {
        Optional<Card> cardOptional = this.cardRepository.findById(idx);

        if (cardOptional.isPresent()) {
            return cardOptional.get();
        } else {
            throw new IndexOutOfBoundsException("No card exists at index: " + idx);
        }
    }

    public Card replaceCard(Integer idx, Card newCard) {
        Optional<Card> cardOptional = this.cardRepository.findById(idx);

        if (cardOptional.isPresent()) {
            Card existing = this.getCardByIndex(idx);

            existing.setName(newCard.getName());
            existing.setTheme(newCard.getTheme());
            existing.setClassType(newCard.getClassType());
            existing.setRarity(newCard.getRarity());
            existing.setCost(newCard.getCost());

            return this.cardRepository.save(existing);

        } else {
            throw new IndexOutOfBoundsException("No card exists at index: " + idx);
        }

    }

    public boolean removeCard(int idx) {
        this.cardRepository.deleteById(idx);
        return this.cardRepository.existsById(idx);
    }

}

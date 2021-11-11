package com.qa.sppd.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return this.cardRepository.getById(idx);
    }

    public Card replaceCard(Integer idx, Card newCard) {
        Card existing = this.getCardByIndex(idx);

        existing.setName(newCard.getName());
        existing.setTheme(newCard.getTheme());
        existing.setClassType(newCard.getClassType());
        existing.setRarity(newCard.getRarity());
        existing.setCost(newCard.getCost());

        return this.cardRepository.save(existing);
    }

    public boolean removeCard(int idx) {
        this.cardRepository.deleteById(idx);
        return this.cardRepository.existsById(idx);
    }

}

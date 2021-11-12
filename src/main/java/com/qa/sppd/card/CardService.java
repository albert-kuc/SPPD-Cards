package com.qa.sppd.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * Saves (post) a Card into the database
     * @param newCard
     * JSON with parameters to create an instance of Card
     * Requires providing name, theme, classType, rarity, cost
     * @return
     * a Card, with provided data
     */
    public Card createCard(Card newCard) {
        return this.cardRepository.save(newCard);
    }

    /**
     * Gets all Cards data from the database
     * @return
     * a List with Cards data
     */
    public List<Card> getAllCards() {
        return this.cardRepository.findAll();
    }

    /**
     * Gets a specific Card data from the database
     * @param idx
     * Card id to look-up in the database
     * @return
     * a Card with stored details
     * @throws IndexOutOfBoundsException
     * if id not found in the database
     */
    public Card getCardByIndex(Integer idx) {
        Optional<Card> cardOptional = this.cardRepository.findById(idx);

        if (cardOptional.isPresent()) {
            return cardOptional.get();
        } else {
            throw new IndexOutOfBoundsException("No card exists at index: " + idx);
        }
    }

    /**
     * Puts new Card details (overwrites) to an existing Card id
     * @param idx
     * Card id to look-up in the database
     * @param newCard
     * JSON with parameters to create an instance of Card
     * Requires providing name, theme, classType, rarity, cost
     * @return
     * a Card, with provided data
     * @throws IndexOutOfBoundsException
     * if id not found in the database
     */
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

    /**
     * Deletes instance with provided id from the database
     * @param idx
     * Card id to look-up in the database
     * @return
     * True if Card is removed, otherwise false
     */
    public boolean removeCard(int idx) {
        this.cardRepository.deleteById(idx);
        return !this.cardRepository.existsById(idx);
    }

}

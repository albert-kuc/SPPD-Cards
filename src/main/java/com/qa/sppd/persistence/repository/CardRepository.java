package com.qa.sppd.persistence.repository;

import com.qa.sppd.persistence.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    List<Card> findCardByNameContaining(String targetName);
    List<Card> findByTheme(String targetTheme);
    List<Card> findByClassType(String targetClassType);
}

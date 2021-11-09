package com.qa.main;

import com.qa.card.Card;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SppdCardsApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SppdCardsApplication.class, args);

		Card cartman = new Card(1L, "Cartman Zen", "mystical", "tank", "rare", 3);
		System.out.println(cartman);

		Card stan = new Card(2L, "Program Stan", "sci-fi", "fighter", "epic", 3);
		System.out.println(stan);
	}

}

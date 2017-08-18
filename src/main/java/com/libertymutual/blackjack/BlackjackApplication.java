package com.libertymutual.blackjack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.libertymutual.blackjack.models.Aces;

@SpringBootApplication
public class BlackjackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackjackApplication.class, args);

	}

}
	// remove this- only for display
	//	new Deck().toString();
		
	//	Hand hand = new Hand();
	//	hand.addCard(new AceCard("Diamonds"));
	//	hand.addCard(new NumberCard(3, "Clubs"));
		
	//	int[] values = hand.getValues();
	//	System.out.println("value: " + values[0]);;
	//	System.out.println("value: " + values[1]);;


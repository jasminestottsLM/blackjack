package com.libertymutual.blackjack.commands;

public class HandMath {

	public int handMath(String[] hand) {
		int aceCount = 0;
		int handValue = 0;
		
		for (String card : hand) {
			if (card != null) {
			switch (card.substring(0, 1)) {
			case "A": handValue += 11;
					  aceCount += 1;
			break;
			case "K": handValue += 10;
			break;
			case "Q": handValue += 10;
			break;
			case "J": handValue += 10;
			break;
			case "1": handValue += 10; 
			break;
			default: handValue += Integer.valueOf(card.substring(0,1));
			break;
		
			}
			}
		}
		
		while (aceCount > 0 && handValue > 21) {
			handValue -= 10;
			aceCount -= 1;
		}
		
			return handValue;
	}
}
		
	
	

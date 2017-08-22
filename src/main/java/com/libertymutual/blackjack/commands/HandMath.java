package com.libertymutual.blackjack.commands;

import java.util.Arrays;

import org.springframework.ui.Model;

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
	
	public String blarg(String inString) {
		String outString;
		if (inString == null) { 
			outString = "";
		}  else {
			outString = inString;
		}
		return outString;
	}
	
	public double result(Model model, int playerHandValue, int dealerHandValue, int bet, String[] dealerHand) {
		double result = 0;
		double walletMod;
		if (playerHandValue > 21) {
			walletMod = 0 - bet;
			result = walletMod;
			
		} else if (playerHandValue == 21 && dealerHandValue == 21) {
				walletMod = 0;
				System.out.println("Tie bet:" + bet + "line 89");
				System.out.println("Tie:" + walletMod + "line 89");
				result = walletMod;
				
		} else if (playerHandValue == 21) {
				walletMod = (1.5 * bet);
					
				System.out.println("Won = 21 bet:" + bet + "line 79");
				System.out.println("Won = 21 should be more:" + walletMod + "line 79");
				result = walletMod;
					
		} else if (dealerHandValue > 21) {
				walletMod = 2 * bet;
				System.out.println("Win dealer > 21 bet:" + bet + "line 89");
				System.out.println("Win dealer > 21 should be more:" + walletMod + "line 89");
				result = walletMod;
					
				System.out.println(walletMod);
		} else if (playerHandValue > dealerHandValue) {
				walletMod = 2* bet;
				System.out.println("won > dealer bet:" + bet + "line 99");
				System.out.println("won > dealer should be more:" + walletMod + "line 99");
				result = walletMod;
					
		} else if (playerHandValue == dealerHandValue) {
					walletMod = 0;
					result = walletMod;
					
		} else if (dealerHandValue > playerHandValue) {
					walletMod = 0 - bet;
					result = walletMod;
					
		} else { 
		}
				
		
		
		
		return result;
		
		
		
	}
}
		
	
	

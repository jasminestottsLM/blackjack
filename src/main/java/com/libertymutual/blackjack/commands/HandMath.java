package com.libertymutual.blackjack.commands;

import java.util.Arrays;

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
	
	public String result(int playerHandValue, int dealerHandValue, int wallet, int bet, boolean nobet, String[] dealerHand, String[] dealerShownResult, String[] dealerShownForHit, String[] dealerShownForStand) {
		String result = null;
		if (playerHandValue > 21) {
	// case player bust
			wallet -= bet;
	//		dealerShown = dealerHand;
	//		Arrays.toString(dealerShown);
			nobet = true;
			result = "playerbust";
			
		} else  {
			if (playerHandValue == 21 && dealerHandValue == 21) {
	// case both blackjack
//				dealerShownResult = dealerHand;
//				Arrays.toString(dealerShownResult);
				nobet = true;
				result = "tie";
				
			} else
				if (playerHandValue == 21) {
	// case player blackjack
					wallet += (1.5 * bet);
//					dealerShownResult = dealerHand;
//					Arrays.toString(dealerShownResult);
	//				Arrays.toString(dealerShown);
					nobet = true;
					result = "win";
					
				} else if (dealerHandValue > 21) {
	// case dealer bust
					wallet += (2* bet);
//					dealerShownResult = dealerHand;
//					Arrays.toString(dealerShownResult);
					nobet = true;
					result = "win";
					
					System.out.println(wallet);
				} else if (playerHandValue > dealerHandValue) {
					wallet += (2* bet);
//					dealerShownResult = dealerHand;
//					Arrays.toString(dealerShownResult);
					nobet = true;
					result = "win";
					
				} else if (playerHandValue == dealerHandValue) {
//					dealerShownResult = dealerHand;
//					Arrays.toString(dealerShownResult);
					nobet = true;
					result = "tie";
					
				} else if (dealerHandValue > playerHandValue) {
					wallet -= bet;
//					dealerShownResult = dealerHand;
//					Arrays.toString(dealerShownResult);
					nobet = true;
					result = "lose";
					
				} else { 
//					Arrays.toString(dealerShownForHit);
					result = "blackjackgame"; }
				
	//			Arrays.toString(dealerShown);
		}
		
		
		return result;
		
		
		
	}
}
		
	
	

package com.libertymutual.blackjack.controllers;

import java.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.libertymutual.blackjack.commands.DeckHandler;
import com.libertymutual.blackjack.commands.HandMath;


@Controller
@RequestMapping("/blackjackgame")
public class BlackjackController {

	
//	private Stack<Integer> betStack;
	public int bet;
	public int wallet = 500;
//	private boolean activeGame = false;
	public int currentbet;
	public boolean nobet = true;
	public String topCardValue;
	public String[] cards;
	public String[] dealerHand = new String[5];
	public String[] dealerShown;
	public String[] dealerShownResult;
	public String[] dealerShownInitial;
	public String[] dealerShownForHit;
	public String[] dealerShownForStand;
	public String[] playerHand;
	public int dealerHandCount = 0;
	public int playerHandCount = 0;
	public int deckCount = 51;
	public int dealerHandValue = 0;
	public int playerHandValue = 0;
	public String playerCardOne;
	public String playerCardTwo;
	public String playerCardThree;
	public String playerCardFour;
	public String playerCardFive;
	public String inString;
	public String result;
	
	@GetMapping("")
	public String showStartPage(Model model) {
// dealerShown and dealerShown Result are both arrays
		nobet = true;
		DeckHandler cardDeck = new DeckHandler();	
		cards = cardDeck.CreateDeck(cards);
		
//		dealerShownInitial = dealerHand;
//		dealerShown[0] = "X";

		model.addAttribute("activebet", nobet);
		model.addAttribute("bet", bet);
		model.addAttribute("hasBet", bet > 0);
		model.addAttribute("wallet", wallet);
		model.addAttribute("playerScore", playerHandValue);
		model.addAttribute("playerHand", Arrays.toString(playerHand));
		model.addAttribute("dealerHand", Arrays.toString(dealerShownInitial));
		// dealerShown and dealerShown Result are both arrays
		
		return "start";  
	}
	
	@PostMapping("/start")
	public String bet(int bet, Model model) {
		if (wallet < bet) {
			System.out.println("no money");
			result = "endgame";
		} else if (bet == 0) {
			nobet = false;
			System.out.println("no bet provided");
			result = "blackjackgame";
		} else {
			nobet = true;
			wallet -= bet;
			System.out.println("bet is " + bet);
			System.out.println("wallet is " + wallet);
			result = "blackjackgame";
		}

		String test = Arrays.toString(playerHand);
		System.out.println(test);
		
		System.out.println("activebet" + nobet);
		HandMath command = new HandMath();
		
//		dealerShown = dealerHand;
//		dealerShown[0] = "X";
		
		model.addAttribute("activebet", nobet == true);
		model.addAttribute("bet", bet);
		model.addAttribute("hasBet", bet > 0);
		model.addAttribute("wallet", wallet);
		model.addAttribute("playerScore", playerHandValue);
		model.addAttribute("playerHand", Arrays.toString(playerHand));
//		model.addAttribute("dealerHand", Arrays.toString(dealerShown);
// 	dealerShown and dealerShown Result are both arrays	
		System.out.println(result);
		return result;
	}
	
	@PostMapping("/deal")
	public String create(Model model) {
		dealerHandCount = 0;
		playerHandCount = 0;	
		if (deckCount < 4) {
			System.out.println("no more cards");
			result = "endgame";	
			
		} else {
		dealerHand = new String[5];
		playerHand = new String[5];
				
		dealerHand[dealerHandCount] = cards[deckCount];
		dealerHandCount += 1;
		deckCount -= 1;
		playerHand[playerHandCount] = cards[deckCount];
		playerHandCount += 1;
		deckCount -= 1;
		
		dealerHand[dealerHandCount] = cards[deckCount];
		dealerHandCount += 1;
		deckCount -= 1;
		playerHand[playerHandCount] = cards[deckCount];
		playerHandCount += 1;
		deckCount -= 1;
				
		HandMath command = new HandMath();
		dealerHandValue = command.handMath(dealerHand);
		playerHandValue = command.handMath(playerHand);
		
		result = "blackjackgame";
		}	
	
		
		HandMath command = new HandMath();
		
		String test = Arrays.toString(playerHand);
		System.out.println(test);
		
//	 	dealerShown and dealerShown Result are both arrays		
		dealerShownInitial = dealerHand;
//		dealerShownInitial[0] = "X";
		
		model.addAttribute("activebet", nobet == true);
		model.addAttribute("bet", bet);
		model.addAttribute("hasBet", bet > 0);
		model.addAttribute("wallet", wallet);
		model.addAttribute("playerScore", playerHandValue);
		model.addAttribute("dealerScore", dealerHandValue);
		model.addAttribute("playerHand", Arrays.toString(playerHand));
//		model.addAttribute("dealerHand", dealerShown);
//	 	dealerShown and dealerShown Result are both arrays		
		model.addAttribute("dealerHand", Arrays.toString(dealerShownInitial));
//	 	Initial is now an Array				
		System.out.println(deckCount);
		return result;
	}
	

	@PostMapping("/hit")
	public String hits(Model model) {
//	 	dealerShown is still an Array and dealerShownResult is now a String
		dealerShown = dealerHand;
		System.out.print(dealerShown);
//		System.out.print(dealerHand);
		
		HandMath command = new HandMath();
		if (deckCount < 1) {
			System.out.println("no more cards");
			result = "/endgame";
		} else if (playerHandCount > 4){
			System.out.println("player has max cards");
			result = command.result(playerHandValue, dealerHandValue, wallet, bet, nobet, dealerHand, dealerShownResult, dealerShownForHit, dealerShownForStand);
		} else {
		playerHand[playerHandCount] = cards[deckCount];
		playerHandValue = command.handMath(playerHand);
//		result = command.result(playerHandValue, dealerHandValue, wallet, bet, dealerShown);
		
		deckCount -= 1;
		playerHandCount += 1;
		
		if (playerHandValue > 21) {
			System.out.println("you lose!  BUST!");
			result = command.result(playerHandValue, dealerHandValue, wallet, bet, nobet, dealerHand, dealerShownResult, dealerShownForHit, dealerShownForStand);
//		 	dealerShown and dealerShownResult are now both Strings
		} else {
			Arrays.toString(dealerShownForHit);
			result = "blackjackgame";
 		}	
		
		if (deckCount < 1) {
			System.out.println("no more cards");
			result = "/endgame";
		} else {
		
		if (dealerHandValue < 16) {
			if (dealerHandCount > 4) {
				System.out.println("Dealer has max cards");
				result = command.result(playerHandValue, dealerHandValue, wallet, bet, nobet, dealerHand, dealerShownResult, dealerShownForHit, dealerShownForStand);
			} else {
			dealerHand[dealerHandCount] = cards[deckCount];
			dealerHandValue = command.handMath(dealerHand);
			result = command.result(playerHandValue, dealerHandValue, wallet, bet, nobet, dealerHand, dealerShownResult, dealerShownForHit, dealerShownForStand);
//		 	dealerShown and dealerShownResult are now both Strings
			deckCount -= 1;
			dealerHandCount += 1;
			}
			if (dealerHandValue > 21) {
				System.out.println("you win!  Dealer busted!");
				result = command.result(playerHandValue, dealerHandValue, wallet, bet, nobet, dealerHand, dealerShownResult, dealerShownForHit, dealerShownForStand);
//			 	dealerShown and dealerShownResult are now both Strings
			} else result = "blackjackgame";
		}
		}
// if no ifs triggered
//	 	dealerShown is still an Array and dealerShownResult is now a String
		}
		
		System.out.println("dealer hand");
		for  ( String dealerCard : dealerHand) {	
			System.out.println(dealerCard);
		}
	
		System.out.println(dealerHandValue);
		System.out.println("player hand");
		for  ( String playerCard : playerHand) {	
			System.out.println(playerCard);
		}
		System.out.println(playerHandValue);
		
//		dealerHandValue = cardDeck.handMath(dealerHand);
//		playerHandValue = cardDeck.handMath(playerHand);
		
		System.out.println(deckCount);
		
		dealerShown = dealerHand;
//		dealerShownInitial[0] = "W";
		
		model.addAttribute("activebet", nobet == true);
		model.addAttribute("bet", bet);
		model.addAttribute("hasBet", bet > 0);
		model.addAttribute("wallet", wallet);
		model.addAttribute("playerScore", playerHandValue);
		model.addAttribute("dealerScore", dealerHandValue);
		model.addAttribute("playerHand", Arrays.toString(playerHand));
		model.addAttribute("dealerHand", "test");
//		model.addAttribute("dealerHand", dealerShownResult);
//		model.addAttribute("dealerHand", Arrays.toString(dealerShown));
		
		return result;
		
	}

	@PostMapping("/stand")
	public String stand(Model model) {
//	 	dealerShown is still an Array and dealerShownResult is now a String
		
		HandMath command = new HandMath();
		
			
		while (dealerHandValue < 16) {
			
			if (deckCount < 1) {
				result = "endgame";
				System.out.println("out of cards");
			} else {
				while (dealerHandValue < 16) {
			dealerHand[dealerHandCount] = cards[deckCount];
			dealerHandValue = command.handMath(dealerHand);
			result = command.result(playerHandValue, dealerHandValue, wallet, bet, nobet, dealerHand, dealerShownResult, dealerShownForHit, dealerShownForStand);
			deckCount -= 1;
			dealerHandCount += 1;
				}
			if (dealerHandValue > 21) {
				System.out.println("you win!  Dealer busted!");
				result = "win";
//				result = command.result(playerHandValue, dealerHandValue, wallet, bet, nobet, dealerHand, dealerShownResult, dealerShownForHit, dealerShownForStand);
//			 	both dealer views are now strings
			} else {
				result = command.result(playerHandValue, dealerHandValue, wallet, bet, nobet, dealerHand, dealerShownResult, dealerShownForHit, dealerShownForStand);	
			}
			
			
		}
		}
		
//		result = command.result(playerHandValue, dealerHandValue, wallet, bet, nobet, dealerHand, dealerShownResult, dealerShownForHit, dealerShownForStand);
//		System.out.println("dealer hand");
//		for  ( String dealerCard : dealerHand) {	
//			System.out.println(dealerCard);
//		}
	
		System.out.println(dealerHandValue);
		System.out.println("player hand");
		for  ( String playerCard : playerHand) {	
			System.out.println(playerCard);
		}
		System.out.println(playerHandValue);
	
		System.out.println(deckCount);
		
//		dealerShown = dealerHand;
//		dealerShown[0] = "Z";
		
//		model.addAttribute("activebet", nobet == true);
		model.addAttribute("bet", bet);
//		model.addAttribute("hasBet", bet > 0);
		model.addAttribute("wallet", wallet);
		model.addAttribute("playerScore", playerHandValue);
		model.addAttribute("dealerScore", dealerHandValue);
		model.addAttribute("playerHand", Arrays.toString(playerHand));
//		model.addAttribute("dealerHand", Arrays.toString(dealerShownResult));
//		model.addAttribute("dealerHand", Arrays.toString(dealerShown));
		
		System.out.println("ok to here");
		return result;
		
	}
	
	
}
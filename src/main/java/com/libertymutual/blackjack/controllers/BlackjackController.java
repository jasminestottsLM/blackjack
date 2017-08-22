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
	public double wallet = 500;
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
	public double result;
	public String pageName; 
	
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
	public String betting(int betcollector, Model model) {
		bet = betcollector;
		if (wallet < bet) {
			System.out.println("no money");
			pageName = "endgame";
		} else if (bet == 0) {
			nobet = false;
			System.out.println("no bet provided");
			pageName = "blackjackgame";
		} else {
			nobet = true;
			wallet -= bet;
			System.out.println("bet is " + bet);
			System.out.println("wallet is " + wallet);
			pageName = "blackjackgame";
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
		return pageName;
	}
	
	@PostMapping("/deal")
	public String create(Model model) {
		dealerHandCount = 0;
		playerHandCount = 0;	
		if (deckCount < 4) {
			System.out.println("no more cards");
			pageName = "endgame";	
			
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
		
		pageName = "blackjackgame";
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
		return pageName;
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
			pageName = "/endgame";
		} else if (playerHandCount > 4){
			System.out.println("player has max cards");
			result = command.result(model, playerHandValue, dealerHandValue, bet, nobet, dealerHand, dealerShownResult, dealerShownForHit, dealerShownForStand);
			wallet = result + wallet;
			if (result == -bet) {
				pageName = "lose"; }
			else if (result == bet) {
				pageName = "win"; }
			else if (result == (1.5 * bet)) {
				pageName = "win"; }
			else if (result == (2 * bet)) {
				pageName = "win"; }
			else if (result == 0) {
				pageName = "tie";
			}
			
		} else {
		playerHand[playerHandCount] = cards[deckCount];
		playerHandValue = command.handMath(playerHand);
//		result = command.result(playerHandValue, dealerHandValue, wallet, bet, dealerShown);
		
		deckCount -= 1;
		playerHandCount += 1;
		
		if (playerHandValue > 21) {
			System.out.println("you lose!  BUST!");
			result = command.result(model, playerHandValue, dealerHandValue, bet, nobet, dealerHand, dealerShownResult, dealerShownForHit, dealerShownForStand);
			wallet = result + wallet;
			if (result == -bet) {
				pageName = "lose"; }
			else if (result == bet) {
				pageName = "win"; }
			else if (result == (1.5 * bet)) {
				pageName = "win"; }
			else if (result == (2 * bet)) {
				pageName = "win"; }
			else if (result == 0) {
				pageName = "tie";
			}
		
//		 	dealerShown and dealerShownResult are now both Strings
		} else {
			Arrays.toString(dealerShownForHit);
			pageName = "blackjackgame";
 		}	
		
		if (deckCount < 1) {
			System.out.println("no more cards");
			pageName = "/endgame";
		} else {
		
		if (dealerHandValue < 16) {
			if (dealerHandCount > 4) {
				System.out.println("Dealer has max cards");
				result = command.result(model, playerHandValue, dealerHandValue, bet, nobet, dealerHand, dealerShownResult, dealerShownForHit, dealerShownForStand);
				wallet = result + wallet;
				if (result == -bet) {
					pageName = "lose"; }
				else if (result == bet) {
					pageName = "win"; }
				else if (result == (1.5 * bet)) {
					pageName = "win"; }
				else if (result == (2 * bet)) {
					pageName = "win"; }
				else if (result == 0) {
					pageName = "tie";
				}
			
			} else {
			dealerHand[dealerHandCount] = cards[deckCount];
			dealerHandValue = command.handMath(dealerHand);
			result = command.result(model, playerHandValue, dealerHandValue, bet, nobet, dealerHand, dealerShownResult, dealerShownForHit, dealerShownForStand);
//		 	dealerShown and dealerShownResult are now both Strings
			deckCount -= 1;
			dealerHandCount += 1;
			}
			if (dealerHandValue > 21) {
				System.out.println("you win!  Dealer busted!");
				result = command.result(model, playerHandValue, dealerHandValue, bet, nobet, dealerHand, dealerShownResult, dealerShownForHit, dealerShownForStand);
				wallet = result + wallet;
				if (result == -bet) {
					pageName = "lose"; }
				else if (result == bet) {
					pageName = "win"; }
				else if (result == (1.5 * bet)) {
					pageName = "win"; }
				else if (result == (2 * bet)) {
					pageName = "win"; }
				else if (result == 0) {
					pageName = "tie";
				}
			
//			 	dealerShown and dealerShownResult are now both Strings
			} else pageName = "blackjackgame";
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
		
		System.out.println("from hit: wallet = " + wallet);
		return pageName;
		
	}

	@PostMapping("/stand")
	public String stand(Model model) {
//	 	dealerShown is still an Array and dealerShownResult is now a String
		
		HandMath command = new HandMath();
		HandMath math = new HandMath();
		
			
		while (dealerHandValue < 16) {
			
			if (deckCount < 1) {
				pageName = "endgame";
				System.out.println("out of cards");
			} else {
				while (dealerHandValue < 16) {
			dealerHand[dealerHandCount] = cards[deckCount];
			dealerHandValue = command.handMath(dealerHand);
			result = math.result(model, playerHandValue, dealerHandValue, bet, nobet, dealerHand, dealerShownResult, dealerShownForHit, dealerShownForStand);
			deckCount -= 1;
			dealerHandCount += 1;
				}
			if (dealerHandValue > 21) {
				System.out.println("you win!  Dealer busted!");
				pageName = "win";
//				result = command.result(playerHandValue, dealerHandValue, wallet, bet, nobet, dealerHand, dealerShownResult, dealerShownForHit, dealerShownForStand);
//			 	both dealer views are now strings
			} else {
//				result = math.result(model, playerHandValue, dealerHandValue, wallet, bet, nobet, dealerHand, dealerShownResult, dealerShownForHit, dealerShownForStand);	
			}
			
			
		}
		}
		System.out.println("trigger from stand" + bet);
		result = math.result(model, playerHandValue, dealerHandValue, bet, nobet, dealerHand, dealerShownResult, dealerShownForHit, dealerShownForStand);
			wallet = result + wallet;
			if (result == -bet) {
				pageName = "lose"; }
			else if (result == bet) {
				pageName = "win"; }
			else if (result == (1.5 * bet)) {
				pageName = "win"; }
			else if (result == (2 * bet)) {
				pageName = "win"; }
			else if (result == 0) {
				pageName = "tie";
			}
		
//		result = command.result(playerHandValue, dealerHandValue, wallet, bet, nobet, dealerHand, dealerShownResult, dealerShownForHit, dealerShownForStand);
//		System.out.println("dealer hand");
//		for  ( String dealerCard : dealerHand) {	
//			System.out.println(dealerCard);
//		}
	
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
		
		System.out.println("from stand: wallet = " + wallet);
		return pageName;
		
	}
	
	
}
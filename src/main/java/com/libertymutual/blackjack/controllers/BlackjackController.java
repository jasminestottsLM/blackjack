package com.libertymutual.blackjack.controllers;

import java.util.Stack;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.libertymutual.blackjack.commands.BetCommand;
import com.libertymutual.blackjack.commands.Card;
import com.libertymutual.blackjack.commands.DealCommand;
import com.libertymutual.blackjack.commands.DealerHand;
import com.libertymutual.blackjack.commands.DeckHandler;
import com.libertymutual.blackjack.commands.Hand;
import com.libertymutual.blackjack.commands.HandMath;
import com.libertymutual.blackjack.commands.Hits;


@Controller
@RequestMapping("/blackjackgame")
public class BlackjackController {

	
//	private Stack<Integer> betStack;
	public int bet;
	public int wallet = 500;
	private boolean activeGame = false;
	public int currentbet;
	public String topCardValue;
	public String[] cards;
	public String[] dealerHand;
	public String[] playerHand;
	public int dealerHandCount = 0;
	public int playerHandCount = 0;
	public int deckCount = 51;
	public int dealerHandValue = 0;
	public int playerHandValue = 0;
	
//	public BlackjackController() {
//		betStack = new Stack<Integer>();
//	}
	
	@GetMapping("")
	public String showStartPage(Model model) {
//		model.addAttribute("bets", betStack);
		model.addAttribute("bet", bet);
//		model.addAttribute("topCard", cards);
//		model.addAttribute("hasBet", betStack.size() >0);
		model.addAttribute("hasBet", bet > 0);
//		model.addAttribute("active", activeGame = true);
//		model.addAttribute("inactive", activeGame = false);
//		model.addAttribute("dealerHand", dealerHand);
		DeckHandler cardDeck = new DeckHandler();
		cards = cardDeck.CreateDeck(cards);
	    return "blackjackgame";  
	}
	
	@PostMapping("/start")
//	public String pushValue(int bet) {
//		BetCommand command = new BetCommand(betStack);	
//		command.wager(bet);
	public String bet(int bet, Model model) {
		if (wallet < bet) {
			System.out.println("no money");
		} else {
		wallet -= bet;
		System.out.println("bet is " + bet);
		System.out.println("wallet is " + wallet);
		}
		model.addAttribute("bet", bet);
		return "redirect:/blackjackgame";
	}
	
	
	
	 
//	public void createDeck(Stack<String> cardStack) {
//		CreateDeck command = new CreateDeck(cardStack);
//		command.create(cardStack);
//	}
	
//	public void shuffle(Stack<String> cardStack) {
//		CreateDeck command = new CreateDeck(cardStack);
//		command.shuffle();
//	}
	
	@PostMapping("/deal")
	public String create() {
		dealerHandCount = 0;
		playerHandCount = 0;	
		if (deckCount < 4) {
			System.out.println("no more cards");
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
		}	
		
		System.out.println(deckCount);
		return "redirect:/blackjackgame";
	}
	

	@PostMapping("/hit")
	public String hits() {
		HandMath command = new HandMath();
		if (deckCount < 1) {
			System.out.println("no more cards");
		} else {
		playerHand[playerHandCount] = cards[deckCount];
		
		
		playerHandValue = command.handMath(playerHand);
		
		deckCount -= 1;
		playerHandCount += 1;
		
		if (playerHandValue > 21) {
			System.out.println("you lose!  BUST!");
		}
		}
		
		if (deckCount < 1) {
			System.out.println("no more cards");
		} else {
		
		if (dealerHandValue < 16) {
			dealerHand[dealerHandCount] = cards[deckCount];
			dealerHandValue = command.handMath(dealerHand);
			deckCount -= 1;
			dealerHandCount += 1;
			if (dealerHandValue > 21) {
				System.out.println("you win!  Dealer busted!");
			}
			
		}
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
		return "redirect:/blackjackgame";
	}

	@PostMapping("/stand")
	public String stand() {
		HandMath command = new HandMath();
		
		while (dealerHandValue < 16) {
			if (deckCount < 1) {
				System.out.println("out of cards");
			} else {
			dealerHand[dealerHandCount] = cards[deckCount];
			dealerHandValue = command.handMath(dealerHand);
			deckCount -= 1;
			dealerHandCount += 1;
			if (dealerHandValue > 21) {
				System.out.println("you win!  Dealer busted!");
			}
			}
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
	
		System.out.println(deckCount);
		return "redirect:/blackjackgame";
	}
	
	
//	public void bet(int currentBet) {
//			currentBet = bet;

//			betStack.push(bet);
//		} 	 
	
		
//	@PostMapping("/blackjack/play")
//	public ModelAndView play() {
//		Hand hand = new Hand();		
//		ModelAndView mv = new ModelAndView("game");
//	public String play(int bet, Model model) {
//		model.addAttribute("bet", currentBet);
//		model.addAttribute("hand", hand);
//		return "game";
//	 }
		
	
}

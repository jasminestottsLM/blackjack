package com.libertymutual.blackjack.commands;

import java.util.Collections;
import java.util.Stack;

public class DealCommand {
	
	private Card[] cards;
	private Stack<String> cardStack;
	private Stack<String> dealerStack;
	private String topCardValue;
	
	public DealCommand(Stack<String> cardStack) {
		this.cardStack = cardStack;
	}

	public void create(Stack<String> cardStack) {
		String[] suits = new String[] 
				{"clubs", "diamonds", "hearts", "spades"};
		int i = 0;
		
		int[] values = new int[] 
				{2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		String[] faces= new String[] 
				{"J", "Q", "K", "A"};
		
		Stack<String> cards = new Stack<String>(); 
		
		for (String suit: suits) {
			
			for (int value: values) {
				cards.push(suit + value);
				
				}
			for (String face: faces) {
				cards.push(suit + face);
				}
			}
		
	}

	public void shuffle(Stack<String> cardStack) {
		Collections.shuffle(cardStack);
	}	
	
	public String dealer(Stack<String> cardStack) {
		topCardValue = cardStack.pop();
		return topCardValue;
}
	
//	public String deal(String topCard) {
//		topCard = cardStack.pop();
//		return topCard; 
//	}
	
	
	
}

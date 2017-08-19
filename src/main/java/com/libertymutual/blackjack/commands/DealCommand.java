package com.libertymutual.blackjack.commands;

import java.util.Stack;

public class DealCommand {
	
	public String topCard;
	public Stack<String> cardStack;
	
	public DealCommand(Stack<String> cardStack) {
		this.cardStack = cardStack;
	}
	
	public String deal(String topCard) {
		topCard = cardStack.pop();
		return topCard; 
	}
	
}

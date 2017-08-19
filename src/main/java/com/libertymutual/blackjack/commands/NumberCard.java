package com.libertymutual.blackjack.commands;

public class NumberCard implements Card {

	private int value;
	private String suit;
	private String displayCard;
	
	public NumberCard(int value, String suit) {
		this.value = value;
		this.suit = suit;
	}
	
	@Override 
	public String toString() {
		return this.displayCard + this.getSuit();
	}
	
	@Override
	public String getSuit() {
		return suit;
	}

	@Override
	public String getDisplay() {
		return String.valueOf(value);
	}

	@Override
	public int[] getValues() {
		return new int[] { value, value };
	}

}

package com.libertymutual.blackjack.commands;

public class FaceCard implements Card {

	private String displayCard;
	private String suit;
	
	public FaceCard(String displayCard, String suit) {
		this.suit = suit;
		this.displayCard = displayCard;
	}
	
	@Override
	public String toString() {
		return this.getDisplay() + this.getSuit();
	}
	
	@Override
	public String getDisplay() {
		return displayCard;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public int[] getValues() {
		return new int[] { 10, 10 };
	}
	
}

package com.libertymutual.blackjack.commands;

public class Aces implements Card {

	private String suit;
	
	public Aces(String suit) {
		this.suit = suit;
	}
	
	@Override
	public String toString() {
		return this.getDisplay() + this.getSuit();
	}
	
	@Override
	public String getSuit() {
		return suit;
	}
	
	@Override
	public String getDisplay() {
		return "A";
	}
	
	@Override
	public int[] getValues() {
		return new int[] { 1, 11 };
	}
}

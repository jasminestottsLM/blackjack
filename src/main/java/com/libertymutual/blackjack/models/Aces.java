package com.libertymutual.blackjack.models;

public class Aces implements Card{

	private String suit;
	
	public Aces(String suit) {
		this.suit = suit;

// left as example, update all of this!		
		
	}
	
	@Override
	public String toString() {
		return this.getCardDisplay() + this.getSuit();
	}
	
	public String getSuit() {
		return suit;
	}
	
	public String getCardDisplay() {
		return "A";
	}
	
	public int[] getValue() {
		return new int[] {1, 11};
	}
	
}

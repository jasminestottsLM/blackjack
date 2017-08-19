package com.libertymutual.blackjack.commands;

import java.util.Collections;
import java.util.Stack;

public class CreateDeck {

		private Card[] cards;
		private int currentCardIndex;
		public Stack<String> cardStack;
		
		public void Deck() {
		
//		public static void main(String[] args) {
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
		
		public void shuffle() {
			Collections.shuffle(cardStack);
		}
			
			public String getCard() {
				if (currentCardIndex >= cards.length) {
					return null;
				}
				
				return cardStack.pop();
				
			}
//test only			
//			System.out.println(cards);
}
		
		

		

// test
//		System.out.println(cards);
	
//		
			


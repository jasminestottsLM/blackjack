package com.libertymutual.blackjack.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class DeckHandler {

		private String[] cards;
		private int currentCardIndex;
		
		public String[] CreateDeck(String[] cards) { 
			cards = new String[52];

			
			int i = 0;
			
			String[] suits = {"clubs", "diamonds", "hearts", "spades"};
			
			String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10"};
			
			String[] faces= {"J", "Q", "K", "A"}; 
			
			
			for (String suit: suits) {
				
				for (String value: values) {
					cards[i] = value + suit;
					i += 1;
					}
				
				for (String face: faces) {
					cards[i] = face + suit;
					i += 1;
					}
					
				
				}
			
			Collections.shuffle(Arrays.asList(cards));
			
			for (String card: cards) {
			
			}
					
			return cards;
		}
		

}

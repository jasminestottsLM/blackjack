package com.libertymutual.blackjack.commands;

import java.util.Collections;
import java.util.Stack;

public class CreateDeck {

		public static void main(String[] args) {
			String[] suits = {
					"clubs", "diamonds", "hearts", "spades"
			};
			
			int[] values = {
					2, 3, 4, 5, 6, 7, 8, 9, 10 
			};
			
			String[] faces= {
					"J", "Q", "K", "A"
			};
			
			
			Stack<String> cards = new Stack<String>(); 
			
			for (String suit: suits) {
				for (int value: values) {
					cards.push(suit + value);
					
					}
				for (String face: faces) {
					cards.push(suit + face);
					}
				}
			
			
			System.out.println(cards);
		}
		
		
}
		
//Collections.shuffle(cards);
// test
//		System.out.println(cards);
	
//		
			


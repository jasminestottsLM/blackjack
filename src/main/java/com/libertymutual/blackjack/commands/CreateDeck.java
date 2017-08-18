package com.libertymutual.blackjack.commands;

import java.util.Objects;
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
		
		
		Stack<String> cards = new Stack(); 
		
		for (String suit: suits) {
			for (int value: values) {
				cards.push(suit + value);
				
				}
			for (String face: faces) {
				cards.push(suit + face);
				}
			}

		System.out.println(cards);
		
// testing arrays
//		for (String suit: suits) {
//			for (int value: values) {
//				System.out.println(suit + value);
//			}
//			for (String face: faces) {
//				System.out.println(suit + face);
//			}
//		}
		
		
			
	}
	
}

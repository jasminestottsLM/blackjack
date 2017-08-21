package com.libertymutual.blackjack.commands;

import java.util.Stack;

public class BetCommand {
	
	private Stack<Integer> betStack;
	private boolean activeGame = false;
	
	public BetCommand(Stack<Integer> betStack) {
		this.betStack = betStack;
	}

	public void wager(int bet) {
		betStack.push(bet);
		activeGame = true;
	}
	
}
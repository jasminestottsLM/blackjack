package com.libertymutual.blackjack.controllers;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.libertymutual.blackjack.commands.CreateDeck;


@Controller
public class BlackjackController {

	
	@GetMapping("/")
	public String showStartPage() {
	    return "start";  
	}
	
	@PostMapping("/blackjack/bet")
	public String betValue(int bet) {
        return "deal";		
	}
	

	
}

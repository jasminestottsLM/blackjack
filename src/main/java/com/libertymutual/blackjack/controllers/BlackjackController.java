package com.libertymutual.blackjack.controllers;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.libertymutual.blackjack.commands.BetCommand;


@Controller
public class BlackjackController {

	private Stack<Double> betStack;
	public double currentbet;

	public BlackjackController() {
		betStack = new Stack<Double>();
	}
	
	@GetMapping("")
	public String showStartPage() {
	    return "start";  
	}
	
	@PostMapping("/blackjack/bet")
	public String getBet(double bet, Model model) {
		model.addAttribute("bet", bet);
		return("play");
	}
	
}

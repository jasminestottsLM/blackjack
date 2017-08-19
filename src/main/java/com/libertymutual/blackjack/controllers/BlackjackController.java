package com.libertymutual.blackjack.controllers;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.libertymutual.blackjack.commands.BetCommand;
import com.libertymutual.blackjack.commands.Card;
import com.libertymutual.blackjack.commands.DealCommand;
import com.libertymutual.blackjack.commands.Hand;


@Controller
public class BlackjackController {

	private Stack<Double> betStack;
	public double currentbet;
	public String topCard;
	public Stack<String> cardStack;

	public BlackjackController() {
		betStack = new Stack<Double>();
	}
	
	@GetMapping("")
	public String showStartPage() {
	    return "start";  
	}
	
	@PostMapping("/blackjack/bet")
	public String bet(double bet, Model model) {
		model.addAttribute("bet", bet);
		return("deal");
	}
	
	@PostMapping("/blackjack/play")
	public ModelAndView play() {
		Hand hand = new Hand();		
		ModelAndView mv = new ModelAndView("game");
		mv.addObject("hand", hand);
		return mv;
	 }
		
	
}

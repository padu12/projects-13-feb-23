package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FirstController {
	
	@GetMapping("/hello")
	public String helloPage(@RequestParam(value= "name", required=false) String name,
							@RequestParam(value="surname", required=false) String surname,
							Model model) {
		
//		System.out.println("Hello, "+ name + " " + surname);
		model.addAttribute("message", "Hello, "+ name + " " + surname);
		
		return "first/hello";
	}
	
	@GetMapping("/goodbye")
	public String goodbyePage() {
		return "first/goodbye";
	}
	
	@GetMapping("/calculator")
	public String calculatorResult( @RequestParam(value= "a") int a,
									@RequestParam(value= "b") int b,
									@RequestParam(value= "action") String action,
									Model model) {
//		int integer = a*b;
//		model.addAttribute("int", a*b);
//		if(action=="multiplication") {
//			model.addAttribute("int", a*b);
//		}
//		else if (action=="addition") {
//			model.addAttribute("int", a+b);
//		}
//		else if (action=="substraction") {
//			model.addAttribute("int", a-b);
//		}
//		else if (action=="division") {
//			model.addAttribute("int", a/b);
//		}
		switch(action) {
		case "multiplication":
			model.addAttribute("int", a*b);
			break;
		case "addition":
			model.addAttribute("int", a+b);
			break;
		case "substraction":
			model.addAttribute("int", a-b);
			break;
		case "division":
			model.addAttribute("int", a/b);
			break;
		}
		// ?a=5&b=5&action=multiplication
		return "first/calculator_result";
	}
	
}

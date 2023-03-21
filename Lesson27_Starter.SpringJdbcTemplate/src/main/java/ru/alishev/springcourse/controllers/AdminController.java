package ru.alishev.springcourse.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Person;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private final PersonDAO personDAO;

	@Autowired
	public AdminController(PersonDAO personDAO) {
		super();
		this.personDAO = personDAO;
	}
	
	@GetMapping()
	public String adminPage(HttpServletResponse response, Model model, @ModelAttribute("person") Person person) {
	    response.setCharacterEncoding("UTF-8");
		model.addAttribute("people", personDAO.index());
		
		return "adminPage";
	}
	
	@PatchMapping("/add")
	public String makeAdmin(@ModelAttribute("person") Person person) {
		System.out.println(person.getId());
		
		return "redirect:/people";
	}
	
}

package ru.alishev.springcourse.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.alishev.springcourse.dao.BookDAO;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

@Controller
@RequestMapping("/library")
public class BookController {
	private final BookDAO bookDAO;
	private final PersonDAO personDAO;

	@Autowired
	public BookController(BookDAO bookDAO, PersonDAO personDAO) {
		super();
		this.bookDAO = bookDAO;
		this.personDAO = personDAO;
	}
	
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("library", bookDAO.index());
		return "library/index";
	}
	
	@GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "library/new";
    }
	
	@PostMapping()
	public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "library/new";
		
		bookDAO.save(book);
		return "redirect:/library";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
		model.addAttribute("book", bookDAO.show(id));
		model.addAttribute("people", personDAO.index());
		model.addAttribute("person1", bookDAO.getAuthor(id));
		return "library/show";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		model.addAttribute("book", bookDAO.show(id));
		return "library/edit";
	}
	
	@PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "library/edit";

        bookDAO.update(id, book);
        return "redirect:/library";
    }
	
	@DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/library";
    }
    
	@PatchMapping("/{id}/set")
	public String set(@PathVariable("id") int id, @ModelAttribute("person") Person person,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors())
			return "library/show";
		
		bookDAO.set(id, person);
//		model.addAttribute("book", bookDAO.show(id));
//		model.addAttribute("people", personDAO.index());
//		model.addAttribute("person1", bookDAO.getAuthor(id));
		return "redirect:/library";
	}
	
	@PatchMapping("/{id}/set-free")
	public String setFree(@PathVariable("id") int id, Model model) {
		bookDAO.setFree(id);
//		model.addAttribute("book", bookDAO.show(id));
//		model.addAttribute("people", personDAO.index());
//		model.addAttribute("person1", bookDAO.getAuthor(id));
		return "redirect:/library";
	}
}

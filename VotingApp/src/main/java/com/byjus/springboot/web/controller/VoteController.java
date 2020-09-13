package com.byjus.springboot.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.byjus.springboot.web.model.Vote;
import com.byjus.springboot.web.service.VoteRepository;
import com.byjus.springboot.web.service.VoteService;

@Controller
public class VoteController {
	
	@Autowired
	VoteRepository repository;
	@Autowired
	VoteService service;

	private List<Vote> graphData ;

	@RequestMapping(value = "/list-vote", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		List<Vote> list = repository.findByUser("admin");
		model.put("votes", service.retrieveVotes(list));
	//	model.put("votes", list);
	
		return "list-vote";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		System.out.println(principal.toString());
		return principal.toString();
	}

	public List<Vote> getGraphData() {
		return graphData;
	}

	public void setGraphData(List<Vote> list) {
		this.graphData = list;
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		return "todo";
	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {

	//	repository.deleteByTitleId(titleid);
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/view-vote", method = RequestMethod.GET)
	public String showPollPage(@RequestParam String title, ModelMap model) {
		List<Vote> list = repository.findByTitle(title);
		model.put("vote", list);
		model.put("title", title);
		setGraphData(list);
		return "list-poll";
	}


	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Vote todo,
			BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}
	repository.save(todo);
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Vote todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}

		repository.save(todo);
		return "redirect:/list-todos";
	}
	
    @GetMapping("/get-data")
    public ResponseEntity<?> getPieChart() {
         
        return new ResponseEntity<>(getGraphData(), HttpStatus.OK);
    }
    
	@RequestMapping(value = "/add-vote/{optiondropdown}", method = RequestMethod.GET)
	public String showAddVotePage(@PathVariable(value="optiondropdown") Long id ,ModelMap model) {
		System.out.println(id);
		Vote vote = repository.findById(id).get();
		System.out.println(vote);
		vote.setCount(vote.getCount()+1);
		
		repository.save(vote);
		System.out.println("saved");
		return "list-poll";
	}
}

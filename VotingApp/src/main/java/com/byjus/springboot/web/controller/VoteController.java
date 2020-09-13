package com.byjus.springboot.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.byjus.springboot.web.EnvironmentConfigurationLogger;
import com.byjus.springboot.web.model.Vote;
import com.byjus.springboot.web.service.VoteRepository;
import com.byjus.springboot.web.service.VoteService;

@Controller
public class VoteController {

	@Autowired
	VoteRepository repository;
	@Autowired
	VoteService service;

	private List<Vote> graphData;

	private static final Logger LOG = LoggerFactory.getLogger(VoteController.class);

	@RequestMapping(value = "/list-vote", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		List<Vote> list = repository.findAll();
		model.put("votes", service.retrieveVotes(list));
		// model.put("votes", list);

		// model.put("votes", list);

		return "list-vote";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

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

	@RequestMapping(value = "/add-poll", method = RequestMethod.GET)
	public String showAddTodoPage(@RequestParam("title") String title, @RequestParam("option") String option) {
		//int titleid = repository.getMaxTitleId();
		String[] options = option.split(",");
		for(String op : options) {
			Vote vote = new Vote(0,title,op,0,"admin");
			repository.save(vote);
			System.out.println("saved");
		}
		
		return "redirect:list-vote";
	}

	@RequestMapping(value = "/delete-vote", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam String title) {
		List<Vote> list = repository.findByTitle(title);
		for (Vote v : list) {
			repository.delete(v);
			System.out.println("deleted");
		}
		return "redirect:/list-vote";
	}

	@RequestMapping(value = "/view-vote", method = RequestMethod.GET)
	public String showPollPage(@RequestParam String title, ModelMap model) {
		List<Vote> list = repository.findByTitle(title);
		model.put("vote", list);
		model.put("title", title);
		setGraphData(list);
		return "list-poll";
	}

	@GetMapping("/get-data")
	public ResponseEntity<?> getPieChart() {

		return new ResponseEntity<>(getGraphData(), HttpStatus.OK);
	}

	@RequestMapping(value = "/add-vote", method = RequestMethod.GET)
	public String showAddVotePage(@RequestParam("optiondropdown") Long id, ModelMap model) {
		Vote vote = repository.findById(id).get();
		vote.setCount(vote.getCount() + 1);

		repository.save(vote);
		return "redirect:/view-vote?title=" + vote.getTitle();
	}
}

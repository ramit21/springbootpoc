package com.practice.controller;

import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ManagedResource
public class HomePageRestController {

	@RequestMapping(value = "/")
	public String welcome(Model model) {
		model.addAttribute("word", "reservation");
		return "welcome";
	}

}

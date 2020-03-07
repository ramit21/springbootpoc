package com.practice.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ManagedResource
public class HomePageRestController {

	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@RequestMapping(value = "/")
	public String welcome(Model model) {
		model.addAttribute("word", "reservation");
		return "welcome";
	}
	
	@RequestMapping(value = "/greet")
	@ResponseBody
	public String greet(
		@RequestHeader(value="Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("greet.message", null, locale);
	}
}

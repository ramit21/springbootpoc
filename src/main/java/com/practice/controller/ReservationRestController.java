package com.practice.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.practice.jpa.ReservationRepository;
import com.practice.model.Reservation;
import com.practice.model.ReservationModel;

@Controller
@RequestMapping("/reserve")
@ManagedResource
public class ReservationRestController {

	@Autowired
	private ReservationRepository repo;

	// http://localhost:8090/reserve/all
	@ManagedOperation(description = "Find all reservations")
	@ResponseBody
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Reservation> reservations() {
		return repo.findAll();
	}

	@RequestMapping(value = "/")
	public String welcome(Model model) {
		return "reservation";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Boolean saveReservation(@RequestBody ReservationModel model) {
		System.out.println("Name : "+model.getName());
		repo.save(new Reservation(model.getName()));
		return true;
	}

	@RequestMapping("/findByName/{name}")
	public Collection<Reservation> findReservationByName(@PathVariable String name) {
		return repo.findByReservationName(name);
	}

}

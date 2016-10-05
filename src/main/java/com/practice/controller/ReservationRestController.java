package com.practice.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.practice.model.Reservation;
import com.practice.model.ReservationModel;
import com.practice.service.ReservationService;

@Controller
@RequestMapping("/reserve")
public class ReservationRestController {

	@Autowired
	@Qualifier("reservationService")
	private ReservationService service;

	// http://localhost:8090/reserve/all
	@ResponseBody
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Reservation> reservations() {
		return service.findAllReservations();
	}

	@RequestMapping(value = "/")
	public String welcome(Model model) {
		return "reservation";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Boolean saveReservation(@RequestBody ReservationModel model) {
		System.out.println("Name : "+model.getName());
		return service.saveReservation(model.getName());
	}

	@RequestMapping("/findByName/{name}")
	public Collection<Reservation> findReservationByName(@PathVariable String name) {
		return service.findReservationByName(name);
	}

}

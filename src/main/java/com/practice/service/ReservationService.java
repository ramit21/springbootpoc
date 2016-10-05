package com.practice.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import com.practice.jpa.ReservationRepository;
import com.practice.model.Reservation;

@Service("reservationService")
@ManagedResource
public class ReservationService {

	@Autowired
	private ReservationRepository repo;

	@ManagedOperation(description = "Find all reservations")
	public Collection<Reservation> findAllReservations() {
		return repo.findAll();
	}

	public Boolean saveReservation(String name) {
		repo.save(new Reservation(name));
		return true;
	}

	public Collection<Reservation> findReservationByName(String name) {
		return repo.findByReservationName(name);
	}

}

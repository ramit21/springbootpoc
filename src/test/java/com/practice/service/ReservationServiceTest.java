package com.practice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.practice.jpa.ReservationRepository;
import com.practice.model.Reservation;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {

	@Mock
	private ReservationRepository repo;

	@Autowired
	@InjectMocks
	private ReservationService service;
	
	private List<Reservation> reservationList;
	
	@Before
	public void setUp(){
		Reservation reservation = new Reservation("Ramit");
		reservation.setId(100L);
		reservationList = new ArrayList<>();
		reservationList.add(reservation);
	}
	
	@Test
	public void testFindReservationByName(){
		Mockito.when(repo.findByReservationName("Ramit")).thenReturn(reservationList);
		Collection<Reservation> reservationCollection = service.findReservationByName("Ramit");
		List<Reservation> resultList = (List<Reservation>) reservationCollection;
		Assert.assertEquals(1, resultList.size());
		Assert.assertEquals(100, resultList.get(0).getId().longValue());
		
	}

}

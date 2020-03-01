package com.practice.jpa;

import java.util.List;

import org.jboss.logging.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.model.Reservation;

//@RepositoryRestResource
//this annotation is as good as exposing a rest controller as in ReservationRestController
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	List<Reservation> findByReservationName(@Param String name);
}

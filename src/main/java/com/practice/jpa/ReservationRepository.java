package com.practice.jpa;

import java.util.Collection;

import org.jboss.logging.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.practice.model.Reservation;

//@RepositoryRestResource
//this annotation is as good as exposing a rest controller as in ReservationRestController
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	Collection<Reservation> findByReservationName(@Param String rn);
}

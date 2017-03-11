package com.practice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Reservation {

	@Id
	@GeneratedValue
	private Long id;
	private String reservationName;
	
	//Optimistic locking
	@Version
	private Integer version;

	public Reservation() { // why jpa why
	}

	public Reservation(String reservationName) {
		this.reservationName = reservationName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[id=");
		sb.append(id);
		sb.append(",name=");
		sb.append(reservationName);
		sb.append("]");
		return sb.toString();
	}
}

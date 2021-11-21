package com.erik.projeto.entities;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity                                               
@Table(name = "additionalGuest")                       
public class AdditionalGuest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id                                              
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long  id;

	@Column(name = "name", nullable = false)
	private String name;	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Booking booking;
	

	public AdditionalGuest() {
	}

	
	//-------Getters and Setters-------//
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
}
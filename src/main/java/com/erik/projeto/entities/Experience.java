package com.erik.projeto.entities;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity                     
@Table(name = "experience")
public class Experience implements Serializable {

	private static final long serialVersionUID = 3L;


	@Id                                               
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	
	@Column(name = "name", nullable = false)
	private String name;
	
    @OneToMany(mappedBy = "experience", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Booking> bookings;	
    
    
	public Experience() {	
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
	

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	
}
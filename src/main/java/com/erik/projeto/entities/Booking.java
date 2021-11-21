package com.erik.projeto.entities;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.erik.projeto.enums.BookingStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity       
@Table(name = "booking")
public class Booking implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "guest_count")
	private Integer guestCount;

	@Column(name = "price_paid")
	private Float pricePaid;

	@Column(name = "local_percent")
	private Integer localPercent;

	@Column(name = "fee_to_yervana")
	private Float feeToYervana;

	@Column(name = "money_earned_by_host")
	private Float moneyEarnedByHost;
	
	@Column(name = "payment_transaction_id")
	private String paymentTransactionId;
	
	@Column(name = "transaction_date")
	private Long transactionDate;

	@Column(name = "incomplete")
	private Boolean incomplete;
	
	@Column(name = "date_time")
	private Long dateTime;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private BookingStatus status;

	@ManyToOne
	private Slot slot;

	@ManyToOne
	private Experience experience;

	@ManyToOne
	private User guest;

	//"User" to "UserHost"
	@OneToOne
	private UserHost host;
	
	@OneToMany(mappedBy = "booking") 
	@JsonIgnore
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<AdditionalGuest> additionalGuests = new HashSet<>();


	public Booking(){
	}


	//-------Getters and Setters-------//
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public Integer getGuestCount() {
		return guestCount;
	}

	public void setGuestCount(Integer guestCount) {
		this.guestCount = guestCount;
	}



	public Float getPricePaid() {
		return pricePaid;
	}

	public void setPricePaid(Float pricePaid) {
		this.pricePaid = pricePaid;
	}



	public Integer getLocalPercent() {
		return localPercent;
	}

	public void setLocalPercent(Integer localPercent) {
		this.localPercent = localPercent;
	}



	public Float getFeeToYervana() {
		return feeToYervana;
	}

	public void setFeeToYervana(Float feeToYervana) {
		this.feeToYervana = feeToYervana;
	}



	public Float getMoneyEarnedByHost() {
		return moneyEarnedByHost;
	}

	public void setMoneyEarnedByHost(Float moneyEarnedByHost) {
		this.moneyEarnedByHost = moneyEarnedByHost;
	}



	public String getPaymentTransactionId() {
		return paymentTransactionId;
	}

	public void setPaymentTransactionId(String paymentTransactionId) {
		this.paymentTransactionId = paymentTransactionId;
	}



	public Long getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Long transactionDate) {
		this.transactionDate = transactionDate;
	}



	public Boolean getIncomplete() {
		return incomplete;
	}

	public void setIncomplete(Boolean incomplete) {
		this.incomplete = incomplete;
	}



	public Long getDateTime() {
		return dateTime;
	}

	public void setDateTime(Long dateTime) {
		this.dateTime = dateTime;
	}



	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}



	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}



	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}



	public User getGuest() {
		return guest;
	}

	public void setGuest(User guest) {
		this.guest = guest;
	}



	public UserHost getHost() {
		return host;
	}


	public void setHost(UserHost host) {
		this.host = host;
	}



	public Set<AdditionalGuest> getAdditionalGuests() {
		return additionalGuests;
	}

	public void setAdditionalGuests(Set<AdditionalGuest> additionalGuests) {
		this.additionalGuests = additionalGuests;
	}

}
package com.erik.projeto;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.erik.projeto.entities.AdditionalGuest;
import com.erik.projeto.entities.Booking;
import com.erik.projeto.entities.Experience;
import com.erik.projeto.entities.Slot;
import com.erik.projeto.entities.User;
import com.erik.projeto.entities.UserHost;
import com.erik.projeto.enums.BookingStatus;
import com.erik.projeto.repositories.AdditionalGuestRepository;
import com.erik.projeto.repositories.BookingRepository;
import com.erik.projeto.repositories.ExperienceRepository;
import com.erik.projeto.repositories.SlotRepository;
import com.erik.projeto.repositories.UserHostRepository;
import com.erik.projeto.repositories.UserRepository;


@SpringBootApplication
@EnableScheduling
public class TestYervanaApplication {

	
	@Autowired 
	private SlotRepository slotRepository;

	@Autowired 
	private ExperienceRepository experienceRepository;
	
	@Autowired 
	private UserHostRepository userHostRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private AdditionalGuestRepository additionalGuestRepository;
	
	@Autowired 
	private BookingRepository bookingRepository;
	

	public static void main(String[] args) {
			
		SpringApplication.run(TestYervanaApplication.class, args);
	}

	
	@Bean
	public CommandLineRunner commandLineRunner(){
			
		return args -> {

			//---Examples - Creating and inserting some objects .
			
			Slot slot1 = new Slot();
			slot1.setId(1l);
			slot1.setName("Slot1");
			
			Slot slot2 = new Slot();
			slot2.setId(2l);
			slot2.setName("Slot2");
			
			Slot slot3 = new Slot();
			slot3.setId(3l);
			slot3.setName("Slot3");
			
			slotRepository.save(slot1);
			slotRepository.save(slot2);
			slotRepository.save(slot3);
			
			//---
			
			Experience experience1 = new Experience();
			experience1.setId(1l);
			experience1.setName("Experience1");

			Experience experience2 = new Experience();
			experience2.setId(2l);
			experience2.setName("Experience2");
			
			Experience experience3 = new Experience();
			experience3.setId(3l);
			experience3.setName("Experience3");
			
			experienceRepository.save(experience1);
			experienceRepository.save(experience2);
			experienceRepository.save(experience3);
			
			//---
			
			User user1 = new User();
			user1.setId(1l);
			user1.setName("User1");
			
			User user2 = new User();
			user2.setId(2l);
			user2.setName("User2");			
			
			User user3 = new User();
			user3.setId(3l);
			user3.setName("User3");
			
			userRepository.save(user1);
			userRepository.save(user2);			
			userRepository.save(user3);
			
			//---
			
			UserHost userHost1 = new UserHost();
			userHost1.setId(1l);
			userHost1.setName("UserHost1");

			UserHost userHost2 = new UserHost();
			userHost2.setId(2l);
			userHost2.setName("UserHost2");			
			
			UserHost userHost3 = new UserHost();
			userHost3.setId(3l);
			userHost3.setName("UserHost3");	
			
			userHostRepository.save(userHost1);
			userHostRepository.save(userHost2);
			userHostRepository.save(userHost3);
			
			//---			
			
			AdditionalGuest additionalGuest1 = new AdditionalGuest();
			additionalGuest1.setId(1l);
			additionalGuest1.setName("AdditionalGuest1");
			
			AdditionalGuest additionalGuest2 = new AdditionalGuest();
			additionalGuest2.setId(2l);
			additionalGuest2.setName("AdditionalGuest2");
			
			AdditionalGuest additionalGuest3 = new AdditionalGuest();
			additionalGuest3.setId(3l);
			additionalGuest3.setName("AdditionalGuest3");
			
			additionalGuestRepository.save( additionalGuest1 );
			additionalGuestRepository.save( additionalGuest2 );			
			additionalGuestRepository.save( additionalGuest3 );
			
			//---
			
			Set<AdditionalGuest> listOfAdditionalGuests1 = new HashSet<>();
			listOfAdditionalGuests1.add(additionalGuest2);
			
			//---
			
			Booking booking1 = new Booking();
			booking1.setId(1l);
			booking1.setGuestCount(1);			
			booking1.setPricePaid(100f);	
			booking1.setLocalPercent(5);
			booking1.setFeeToYervana(10f);
			booking1.setMoneyEarnedByHost(30f);
			booking1.setPaymentTransactionId("001");
			booking1.setTransactionDate(1l);
			booking1.setIncomplete(false);
			booking1.setDateTime(10l);
			booking1.setStatus(BookingStatus.IN_PROGRESS);
			booking1.setSlot(slot1);
			booking1.setExperience(experience1);
			booking1.setGuest(user1);
			booking1.setHost(userHost1);
			booking1.setAdditionalGuests( listOfAdditionalGuests1 );

			bookingRepository.save( booking1 );

		};
	}
	
}
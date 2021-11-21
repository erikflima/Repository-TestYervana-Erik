package com.erik.projeto.scheduler;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.erik.projeto.entities.Booking;
import com.erik.projeto.repositories.BookingRepository;

@Component
public class Scheduler {
	
	@Autowired 
	private BookingRepository bookingRepository;
	

	@Scheduled(fixedRate=2000) //2 segundos.
	//@Scheduled(cron = "0 0 1 * * MON")//Executed every monday at 1:00:am
	public void EmailSendingVerification() {

		System.out.println("\n\nFiring emails to explorers who booked an adventure last week.");
		
		//Get today and clear time of day
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0); //Clear would not reset the hour of day !
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);

		//Get start of this week in milliseconds
		cal.set( Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() );
		
		Date FirstDayOfthisWeek = cal.getTime();		
		Date LastDayOfLastWeek  = new DateTime(FirstDayOfthisWeek).minusDays(1).toDate();
		Date FirstDayOfLastWeek = new DateTime(LastDayOfLastWeek ).minusDays(6).toDate();
		
		System.out.println("FirstDayOfthisWeek: "+FirstDayOfthisWeek);
		System.out.println("LastDayOfLastWeek:  "+LastDayOfLastWeek );		
		System.out.println("FirstDayOfLastWeek: "+FirstDayOfLastWeek);	

		
		List<Booking> bookignsBookedLastWeek= bookingRepository.findByDateTimeBetween( FirstDayOfLastWeek, LastDayOfLastWeek );

		//EmailSender enviar e-amils nessa melda
	
	
	}

}

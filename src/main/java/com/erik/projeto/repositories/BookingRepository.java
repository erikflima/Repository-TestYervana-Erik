package com.erik.projeto.repositories;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.erik.projeto.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	List<Booking> findByDateTimeAfter( Date dateTime ); 
	
	List<Booking> findByTransactionDateAfter( Date transactionDate ); 	

}
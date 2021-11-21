package com.erik.projeto.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.erik.projeto.entities.AdditionalGuest;

public interface AdditionalGuestRepository extends JpaRepository<AdditionalGuest, Long> {
	
}
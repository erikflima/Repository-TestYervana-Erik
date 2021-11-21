package com.erik.projeto.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.erik.projeto.entities.UserHost;

public interface UserHostRepository extends JpaRepository<UserHost, Long> {
	
 
}
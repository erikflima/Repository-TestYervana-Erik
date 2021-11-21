package com.erik.projeto.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.erik.projeto.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
 
}
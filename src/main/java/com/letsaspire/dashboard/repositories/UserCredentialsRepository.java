package com.letsaspire.dashboard.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.letsaspire.dashboard.domain.User;
import com.letsaspire.dashboard.domain.UserCredentials;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, UUID> {
	
	//@Query(nativeQuery = true, value = "select * from user_credentials c where c.phone_number =:phonenumber and is_active = 1")
	public UserCredentials findByPhoneNumber(Long phonenumber);
	
	//@Query(nativeQuery = true, value = "select * from user_credentials c where c.email =:email and is_active = 1")
	public UserCredentials findByEmail(String email);

}

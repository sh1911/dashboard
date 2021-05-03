package com.letsaspire.dashboard.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.letsaspire.dashboard.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
	public User findByPhoneNumber(long phoneNumber);
	
	@Query(nativeQuery = true, value = "select * from user c where c.pannumber =:pannumber and is_active = 1")
	public User findByPanNumber(String pannumber);
	
	@Query(nativeQuery = true, value = "select * from user c where c.phone_number =:phonenumber and is_active = 1")
	public User findByPhoneNumber(String phonenumber);

}

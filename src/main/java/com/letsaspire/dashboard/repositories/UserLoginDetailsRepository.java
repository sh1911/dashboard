package com.letsaspire.dashboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letsaspire.dashboard.domain.UserLoginDetails;

public interface UserLoginDetailsRepository extends JpaRepository<UserLoginDetails, Long> {

}

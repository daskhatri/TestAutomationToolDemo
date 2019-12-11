package com.finexus.automation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finexus.automation.entity.Suite;

public interface SuiteRepository extends JpaRepository<Suite, Integer>{
	
}

package com.finexus.automation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finexus.automation.entity.TestMethod;

public interface TestMethodRepository extends JpaRepository<TestMethod, Integer> {
	
	
}

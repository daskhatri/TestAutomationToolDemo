package com.finexus.automation.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finexus.automation.entity.TestCase;

public interface TestCaseRepository extends JpaRepository<TestCase, Integer>{

//	@Query(value = "Select tc from TestCase Join Fetch")
//	TestCase findTestCase();
}

package com.finexus.automation.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.finexus.automation.entity.TestngResults;

public interface TestngResultsRepository extends JpaRepository<TestngResults, Long>{

	TestngResults findTopByOrderByTestngIdDesc();
	
}

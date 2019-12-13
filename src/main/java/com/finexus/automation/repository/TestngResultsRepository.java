package com.finexus.automation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finexus.automation.entity.TestngResults;

public interface TestngResultsRepository extends JpaRepository<TestngResults, Long>{

	TestngResults findTopByOrderByTestngIdDesc();
	
	@Query("SELECT   SUM(tr.failed) as failed, SUM(tr.passed) as passed, SUM(tr.ignored) as ignored, sum(tr.skipped) as skipped, SUM(tr.total) as total FROM TestngResults tr")
	List<Object[]> getTotalCountOfExecutions();
	
}

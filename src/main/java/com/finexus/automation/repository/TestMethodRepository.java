package com.finexus.automation.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finexus.automation.entity.TestMethod;
import com.finexus.automation.pojo.TestMethodPojo;

public interface TestMethodRepository extends JpaRepository<TestMethod, Integer> {

	
//	@Query("Select tm.name, max(tm.finished_at) from TestMethod tm ")
//	where tm.name not in (\"setUp\", \"tearDown\") group by tm.name
//	@Query(value = "SELECT new com.package.Result (u, MAX (p.points) ) FROM user u JOIN points p ON u.id = p.user_id GROUP BY u")
//	@Query(value = "Select new com.finexus.automation.pojo.TestMethodPojo (tm, MAX (tm.inished_at)) from TestMethod tm ")
	
	@Query(value = "Select new com.finexus.automation.pojo.TestMethodPojo (tm.id, tm.status, tm.name, MAX (tm.finished_at)) from TestMethod tm WHERE tm.name NOT in ('setUp', 'tearDown') GROUP BY tm.name ")
	List<TestMethodPojo> getAllTestMethods();
	
	
}

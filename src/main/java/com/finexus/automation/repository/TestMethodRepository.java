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
	
	//@Query(value = "Select new com.finexus.automation.pojo.TestMethodPojo (tm.id, tm.status, tm.name, MAX (tm.finished_at)) from TestMethod tm WHERE tm.name NOT in ('setUp', 'tearDown') GROUP BY tm.name ")

	@Query(value = "select tc.id as Id, tm.name as Name, tm.status as Status, max(tm.finished_at) as lastRun, tm.id as TestMethodId from test_case tc right join test_method tm on tc.id = tm.t_casetmethod_fk where tm.name NOT in ('setUp', 'tearDown') group by tm.name order by lastRun desc", nativeQuery = true )
	List<Object> getAllTestMethods();
	
	// for line chart 
	@Query(value = "SELECT count(tm.id), DATE(tm.finished_at) FROM test_method tm WHERE tm.finished_at >= (DATE(NOW()) - INTERVAL 25 DAY) GROUP BY DATE(tm.finished_at) ORDER BY tm.finished_at DESC", nativeQuery=true)
	List<Object[]> getNoOfTestCaseGroupByDate();
}

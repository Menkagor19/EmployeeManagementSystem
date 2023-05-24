package org.employeemanagement.repository;

import org.employeemanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "SELECT * FROM EMPLOYEE u WHERE u.aadhar = :aadhar", nativeQuery = true)
    Employee findByAadhar(@Param("aadhar") String aadhar);

}

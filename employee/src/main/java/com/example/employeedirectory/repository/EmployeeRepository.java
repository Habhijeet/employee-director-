package com.example.employeedirectory.repository;

import com.example.employeedirectory.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartment(String department);
    List<Employee> findByJoiningDateBetween(Date startDate, Date endDate);
}

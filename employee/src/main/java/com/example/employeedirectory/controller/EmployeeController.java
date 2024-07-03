package com.example.employeedirectory.controller;

import com.example.employeedirectory.entity.Employee;
import com.example.employeedirectory.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return employeeService.updateEmployee(id, employeeDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/department/{department}")
    public List<Employee> getEmployeesByDepartment(@PathVariable String department) {
        return employeeService.getEmployeesByDepartment(department);
    }

    @PutMapping("/{id}/salary")
    public void updateEmployeeSalary(@PathVariable Long id, @RequestParam double salary) {
        employeeService.updateEmployeeSalary(id, salary);
    }

    @GetMapping("/total-salary/{department}")
    public double calculateTotalSalaryByDepartment(@PathVariable String department) {
        return employeeService.calculateTotalSalaryByDepartment(department);
    }

    @GetMapping("/joining-date-range")
    public List<Employee> findEmployeesByJoiningDateRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        return employeeService.findEmployeesByJoiningDateRange(startDate, endDate);
    }

    @PutMapping("/{id}/position")
    public void updateEmployeePositionBasedOnExperience(@PathVariable Long id, @RequestParam int experienceYears) {
        employeeService.updateEmployeePositionBasedOnExperience(id, experienceYears);
    }

    @GetMapping("/report/{id}")
    public String generateEmployeeReport(@PathVariable Long id) {
        return employeeService.generateEmployeeReport(id);
    }

    @GetMapping("/promotion-eligibility/{id}")
    public boolean isEmployeeEligibleForPromotion(@PathVariable Long id, @RequestParam double performanceScore, @RequestParam int yearsOfService) {
        return employeeService.isEmployeeEligibleForPromotion(id, performanceScore, yearsOfService);
    }

    @PostMapping("/notifications")
    public void sendNotificationToEmployees(@RequestBody String message) {
        employeeService.sendNotificationToEmployees(message);
    }

    @GetMapping("/department-performance")
    public List<String> analyzeDepartmentPerformance() {
        return employeeService.analyzeDepartmentPerformance();
    }

    @PutMapping("/update-budget/{departmentId}")
    public void updateDepartmentBudget(@PathVariable Long departmentId, @RequestParam int employeeCount) {
        employeeService.updateDepartmentBudget(departmentId, employeeCount);
    }

    @GetMapping("/top-paid/{n}")
    public List<Employee> findTopNHighestPaidEmployees(@PathVariable int n) {
        return employeeService.findTopNHighestPaidEmployees(n);
    }

    @GetMapping("/duplicate-employees")
    public List<Employee> checkForDuplicateEmployees() {
        return employeeService.checkForDuplicateEmployees();
    }

    @GetMapping("/average-salary/{position}")
    public double calculateAverageSalaryByPosition(@PathVariable String position) {
        return employeeService.calculateAverageSalaryByPosition(position);
    }
}

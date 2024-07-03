package com.example.employeedirectory.service;

import com.example.employeedirectory.entity.Employee;
import com.example.employeedirectory.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        validateEmployee(employee);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found for this id :: " + id));

        employee.setName(employeeDetails.getName());
        employee.setPosition(employeeDetails.getPosition());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setSalary(employeeDetails.getSalary());
        employee.setEmail(employeeDetails.getEmail());
        employee.setJoiningDate(employeeDetails.getJoiningDate());

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found for this id :: " + id));
        employeeRepository.delete(employee);
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    public void updateEmployeeSalary(Long id, double salary) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found for this id :: " + id));
        employee.setSalary(salary);
        employeeRepository.save(employee);
    }

    public double calculateTotalSalaryByDepartment(String department) {
        List<Employee> employees = employeeRepository.findByDepartment(department);
        return employees.stream().mapToDouble(Employee::getSalary).sum();
    }

    public List<Employee> findEmployeesByJoiningDateRange(Date startDate, Date endDate) {
        return employeeRepository.findByJoiningDateBetween(startDate, endDate);
    }

    public void updateEmployeePositionBasedOnExperience(Long id, int experienceYears) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found for this id :: " + id));
        if (experienceYears > 5 && employee.getPosition().equalsIgnoreCase("Junior")) {
            employee.setPosition("Senior");
            employeeRepository.save(employee);
        }
    }

    public String generateEmployeeReport(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found for this id :: " + id));

        return "Employee Report:\n" +
                "Name: " + employee.getName() + "\n" +
                "Position: " + employee.getPosition() + "\n" +
                "Department: " + employee.getDepartment() + "\n" +
                "Salary: " + employee.getSalary();
    }

    public boolean isEmployeeEligibleForPromotion(Long id, double performanceScore, int yearsOfService) {
        @SuppressWarnings("unused")
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found for this id :: " + id));
        return performanceScore > 4.5 && yearsOfService > 3;
    }

    public void sendNotificationToEmployees(String message) {
        // Logic to send notification
    }

    public List<String> analyzeDepartmentPerformance() {
        // Logic to analyze and rank departments
        return List.of(); // Placeholder
    }

    public void updateDepartmentBudget(Long departmentId, int employeeCount) {
        // Logic to update department budget
    }

    public List<Employee> findTopNHighestPaidEmployees(int n) {
        return employeeRepository.findAll()
                .stream()
                .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
                .limit(n)
                .toList();
    }

    public List<Employee> checkForDuplicateEmployees() {
        // Logic to check for duplicate employees based on email addresses
        return List.of(); // Placeholder
    }

    public double calculateAverageSalaryByPosition(String position) {
        List<Employee> employees = employeeRepository.findAll()
                .stream()
                .filter(e -> e.getPosition().equalsIgnoreCase(position))
                .toList();
        return employees.stream().mapToDouble(Employee::getSalary).average().orElse(0);
    }

    private void validateEmployee(Employee employee) {
        if (employee.getName() == null || employee.getName().isEmpty()) {
            throw new RuntimeException("Employee name is required");
        }
        if (employee.getEmail() == null || !employee.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new RuntimeException("Invalid email format");
        }
        if (employee.getSalary() <= 0) {
            throw new RuntimeException("Salary must be a positive number");
        }
    }
}

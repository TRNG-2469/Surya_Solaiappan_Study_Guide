package com.rev.rest.employeemanagement.Controller;

import com.rev.rest.employeemanagement.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    private List<Employee> employees = new ArrayList<>(List.of(
            new Employee(101, "Rahul Sharma","IT","Developer", 65000),
            new Employee(102, "Priya Singh", "HR",  "Manager",   75000),
            new Employee(103,  "Amit Verma",  "IT",  "Tester", 55000),
                    new Employee(104,  "Sneha Patel",  "Finance",  "Accountant",  60000),
                    new Employee(105,  "Arjun Mehta",  "IT",  "Manager" ,  90000),
                    new Employee(106,  "Neha Gupta",    "HR",   "Recruiter",  45000)
                    ));

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employees;
    }

    @GetMapping("/employees/id")
    public Employee getEmployeeById(@RequestParam int id){
        for(Employee e: employees){
            if(e.getId()==id){
                return e;
            }
        }
        return null;
    }

    @PostMapping("/employees")
    public String postEmployee(@RequestBody Employee employee){
        employees.add(employee);
        return "success";
    }

    @PutMapping("/employees/{id}")
    public String updateEmployee(int id, @RequestBody Employee employee){
        for(Employee e: employees){
            if(e.getId()==id){
                e.setName(employee.getName());
                e.setEmail(employee.getEmail());
                e.setDepartment(employee.getDepartment());
                e.setRole(employee.getRole());
                e.setSalary(employee.getSalary());
            }
        }
        return null;
    }

    @DeleteMapping()

}

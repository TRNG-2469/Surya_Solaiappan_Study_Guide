package com.rev.rest.employeemanagement.Service;

import com.rev.rest.employeemanagement.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private List<Employee> employees = new ArrayList<>(List.of(
            new Employee(101, "Rahul Sharma", "rahul@company.com", "IT", "Developer", 65000),
            new Employee(102, "Priya Singh",  "priya@company.com", "HR", "Manager",   75000),
            new Employee(103, "Amit Verma",   "amit@company.com",  "IT", "Tester",    55000),
            new Employee(104, "Sneha Patel",  "sneha@company.com", "Finance", "Accountant", 60000),
            new Employee(105, "Arjun Mehta",  "arjun@company.com", "IT", "Manager",   90000),
            new Employee(106, "Neha Gupta",   "neha@company.com",  "HR", "Recruiter", 45000)
    ));

    public List<Employee> getAllEmployees(){
        return employees;
    }

    public Employee getEmployeeById(int id){
        for(Employee e: employees){
            if(e.getId()==id){
                return e;
            }
        }
        return null;
    }

    public String postEmployee(Employee employee){
        employees.add(employee);
        return "success in creating employee";
    }

    public String updateEmployee(int id, Employee employee) {
        for(Employee e: employees){
            if(e.getId()==id){
                e.setName(employee.getName());
                e.setEmail(employee.getEmail());
                e.setDepartment(employee.getDepartment());
                e.setDesignation(employee.getDesignation());
                e.setSalary(employee.getSalary());
                return "Employee updated successfully";
            }
        }
        return "Employee not found";
    }

    public String deleteEmployee(int id){
        for(Employee e: employees){
            if(e.getId()==id){
                employees.remove(e);
                return "success in deleting employee";
            }
        }
        return "failure in deleting employee";
    }

    public Employee findByEmail(String email){
        for(Employee e: employees){
            if(e.getEmail().equals(email)){
                return e;
            }
        }
        return null;
    }

    public List<Employee> findByDepartment(String department){
        List<Employee> emp = new ArrayList<>();
        for(Employee e: employees){
            if(e.getDepartment().equals(department)){
                emp.add(e);
            }
        }
        return emp;
    }

    public List<Employee> findByDesignation(String designation){
        List<Employee> emp = new ArrayList<>();
        for(Employee e: employees){
            if(e.getDesignation().equals(designation)){
                emp.add(e);
            }
        }
        return emp;
    }

    public List<Employee> getBySalaryRange(int minSalary, int maxSalary){
        List<Employee> employeesWithSuchSalary = new ArrayList<>();
        for(Employee e: employees){
            if(e.getSalary()>minSalary && e.getSalary()< maxSalary){
                employeesWithSuchSalary.add(e);
            }
        }
        return employeesWithSuchSalary;
    }




}

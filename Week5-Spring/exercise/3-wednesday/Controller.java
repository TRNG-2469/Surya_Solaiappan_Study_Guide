package com.rev.rest.employeemanagement.Controller;

import com.rev.rest.employeemanagement.Service.EmployeeService;
import com.rev.rest.employeemanagement.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    private final EmployeeService service;

    public Controller(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return ResponseEntity.ok(service.getAllEmployees());
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
        Employee employee = service.getEmployeeById(id);
        if(employee==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/employees")
    public ResponseEntity<String> postEmployee(@RequestBody Employee employee){
        String created = service.postEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);

    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        String updated = service.updateEmployee(id, employee);
        if (updated.equals("Employee not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id){
        String deleted =  service.deleteEmployee(id);
        if (deleted.equals("failure in deleting employee")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/employees/email/{email}")
    public ResponseEntity<Employee> findByEmail(@PathVariable String email) {
        Employee employee = service.findByEmail(email);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(employee);
    }

    @GetMapping(value = "/employees", params = "department")
    public ResponseEntity<List<Employee>> findByDepartment(@RequestParam String department) {
        return ResponseEntity.ok(service.findByDepartment(department));
    }

    @GetMapping(value = "/employees", params = "designation")
    public ResponseEntity<List<Employee>> findByDesignation(@RequestParam String designation) {
        return ResponseEntity.ok(service.findByDesignation(designation));
    }

    @GetMapping(value = "/employees", params = {"minSalary", "maxSalary"})
    public ResponseEntity<List<Employee>> getBySalaryRange(@RequestParam int minSalary, @RequestParam int maxSalary) {
        return ResponseEntity.ok(service.getBySalaryRange(minSalary, maxSalary));
    }



}

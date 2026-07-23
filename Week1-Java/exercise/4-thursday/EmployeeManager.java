package com.revature.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class EmployeeManager implements Comparator<Employee> {
    public static void main(String[] args){
        Employee e1 = new Employee(1, "surya", 223.22);
        Employee e2 = new Employee(2, "bob", 32.23);
        List<Employee> employees = new ArrayList<>();
        employees.add(e1);
        employees.add(e2);
        for(Employee e:employees){
            System.out.println(e);
        }
        Collections.sort(employees, new EmployeeManager());
        System.out.println("\nAfter sorting in ascending order of salary:");
        for(Employee e : employees){
            System.out.println(e);
        }
    }

    @Override
    public int compare(Employee e1, Employee e2) {
        if(e1.getSalary() > e2.getSalary()) return 1;
        if(e1.getSalary() < e2.getSalary()) return -1;
        return 0;
    }

}

package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

        public static void main(String[] args){
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationConfig.xml");
            Employee e = context.getBean("employeeBean", Employee.class);
            e.setId(1);
            e.setName("Joe");
            e.setSalary(65000);

            Address a = e.getAddress();
            a.setName("Maple Way");
            a.setCity("Washington");
            a.setPincode(283392);

            System.out.println("Employee ID: " + e.getId());
            System.out.println("Name: " + e.getName());
            System.out.println("Salary: " + e.getSalary());
            System.out.println("Address Street Name: " + e.getAddress().getName());
            System.out.println("State: " + e.getAddress().getCity());
            System.out.println("Pincode: " + e.getAddress().getPincode());

            context.close();
        }
}

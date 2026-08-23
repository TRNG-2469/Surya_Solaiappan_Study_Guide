package com.rev.rest.springg.exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(int id) {
        super("Student with id "+id +" not found.");
    }
}

package com.rev.rest.springg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    @Id
    private int id;
    @NotBlank(message="Email is mandatory")
    @Size(min = 2, max = 50)
    private String name;
    private String email;
    private String course;

}
package com.rev.rest.bankaccount.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private int id;

    @NotBlank(message = "Account Number is required")
    private String accountNumber;

    @NotBlank(message = "Customer Name is required")
    private String customerName;

    @NotBlank(message = "Customer Email is required")
    @Email
    private String customerEmail;


    @NotNull(message = "Account Type is required")
    private AccountType accountType;


    @NotBlank(message = "Branch is required")
    private String branch;

    @NotNull(message = "Balance is required")
    @Min(value = 0)
    private int balance;

}



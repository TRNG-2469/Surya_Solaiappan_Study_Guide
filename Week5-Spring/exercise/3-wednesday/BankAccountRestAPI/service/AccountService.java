package com.rev.rest.bankaccount.service;

import com.rev.rest.bankaccount.exception.AccountNotFoundException;
import com.rev.rest.bankaccount.exception.InsufficientBalanceException;
import com.rev.rest.bankaccount.exception.InvalidAmountException;
import com.rev.rest.bankaccount.model.Account;
import com.rev.rest.bankaccount.model.AccountType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.rev.rest.bankaccount.model.AccountType.CURRENT;
import static com.rev.rest.bankaccount.model.AccountType.SAVINGS;
@Service
public class AccountService {

    private List<Account> accounts = new ArrayList<>(List.of(
            new Account(1, "ACC1001", "Rahul Sharma", "rahul@example.com", SAVINGS, "Bhopal", 50000),
            new Account(2, "ACC1002", "Priya Singh", "priya@example.com", CURRENT, "Indore", 125000),
            new Account(3, "ACC1003", "Amit Verma", "amit@example.com", SAVINGS, "Bhopal", 75000),
            new Account(4, "ACC1004", "Sneha Patel", "sneha@example.com", CURRENT, "Delhi", 200000),
            new Account(5, "ACC1005", "Arjun Mehta", "arjun@example.com", SAVINGS, "Mumbai", 35000),
            new Account(6, "ACC1006", "Neha Gupta", "neha@example.com", SAVINGS, "Bhopal", 90000)
    ));

    public List<Account> getAllAccounts(){
        return accounts;
    }

    public Account getAccountsById(int id){
        for(Account a:accounts){
            if(a.getId()==id){
                return a;
            }
        }
        throw new AccountNotFoundException("Account not found with id " + id);
    }

    public Account createAccount(Account account){
        accounts.add(account);
        return account;
    }

    public Account updateAccount(int id, Account account){
        for(Account a: accounts){
            if(a.getId()==id){
                a.setCustomerName(account.getCustomerName());
                a.setCustomerEmail(account.getCustomerEmail());
                a.setAccountType(account.getAccountType());
                a.setBranch(account.getBranch());
                a.setBalance(account.getBalance());
                return a;
            }
        }
        return null;
    }

    public List<Account> deleteAccount(int id){
        for(Account a:accounts){
            if(a.getId()==id){
                accounts.remove(a);
            }
        }
        return accounts;
    }

    public Account getByAccountNumber(String accountNumber){
        for(Account a:accounts){
            if(a.getAccountNumber().equals(accountNumber)){
                return a;
            }
        }
        return null;
    }

    public List<Account> findByCustomerName(String customerName){
        List<Account> accountsWithName = new ArrayList<>();
        for(Account a:accounts){
            if(a.getCustomerName().equalsIgnoreCase(customerName)){
                accountsWithName.add(a);
            }
        }
        return accountsWithName;
    }

    public List<Account> findByBranch(String branch){
        List<Account> accountsWithBranch = new ArrayList<>();
        for(Account a:accounts){
            if(a.getBranch().equals(branch)){
                accountsWithBranch.add(a);
            }
        }
        return accountsWithBranch;
    }


    public List<Account> findByAccountType(AccountType accountType){
        List<Account> accountsWithType = new ArrayList<>();
        for(Account a:accounts){
            if(a.getAccountType()==accountType){
                accountsWithType.add(a);
            }
        }
        return accountsWithType;
    }

    public List<Account> findByBalanceRange(int minBalance, int maxBalance){
        List<Account> acc = new ArrayList<>();
        for(Account a:accounts){
            if(a.getBalance()>=minBalance && a.getBalance()<=maxBalance){
                acc.add(a);
            }
        }
        return acc;
    }

    public Account deposit(int id, int amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than 0");
        }
        Account account = getAccountsById(id);
        account.setBalance(account.getBalance() + amount);
        return account;
    }

    public Account withdraw(int id, int amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than 0");
        }
        Account account = getAccountsById(id);
        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance in account");
        }
        account.setBalance(account.getBalance() - amount);
        return account;
    }
}

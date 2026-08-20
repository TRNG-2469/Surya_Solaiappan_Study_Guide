package com.rev.rest.bankaccount.Controller;

import com.rev.rest.bankaccount.service.AccountService;
import com.rev.rest.bankaccount.model.Account;
import com.rev.rest.bankaccount.model.AccountType;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    AccountService service;
    public Controller(AccountService service){
        this.service=service;
    }
    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts(){
        List<Account> allAccounts = service.getAllAccounts();
        return ResponseEntity.ok(allAccounts);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccountsById(@PathVariable int id){
        Account accountWithId = service.getAccountsById(id);
        return ResponseEntity.ok(accountWithId);
    }

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account){
        Account createdAccount = service.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<Account>updateAccount(@PathVariable int id, @Valid @RequestBody Account account){
        Account updatedAccount = service.updateAccount(id,account);
        if(updatedAccount==null){
            return ResponseEntity.notFound().build(); // return 404
        }
        return ResponseEntity.ok(updatedAccount); //200
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<List<Account>> deleteAccount(@PathVariable int id){
        List<Account> remainingAccounts = service.deleteAccount(id);
        if(!remainingAccounts.isEmpty()) return ResponseEntity.ok(remainingAccounts);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/accounts/number/{accountNumber}")
    public ResponseEntity<Account> getByAccountNumber(@PathVariable String accountNumber){
        Account accountWithNumber = service.getByAccountNumber(accountNumber);
        return ResponseEntity.ok(accountWithNumber);
    }

    @GetMapping(value="/accounts",params="customerName")
    public ResponseEntity<List<Account>> findByCustomerName(@RequestParam String customerName){
        List<Account> accountsWithCustomerName = service.findByCustomerName(customerName);
        return ResponseEntity.ok(accountsWithCustomerName);
    }

    @GetMapping(value="/accounts",params="branch")
    public ResponseEntity<List<Account>> findByBranch(@RequestParam String branch){
        List<Account> accountsWithBranch = service.findByBranch(branch);
        return ResponseEntity.ok(accountsWithBranch);
    }

    @GetMapping(value="/accounts",params="accountType")
    public ResponseEntity<List<Account>> findByAccountType(@RequestParam AccountType accountType){
        List<Account> accountsWithType = service.findByAccountType(accountType);
        return ResponseEntity.ok(accountsWithType);
    }

    @GetMapping(value="/accounts",params={"minBalance", "maxBalance"})
    public ResponseEntity<List<Account>> findByBalanceRange(@RequestParam int minBalance, @RequestParam int maxBalance){
        List<Account> accountsWithinRange = service.findByBalanceRange(minBalance, maxBalance);
        return ResponseEntity.ok(accountsWithinRange);
    }

    @PostMapping("/accounts/{id}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable int id, @RequestParam int amount){
        Account accountDepositedInto = service.deposit(id,amount);
        return ResponseEntity.ok(accountDepositedInto);
    }


    @PostMapping(value="/accounts/{id}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable int id, @RequestParam int amount){
        Account accountWithdrawnFrom = service.withdraw(id, amount);
        return ResponseEntity.ok(accountWithdrawnFrom);
    }





}

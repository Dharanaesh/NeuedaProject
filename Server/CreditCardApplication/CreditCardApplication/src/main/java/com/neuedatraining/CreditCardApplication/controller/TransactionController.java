package com.neuedatraining.CreditCardApplication.controller;

import com.neuedatraining.CreditCardApplication.dto.*;
import com.neuedatraining.CreditCardApplication.entity.Transactions;
import com.neuedatraining.CreditCardApplication.exception.CardUserNotFoundException;
import com.neuedatraining.CreditCardApplication.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService service;
    @GetMapping
    public List<Transactions> getAllUser() {
        return  this.service.getUsers();
    }
    @GetMapping("/mapRegion")
    public List<Transactions> getUsersByRegion(@RequestParam("city") String city) {
        if(city == null)
            return this.service.getUsers();
        return  this.service.findCardUsersByRegion(city);
    }
    @GetMapping("/mapLAmount")
    public List<Transactions> getTransactionsLessThan(@RequestParam("amount") double amount) {
        return  this.service.findTransactionsLesserThan(amount);
    }
    @GetMapping("/mapGAmount")
    public List<Transactions> getTransactionsGreaterThan(@RequestParam("amount") double amount) {
        return  this.service.findTransactionsGreaterThan(amount);
    }
    @GetMapping("/mapBAmount")
    public List<Transactions> getTransactionsInBetween(@RequestParam("minAmount") double minAmount,@RequestParam("maxAmount") double maxAmount) {
        return  this.service.findTransactionsBetween(minAmount,maxAmount);
    }
    @GetMapping("/mapName")
    public ResponseEntity<List<Transactions>> getUsersByFName(@RequestParam("first") String first, @RequestParam("last") String last) {
        List<Transactions> user = service.findCardUsersByName(first, last);
        if (user != null) {
            return ResponseEntity.ok(user); // Return the user with 200 status code
        } else {
            return ResponseEntity.notFound().build(); // Return 404 status code
        }
    }

    @GetMapping("/{customer_id}")
    public ResponseEntity<List<Transactions>> getUserById(@PathVariable int customer_id) {
        try {
            List<Transactions> user = service.findCardUsersByCustomerId(customer_id);
            return ResponseEntity.ok(user);
        } catch (CardUserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/statetransactions")
    public List<StateSales> getTotalTransactionByState(){
        return service.getTotalTransactionsByState();
    }
    @GetMapping("/merchanttransactions")
    public List<Merchantspend> getTotalTransactionByMerchant(){
        return service.getCategorySpendByMerchant();
    }

    @GetMapping("statetransactions/{state}")
    public List<CategorySpend> getCategorySpendByState(@PathVariable String state){
        return service.getCategorySpendByState(state);
    }
    @GetMapping("gendertransactions/{gender}")
    public List<GenderCategorySpend> getCategorySpendByGender(@PathVariable String gender){
        return service.getCategorySpendByGender(gender);
    }
    @GetMapping("/pages")
    public TransactionPerPageResponse getTransactionsByPage(@RequestParam(required = false, defaultValue = "0") int pageno,
                                                            @RequestParam(required = false, defaultValue = "200") int size)
    {
        return this.service.getTransactionsByPagination(pageno, size);
    }
}

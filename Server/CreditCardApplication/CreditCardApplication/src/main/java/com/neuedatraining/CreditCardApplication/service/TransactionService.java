package com.neuedatraining.CreditCardApplication.service;


import com.neuedatraining.CreditCardApplication.dto.*;
import com.neuedatraining.CreditCardApplication.entity.Transactions;
import com.neuedatraining.CreditCardApplication.exception.CardUserNotFoundException;
import com.neuedatraining.CreditCardApplication.repository.TransactionReportsRepo;
import com.neuedatraining.CreditCardApplication.repository.Transactionrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class TransactionService {
    Logger LOGGER=  LoggerFactory.getLogger("CreditCardChallengeApplication");
    @Autowired
    private Transactionrepo repo;

    //List of total transaction count is displayed
    public long getTransCount(){
        LOGGER.info("***List of total transaction count is displayed***");
        return this.repo.count();
    }
    //List of users from transactions is displayed
    public List<Transactions> getUsers(){
        LOGGER.info("***List of users from transactions is displayed***");
        return repo.findTop300By();
    }
    //List of users from a given region is displayed
    public List<Transactions> findCardUsersByRegion(String city){
        LOGGER.info("***List of users from a given region is displayed***");
        return this.repo.findByCity(city);
    }
    //Details of a user with a particular name is displayed
    public List<Transactions> findCardUsersByName(String fName,String lName){
        LOGGER.info("***Details of a user with a particular name is displayed***");
        return this.repo.findByName(fName,lName);
    }
    //findTransactionsWithAmountGreaterThan
    public List<Transactions> findTransactionsGreaterThan(double amount){
        LOGGER.info("***List of transactions greater than a given amount is displayed***");
        return this.repo.findTransactionsWithAmountGreaterThan(amount);
    }
    //List of transactions lesser than a given amount is displayed
    public List<Transactions> findTransactionsLesserThan(double amount){
        LOGGER.info("***List of transactions lesser than a given amount is displayed***");
        return this.repo.findTransactionsWithAmountLessThan(amount);
    }
    //List of transactions between two amount values is displayed
    public List<Transactions> findTransactionsBetween(double minAmount,double maxAmount){
        LOGGER.info("***List of transactions between two amount values is displayed***");
        return this.repo.findTransactionsWithAmountBetween(minAmount,maxAmount);
    }
    //Details of user with a particular Customer Id is displayed
    public List<Transactions> findCardUsersByCustomerId(int customer_id) throws CardUserNotFoundException{
        LOGGER.info("***Details of user with a particular Customer Id is displayed***");
        return this.repo.findByCustomerId(customer_id);
    }
    @Autowired
    TransactionReportsRepo repository;
    public List<StateSales>  getTotalTransactionsByState(){
        LOGGER.info("***List of total transactions by state is displayed***");
        return repository.getTotalTransactionsByState();
    }
    //Details on category spend by state is displayed
    public List<CategorySpend> getCategorySpendByState(String state) {
        LOGGER.info("***Details on category spend by state is displayed***");
        return repository.getCategorySpendByState(state);
    }
    public List<GenderCategorySpend> getCategorySpendByGender(String gender) {
        LOGGER.info("***List of total category of transactions by Gender is displayed***");
        return repository.getCategorySpendByGender(gender);
    }
    public List<Merchantspend> getCategorySpendByMerchant() {
        LOGGER.info("***Details on All Merchant Transaction is displayed***");
        return repository.getCategorySpendByMerchant();
    }
    //pagination
    public TransactionPerPageResponse getTransactionsByPagination(int pageno, int size) {
        Pageable pageable = PageRequest.of(pageno, size);
        Page<Transactions> page = repo.findAll(pageable);
        int totalPages = page.getTotalPages();
        long totalElements = page.getTotalElements();
        int noofelements = page.getNumberOfElements();
        int pagesize = page.getSize();
        TransactionPerPageResponse response = new TransactionPerPageResponse();
        response.setUsers(page.getContent());
        response.setNoofelements(noofelements);
        response.setPagesize(pagesize);
        response.setTotalElements(totalElements);
        response.setTotalPages(totalPages);
        return response;
    }

}


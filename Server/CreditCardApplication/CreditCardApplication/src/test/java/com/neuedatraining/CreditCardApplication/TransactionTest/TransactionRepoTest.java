package com.neuedatraining.CreditCardApplication.TransactionTest;


import com.neuedatraining.CreditCardApplication.entity.CreditCardUser;
import com.neuedatraining.CreditCardApplication.entity.Transactions;
import com.neuedatraining.CreditCardApplication.repository.Transactionrepo;
import org.assertj.core.api.AbstractFileAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class TransactionRepoTest {

    @Autowired
    private Transactionrepo repo;



    @Test
    public void findUserByName(){
        repo.save(new Transactions(null, 1000.00,11,1, "Chicago","Illinois",200000,"Alice","Food","Tom","Hanks","M","Doctor",null));
        List<Transactions> userFound = repo.findByName("Tom","Hanks");

        assertEquals("Doctor",userFound.get(0).getJob());
    }

    @Test
    public void findByCity(){
        repo.save(new Transactions(null, 1000.00,11,1, "Chicago","Illinois",200000,"Alice","Food","Tom","Hanks","M","Doctor",null));
        List<Transactions> userFound = repo.findByCity("Chicago");

        assertEquals("Tom",userFound.get(0).getFirst());
    }
    @Test
    public void findByCustomerId(){
        repo.save(new Transactions(null, 1000.00,11,1, "Chicago","Illinois",200000,"Alice","Food","Tom","Hanks","M","Doctor",null));
        List<Transactions> userFound = repo.findByCustomerId(1);
        assertEquals(1000.00,userFound.get(0).getAmt());
    }
    @Test
    public void findTransactionsWithAmountGreaterThan(){
        repo.save(new Transactions(null, 1000.00,11,1, "Chicago","Illinois",200000,"Alice","Food","Tom","Hanks","M","Doctor",null));
        List<Transactions> userFound = repo.findTransactionsWithAmountGreaterThan(500.00);
        assertEquals(1000.00,userFound.get(0).getAmt());
    }
    @Test
    public void findTransactionsWithAmountLessThan(){
        repo.save(new Transactions(null, 1000.00,11,1, "Chicago","Illinois",200000,"Alice","Food","Tom","Hanks","M","Doctor",null));
        List<Transactions> userFound = repo.findTransactionsWithAmountLessThan(1500.00);
        assertEquals(1000.00,userFound.get(0).getAmt());
    }
    @Test
    public void findTransactionsWithAmountBetween(){
        repo.save(new Transactions(null, 1000.00,11,1, "Chicago","Illinois",200000,"Alice","Food","Tom","Hanks","M","Doctor",null));
        List<Transactions> userFound = repo.findTransactionsWithAmountBetween(500.00,1500.00);
        assertEquals(1000.00,userFound.get(0).getAmt());
    }

}
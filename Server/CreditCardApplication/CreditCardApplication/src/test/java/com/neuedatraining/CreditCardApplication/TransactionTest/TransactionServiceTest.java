package com.neuedatraining.CreditCardApplication.TransactionTest;



import com.neuedatraining.CreditCardApplication.entity.Transactions;
import com.neuedatraining.CreditCardApplication.exception.CardUserNotFoundException;
import com.neuedatraining.CreditCardApplication.repository.Transactionrepo;
import com.neuedatraining.CreditCardApplication.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionServiceTest {
    @Mock
    Transactionrepo repo;
    @InjectMocks
    TransactionService service;

    List<Transactions> transCollection;

    Transactions trans11,trans12,trans21,trans22,trans31,trans32;




    @BeforeEach
    public void firstBeforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    public void befEach(){
        trans11 = new Transactions(null,1000.00,11,1,"Los Angeles","California",200000,"Alice","Food","Tom","Tailor","M","Doctor",null) ;
        trans12 = new Transactions(null,2000.00,12,1,"Los Angeles","California",200000,"Alice","Medication","Tom","Tailor","M","Doctor",null) ;

        trans21 = new Transactions(null,2000.00,21,2,"Chicago","Illinois",150000,"Nicole","Food","Thomas","Robinson","M","Analyst",null) ;
        trans22 = new Transactions(null,2500.00,22,2,"Chicago","Illinois",150000,"Nicole","Medication","Thomas","Robinson","M","Analyst",null) ;

        trans31 = new Transactions(null,550.00,31,3,"Houston","Texas",130000,"Harry","Fuel","Bailey","Scott","F","Teacher",null) ;
        trans32 = new Transactions(null,1550.00,32,3,"Houston","Texas",130000,"Harry","Food","Bailey","Scott","F","Teacher",null) ;


        transCollection=Arrays.asList(trans11,trans12,trans21,trans22,trans31,trans32);
    }
    @Test
    public void getUsers(){
        when(repo.findAll()).thenReturn(transCollection);
        assertEquals(3,service.getUsers().size());
    }

    @Test
    public void findCardUserByName(){
        List<Transactions> Transactions1 = new ArrayList<>(Arrays.asList(trans11,trans22));
        when(repo.findByName("Tom", "Tailor")).thenReturn(Transactions1);
        List<Transactions> returnValue = service.findCardUsersByName("Tom", "Tailor");
        assertEquals(Transactions1, returnValue);

    }

    @Test
    public void findCardUsersByRegion(){
        List<Transactions> Transactions3 = new ArrayList<>(Arrays.asList(trans31,trans32));
        when(repo.findByCity("Houston")).thenReturn(Transactions3);
        List<Transactions> returnValue = service.findCardUsersByRegion("Houston");
        assertEquals(Transactions3, returnValue);

    }

    @Test
    public void findCardUsersByCustomerId() throws CardUserNotFoundException {
        List<Transactions> Transactions2 = new ArrayList<>(Arrays.asList(trans21,trans22));
        when(repo.findByCustomerId(2)).thenReturn(Transactions2);
        List<Transactions> returnValue = service.findCardUsersByCustomerId(2);
        assertEquals(Transactions2, returnValue);

    }
    @Test
    public void findTransactionsBetween() {
        List<Transactions> Transactions_between = new ArrayList<>(Arrays.asList(trans11,trans12,trans21,trans32));
        when(repo.findTransactionsWithAmountBetween(1000.00, 2000.00)).thenReturn(Transactions_between);
        List<Transactions> returnValue = service.findTransactionsBetween(1000.00, 2000.00);
        assertEquals(Transactions_between, returnValue);
    }

    @Test
    public void findTransactionsGreaterThan() {
        List<Transactions> Transactions_greater = new ArrayList<>(Arrays.asList(trans22));
        when(repo.findTransactionsWithAmountGreaterThan(2000.00)).thenReturn(Transactions_greater);
        List<Transactions> returnValue = service.findTransactionsGreaterThan(2000.00);
        assertEquals(Transactions_greater, returnValue);
    }
    @Test
    public void findTransactionsLesserThan() {
        List<Transactions> Transactions_lesser = new ArrayList<>(Arrays.asList(trans11,trans31,trans32));
        when(repo.findTransactionsWithAmountLessThan(2000.00)).thenReturn(Transactions_lesser);
        List<Transactions> returnValue = service.findTransactionsLesserThan(2000.00);
        assertEquals(Transactions_lesser, returnValue);
    }

}
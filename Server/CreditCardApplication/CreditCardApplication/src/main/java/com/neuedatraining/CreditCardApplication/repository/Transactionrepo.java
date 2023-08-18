package com.neuedatraining.CreditCardApplication.repository;

import com.neuedatraining.CreditCardApplication.entity.CreditCardUser;
import com.neuedatraining.CreditCardApplication.entity.Transactions;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface Transactionrepo extends MongoRepository<Transactions, ObjectId> {
    @Query("{ 'city' : ?0 }")
    public List<Transactions> findByCity(String city);

    @Query("{ 'first' : ?0, 'last': ?1 }")
    public List<Transactions> findByName(String fName,String lName);

    List<Transactions> findTop300By();
    @Query("{ 'customer_id' : ?0 }")
    public List<Transactions> findByCustomerId(int customer_id);
    @Query("{ 'amt' : { $gt : ?0 } }")
    List<Transactions> findTransactionsWithAmountGreaterThan(double amount);
    @Query("{ 'amt' : { $lt : ?0 } }")
    List<Transactions> findTransactionsWithAmountLessThan(double amount);
    @Query("{ 'amt' : { $gte : ?0, $lte : ?1 } }")
    List<Transactions> findTransactionsWithAmountBetween(double minAmount, double maxAmount);

}

package com.neuedatraining.CreditCardApplication.repository;

import com.neuedatraining.CreditCardApplication.entity.CreditCardUser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CreditCardUserRepo extends MongoRepository<CreditCardUser, ObjectId> {
// customised Query methods
    //convention-> find<entity name>By<property>
    @Query("{ 'first' : ?0, 'last': ?1 }")
    public CreditCardUser findByName(String fName,String lName);

    @Query("{ 'gender' : ?0 }")
    public List<CreditCardUser> findByGender(String gender);

    @Query("{ 'customer_id' : ?0 }")
    public List<CreditCardUser> findByCustomerId(int customer_id);
    boolean existsByCustomerId(int customer_id);
    CreditCardUser save(CreditCardUser user);
    void deleteByCustomerId(int customer_id);
}

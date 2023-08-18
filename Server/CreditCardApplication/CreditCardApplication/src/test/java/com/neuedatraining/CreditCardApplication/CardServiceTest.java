package com.neuedatraining.CreditCardApplication;

import com.neuedatraining.CreditCardApplication.entity.CreditCardUser;
import com.neuedatraining.CreditCardApplication.exception.CardUserAlreadyFoundException;
import com.neuedatraining.CreditCardApplication.exception.CardUserNotFoundException;
import com.neuedatraining.CreditCardApplication.repository.CreditCardUserRepo;
import com.neuedatraining.CreditCardApplication.service.CardUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;

import java.util.Arrays;
import java.util.List;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CardServiceTest {
    @Mock
    CreditCardUserRepo repo;

    @InjectMocks
    CardUserService service;

    List<CreditCardUser>   collection ;
    CreditCardUser user1 ,user2,user3;

    @BeforeEach
    public void firstBeforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    public void befEach(){
        user1 = new CreditCardUser(1, "Mary", "Kom", "F", "Boxer", null);
        user2 = new CreditCardUser(2, "Virat", "Kholi", "M", "Cricketer", null);
        user3 = new CreditCardUser(3, "Kamal", "Hassan", "M", "Actor", null);

        collection = Arrays.asList(user1,user2,user3);


    }
    @Test
    public void getAllUsers(){
        when(repo.findAll()).thenReturn(collection);

        assertEquals(3,service.getUsers().size());

    }

    @Test
    public void findCardUserByName() throws CardUserNotFoundException {

        when(repo.findByName("Virat", "Kohli")).thenReturn(user2);
        CreditCardUser retval = service.findCardUserByName("Virat", "Kohli");
        assertEquals(user2,retval);
    }

    @Test
    public void insertUser() throws CardUserAlreadyFoundException {

        when(repo.save(user1)).thenReturn(user1);

        service.insertUser(user1);
        assertEquals(4,collection.size()+1);
    }

    @Test
    public void  deleteUser() throws CardUserNotFoundException{
        when(repo.existsByCustomerId(2)).thenReturn(true);
        service.deleteUser(2);
        assertEquals(2, collection.size()-1);

    }

    @Test
    public void updateUser() throws CardUserNotFoundException{
        user2 = new CreditCardUser(2, "Virat", "Kholi", "M", "Businessman", null);
        when(repo.existsByCustomerId(2)).thenReturn(true);

        CreditCardUser updatedUser = service.updateUser(user2);

        assertEquals(user2.getJob(), updatedUser.getJob());
    }


}
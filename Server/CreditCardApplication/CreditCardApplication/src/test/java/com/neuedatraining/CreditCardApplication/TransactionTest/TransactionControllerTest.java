package com.neuedatraining.CreditCardApplication.TransactionTest;

import com.neuedatraining.CreditCardApplication.entity.Transactions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerTest {

    @Autowired
    TestRestTemplate template;

    @Test
    public void getAllUser(){

        ResponseEntity<List<Transactions>> rEntity = template.exchange("/creditcard", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Transactions>>() {
                });

        List<Transactions> resp = rEntity.getBody();
        assertEquals(HttpStatus.OK,rEntity.getStatusCode());
        assertEquals(983,resp.size());
    }

}
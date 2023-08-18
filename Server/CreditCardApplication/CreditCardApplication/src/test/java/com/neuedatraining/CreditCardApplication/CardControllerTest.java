package com.neuedatraining.CreditCardApplication;

import com.neuedatraining.CreditCardApplication.entity.CreditCardUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardControllerTest {
    @Autowired
    private TestRestTemplate template;

    @Test
    public void getAllUser(){

        ResponseEntity<List<CreditCardUser>> rEntity = template.exchange("/creditcard",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CreditCardUser>>() {
                });

        List<CreditCardUser> resp = rEntity.getBody();
        assertEquals(HttpStatus.OK,rEntity.getStatusCode());
        assertEquals(983,resp.size());



    }

    @Test
    public void insertUser() throws URISyntaxException {
        //TestRestTemplate template1 = new TestRestTemplate();
        URI uri =  new URI("http://localhost:8080/creditcard");

        CreditCardUser user = new CreditCardUser(999, "Albert", "Einstein", "M", "Scientist", null);

        ResponseEntity<CreditCardUser> response = template.postForEntity(uri,user, CreditCardUser.class);
        //CreditCardUser newUser = response.getBody();
        //assertEquals("Scientist",response.getBody().getJob());
        //assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        //assertThat(response.getBody()).isNotNull();
        //assertThat(response.getBody().getJob()).isEqualTo(user.getJob());
        assertEquals(user.getJob(),response.getBody().getJob());

    }

    @Test
    public void updateUser() throws URISyntaxException {
        // TestRestTemplate template1 = new TestRestTemplate();
        URI uri =  new URI("http://localhost:8080/creditcard");

        CreditCardUser user = new CreditCardUser(1024, "Vishwanath","Anand","M","ChessPlayer",null);
        ResponseEntity<String> responseEntity = template.exchange(
                uri, HttpMethod.PUT,
                new HttpEntity<>(user), String.class);

        assertThat(responseEntity.getBody()).isEqualTo("User with "+ user.getCustomerId()+ " does not exist");

    }

    @Test
    public void deleteUser() throws URISyntaxException {
        TestRestTemplate template1 = new TestRestTemplate();
        URI uri =  new URI("http://localhost:8080/creditcard/999");

        ResponseEntity<String> response = template1.exchange(uri,HttpMethod.DELETE, null,String.class);
        //assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User with this customer ID not found",response.getBody());
    }

}
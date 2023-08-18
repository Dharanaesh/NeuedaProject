package com.neuedatraining.CreditCardApplication.controller;

import com.neuedatraining.CreditCardApplication.dto.UserPerPageResponse;
import com.neuedatraining.CreditCardApplication.entity.CreditCardUser;
import com.neuedatraining.CreditCardApplication.exception.CardUserAlreadyFoundException;
import com.neuedatraining.CreditCardApplication.exception.CardUserNotFoundException;
import com.neuedatraining.CreditCardApplication.service.CardUserService;
import com.neuedatraining.CreditCardApplication.utility.StatusMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/creditcard")
public class CreditCardUserController {
    @Autowired
    private CardUserService service;
    @GetMapping
    public List<CreditCardUser> getAllUser() {
        return  this.service.getUsers();
  }
    @GetMapping("/{customer_id}")
    public ResponseEntity<List<CreditCardUser>> getUserById(@PathVariable int customer_id) {
        try {
            List<CreditCardUser> users = service.findCardUserByCustomerId(customer_id);
            return ResponseEntity.ok(users);
        } catch (CardUserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/mapName")
    public ResponseEntity<CreditCardUser> getUsersByFName(@RequestParam("first") String first, @RequestParam("last") String last) {
        CreditCardUser user = service.findCardUserByName(first, last);
        if (user != null) {
            return ResponseEntity.ok(user); // Return the user with 200 status code
        } else {
            return ResponseEntity.notFound().build(); // Return 404 status code
        }
    }
    @GetMapping("/mapGender")
    public List<CreditCardUser> getUsersByGender(@RequestParam("gender") String gender) {
        if(gender == null)
            return this.service.getUsers();
        return  this.service.findCardUserByGender(gender);
    }
    @PostMapping
    public ResponseEntity<Object> insertUser(@RequestBody CreditCardUser creditCardUser) {
        try {
            CreditCardUser user1 = this.service.insertUser(creditCardUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(user1);
        } catch (CardUserAlreadyFoundException e) {
            String errorMessage = "Card user with " + creditCardUser.getCustomerId() + " already exists";
            Map<String, Object> response = new HashMap<>();
            response.put("error", errorMessage);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody CreditCardUser creditCardUser) {
        try {
            CreditCardUser user1 =this.service.updateUser(creditCardUser);
            return ResponseEntity.ok(user1);
        } catch (CardUserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{customer_id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int customer_id) {
        Map<StatusMessages, String> map =new HashMap<>();
        try {
            map.put(StatusMessages.SUCCESS,"Employee Deleted Successfully");
            service.deleteUser(customer_id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
        } catch (CardUserNotFoundException e) {
            map.put(StatusMessages.FAILURE,e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(map);
        }
    }
    @GetMapping("/pages")
    public UserPerPageResponse getUsersByPage(
            @RequestParam(required = false, defaultValue = "0") int pageno,
            @RequestParam(required = false, defaultValue = "20") int size
    ) {
        return service.getUsersByPagination(pageno, size);
    }

}
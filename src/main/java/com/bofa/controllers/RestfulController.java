package com.bofa.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bofa.models.Confirmation;
import com.bofa.models.Trade;
import com.bofa.models.User;

@RestController
public class RestfulController {

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers(User user) {
        return new ArrayList<>(
            Arrays.asList(
                new User(1L, "John", 11), 
                new User(2L, "Mary", 21)));
    }

    @GetMapping(value = "/user/{identity}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable("identity") long id) {
        return new User(id, "Kate", 5);
    }

    @PostMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trade> post(@RequestBody Trade trade) {
        HttpHeaders header = new HttpHeaders();
        return new ResponseEntity<Trade>(trade, header, HttpStatus.OK);
    }

    @PostMapping(value = "/confirmation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Confirmation> httpClient(@RequestBody Trade trade) {
        RestTemplate client = new RestTemplate();
        client.postForEntity(null, client, null, null)
        HttpHeaders header = new HttpHeaders();
        return new ResponseEntity<Confirmation>(null, header, HttpStatus.OK);
    }
}

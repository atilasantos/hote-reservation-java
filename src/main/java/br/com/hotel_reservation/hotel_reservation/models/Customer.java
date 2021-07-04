package br.com.hotel_reservation.hotel_reservation.models;

import br.com.hotel_reservation.hotel_reservation.exceptions.InvalidInputFormatException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnore
    static final String emailRegex = "^(.+)@(.+).(.+)$";
    @JsonIgnore
    static Pattern pattern = Pattern.compile(emailRegex);

    public Customer(
            @JsonProperty("first_name") String firstName,
            @JsonProperty("last_name") String lastName,
            @JsonProperty("e-mail") String email) throws InvalidInputFormatException {
        this.firstName = firstName;
        this.lastName = lastName;
        if(pattern.matcher(email).matches()){
            this.email = email;
        }else{
            throw new InvalidInputFormatException("Invalid input for e-mail field: must have a '@' and '.com' in the end");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName
                + " , Last Name: " + lastName
                + " , Email: " + email;
    }
}

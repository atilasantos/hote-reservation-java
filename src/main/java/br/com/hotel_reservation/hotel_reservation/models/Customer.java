package br.com.hotel_reservation.hotel_reservation.models;

import br.com.hotel_reservation.hotel_reservation.exceptions.InvalidInputFormatException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    static final String emailRegex = "^(.+)@(.+).(.+)$";
    static Pattern pattern = Pattern.compile(emailRegex);

    public Customer(String firstName, String lastName, String email) throws InvalidInputFormatException {
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

    public String getEmailRegex() {
        return emailRegex;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName
                + " , Last Name: " + lastName
                + " , Email: " + email;
    }
}

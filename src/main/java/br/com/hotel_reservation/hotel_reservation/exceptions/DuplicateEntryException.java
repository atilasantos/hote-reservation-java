package br.com.hotel_reservation.hotel_reservation.exceptions;

public class DuplicateEntryException extends Exception{

    public DuplicateEntryException(String message){
        super(message);
    }
}

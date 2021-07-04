package br.com.hotel_reservation.hotel_reservation.models;

import org.springframework.security.core.GrantedAuthority;

public class Profile implements GrantedAuthority {

    private Long id = null;
    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }


}

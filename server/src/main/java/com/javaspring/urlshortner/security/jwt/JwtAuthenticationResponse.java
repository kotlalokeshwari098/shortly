package com.javaspring.urlshortner.security.jwt;


import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String token;
    String jwt;

}

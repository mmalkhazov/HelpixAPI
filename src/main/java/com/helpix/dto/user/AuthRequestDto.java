package com.helpix.dto.user;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder


public class AuthRequestDto {

    private String authProvider;
    private String accessToken;
    private String idToken;
    private String userAgent;
    private String clientId;




}



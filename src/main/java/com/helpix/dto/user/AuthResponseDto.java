package com.helpix.dto.user;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class AuthResponseDto {



    private String accessToken;
    private String refreshToken;
    private int expiresIn;


}

package com.helpix.dto.user;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class RefreshRequestDto {

    private String refreshToken;
    private String userAgent;
    private String clientId;

}

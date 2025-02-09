package com.helpix.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class UserResponseDto {

    private String username;
    private String createdAt;
    private String email;
    private int userId;
    private String pictureUrl;

}

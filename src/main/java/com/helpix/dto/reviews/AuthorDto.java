package com.helpix.dto.reviews;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class AuthorDto {

    public String username;
    public String createdAt;
    public String email;
    public int userId;
    public String pictureUrl;
}

package com.helpix.dto.reviews;

import com.helpix.dto.listings.TranslationDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@Builder

public class ReviewResponseDto {
    private AuthorDto author;
    private int id;
    private int rating;
    private String comment;
}

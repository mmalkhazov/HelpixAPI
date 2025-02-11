package com.helpix.dto.reviews;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class ReviewRequestDto {
    private int rating;
    private String comment;


}

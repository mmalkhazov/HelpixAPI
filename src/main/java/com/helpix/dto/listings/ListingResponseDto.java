package com.helpix.dto.listings;

//import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpix.dto.user.UserResponseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@Builder
public class ListingResponseDto {

    private	int	id;
    private UserResponseDto author;
    private	String	slug;
    private	List<TranslationDto> translation;
    private List<ImageDto> images;
    private	String	createdAt;
    private	String	updatedAt;
    private	String	contactPhone;
    private	String	contactEmail;
    private	String	location;
    private	String	address;
    private	BigDecimal	price;
    private	String	currencyCode;
    private BigDecimal priceUsd;
    private	int	paymentType;
    private	int	categoryId;
    private	boolean	active;


}

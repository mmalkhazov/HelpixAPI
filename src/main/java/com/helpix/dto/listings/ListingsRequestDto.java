package com.helpix.dto.listings;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@Builder
public class ListingsRequestDto {

    private int categoryId;
    private String contactPhone;
    private String contactEmail;
    private String websiteUrl;
    private String location;
    private String mapLink;
    private String mapEmbedLink;
    private String mapProvider;
    private String address;
    private double price;
    private String currencyCode;
    private double priceUsd;
    private int paymentType;
    private List<TranslationDto> translations;
    private List<ImageDto> images;


}

package com.helpix.utils;

import com.helpix.dto.listings.ImageDto;
import com.helpix.dto.listings.ListingsRequestDto;
import com.helpix.dto.listings.TranslationDto;
import com.helpix.dto.reviews.ReviewRequestDto;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class TestDataFactory {

    public static ListingsRequestDto CreateValidListingsRequest() {
        return ListingsRequestDto.builder()
                .categoryId(4)
                .contactPhone("+491117777777")
                .contactEmail("Category_04@example.com")
                .websiteUrl("https://www.example.com")
                .location("New York, USA")
                .mapLink("https://goo.gl/maps/example")
                .mapEmbedLink("<iframe src='https://www.google.com/maps/embed?pb=example'/>")
                .mapProvider("Google")
                .address("123")
                .price(3000)
                .currencyCode("USD")
                .priceUsd(199.99)
                .paymentType(1)
                .translations(createDefaultTranslation())
                .images(createDefaultImage())
                .build();
    }

    public static ListingsRequestDto CreateInvalidListingsRequest() {
        return ListingsRequestDto.builder()
                .categoryId(4)
                .contactPhone("+49111777bhgh7")
                .contactEmail("Category_04example.com")
                .websiteUrl("https://www.example.com")
                .location("New York, USA")
                .mapLink("https://goo.gl/maps/example")
                .mapEmbedLink("<iframe src='https://www.google.com/maps/embed?pb=example'/>")
                .mapProvider("Google")
                .address("123")
                .price(3000)
                .currencyCode("USD")
                .priceUsd(199.99)
                .paymentType(1)
                .translations(createDefaultTranslation())
                .images(createDefaultImage())
                .build();
    }

    public static ListingsRequestDto UpdatedListingsRequest() {
        return ListingsRequestDto.builder()
                .categoryId(4)
                .contactPhone("+49111888")
                .contactEmail("Category_77@example.com")
                .websiteUrl("https://www.example.com")
                .location("LA, USA")
                .mapLink("https://goo.gl/maps/example")
                .mapEmbedLink("<iframe src='https://www.google.com/maps/embed?pb=example'/>")
                .mapProvider("Google")
                .address("123")
                .price(3000)
                .currencyCode("USD")
                .priceUsd(199.99)
                .paymentType(1)
                .translations(createDefaultTranslation())
                .images(createDefaultImage())
                .build();
    }
    public static ListingsRequestDto UpdatedListingsRequestWithInvalidCategoryId() {
        return ListingsRequestDto.builder()
                .categoryId(444)
                .contactPhone("+491117777777")
                .contactEmail("Category_04@example.com")
                .websiteUrl("https://www.example.com")
                .location("LA, USA")
                .mapLink("https://goo.gl/maps/example")
                .mapEmbedLink("<iframe src='https://www.google.com/maps/embed?pb=example'/>")
                .mapProvider("Google")
                .address("123")
                .price(3000)
                .currencyCode("USD")
                .priceUsd(199.99)
                .paymentType(1)
                .translations(createDefaultTranslation())
                .images(createDefaultImage())
                .build();
    }

    public static List<TranslationDto> createDefaultTranslation() {
        return Arrays.asList(
                TranslationDto.builder()
                        .languageCode("en")
                        .title("Product Title")
                        .description("This is the product description.")
                        .isDefault(true)
                        .build()
        );
    }


    public static List<ImageDto> createDefaultImage() {
        return Arrays.asList(
                ImageDto.builder()
                        .imageUrl("https://example.com/image.jpg")
                        .isPrimary(true)
                        .build()
        );
    }

    public static ReviewRequestDto CreateValidReviewRequest() {
        return ReviewRequestDto.builder()
                .rating(4)
                .comment("Great product!")
                .build();
    }
    public static ReviewRequestDto CreateReviewRequestWithInvalidRating() {
        return ReviewRequestDto.builder()
                .rating(7)
                .comment("Great product!")
                .build();
    }
    public static ReviewRequestDto CreateReviewRequestWithInvalidComment() {
        return ReviewRequestDto.builder()
                .rating(4)
                .comment("I hate you")
                .build();
    }

    public static ReviewRequestDto UpdatedValidReviewRequest() {
        return ReviewRequestDto.builder()
                .rating(1)
                .comment("Not a great product!")
                .build();
    }





}

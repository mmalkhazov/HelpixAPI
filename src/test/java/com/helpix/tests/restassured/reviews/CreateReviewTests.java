package com.helpix.tests.restassured.reviews;

import com.helpix.dto.listings.ImageDto;
import com.helpix.dto.listings.ListingResponseDto;
import com.helpix.dto.listings.ListingsRequestDto;
import com.helpix.dto.listings.TranslationDto;
import com.helpix.dto.reviews.AuthorDto;
import com.helpix.dto.reviews.ReviewRequestDto;
import com.helpix.dto.reviews.ReviewResponseDto;
import com.helpix.tests.restassured.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertEquals;

public class CreateReviewTests extends TestBase {

//    We use this variable to store the created Listing ID in precondition
//    and use it in DeleteListingTest()
    private static Integer savedListingId;

//    We use this precondition, for ensuring, that the Listing is created,

    @BeforeMethod
    public void precondition() {
        List<TranslationDto> translations = Arrays.asList(
                TranslationDto.builder()
                        .languageCode("en")
                        .title("Product Title")
                        .description("This is the product description.")
                        .isDefault(true)
                        .build()
        );

        List<ImageDto> images = Arrays.asList(
                ImageDto.builder()
                        .imageUrl("https://example.com/image.jpg")
                        .isPrimary(true)
                        .build()
        );


        ListingsRequestDto listing = ListingsRequestDto.builder()
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
                .translations(translations)
                .images(images)
                .build();


        ListingResponseDto responseDto = given()
                .contentType(ContentType.JSON)
                .body(listing)
                .header("Authorization", "Bearer " + getSavedLocalAccessToken())
                .when()
                .post("/listings")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .extract()
                .as(ListingResponseDto.class);

        assertEquals(responseDto.getAuthor().getUsername(), "Bob Carter", "Username does not match!");

        savedListingId = responseDto.getId();
//        System.out.println("The Listing ID: " + savedListingId);

    }
    @Test
    public void CreateReviewTest() {

        ReviewRequestDto review = ReviewRequestDto.builder()
                .rating(4)
                .comment("Great product!")
                .build();


        ReviewResponseDto responseDto = given()
                .contentType(ContentType.JSON)
                .body(review)
                .header("Authorization", "Bearer " + getSavedLocalAccessToken())
                .when()
                .post("/reviews/listings/" + savedListingId)
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .extract()
                .as(ReviewResponseDto.class);

        assertEquals(responseDto.getRating(),4);
        assertEquals(responseDto.getComment(), "Great product!", "Review comment does not match!");

//        System.out.println("Created Listing: " + responseDto);

    }
}

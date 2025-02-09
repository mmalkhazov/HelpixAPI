package com.helpix.tests.restassured.listings;

import com.helpix.dto.listings.ImageDto;
import com.helpix.dto.listings.ListingResponseDto;
import com.helpix.dto.listings.ListingsRequestDto;
import com.helpix.dto.listings.TranslationDto;
import com.helpix.tests.restassured.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertEquals;

public class CreateListingsTests extends TestBase {


    @Test
    public void CreateListingTest() {
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

//        System.out.println("Created Listing: " + responseDto);

    }


    @Test
    public void CreateListingWithInvalidTokenTest() {
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


        Response response = given()
                .contentType(ContentType.JSON)
                .body(listing)
                .header("Authorization", "Bearer " + LOCAL_INVALID_ACCESS_TOKEN)
                .when()
                .post("/listings");

        response.then().statusCode(403);

//            assertEquals(responseDto.getAuthor().getUsername(), "Bob Carter", "Username does not match!");

//        System.out.println("Созданный листинг: " + responseDto);

    }


}

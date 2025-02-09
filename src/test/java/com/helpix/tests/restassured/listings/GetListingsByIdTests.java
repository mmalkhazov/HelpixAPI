package com.helpix.tests.restassured.listings;


import com.helpix.dto.listings.ListingResponseDto;
import com.helpix.tests.restassured.TestBase;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class GetListingsByIdTests extends TestBase {


    @Test
    public void GetListingByIdTest() {
        ListingResponseDto responseDto = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getSavedLocalAccessToken())
                .when()
                .get("/listings/13")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .extract()
                .as(ListingResponseDto.class);


        Assert.assertEquals(responseDto.getAuthor().getUsername(), "Bob Carter", "Username does not match!");


    }


}

package com.helpix.tests.restassured.listings;

import com.helpix.dto.listings.ListingResponseDto;
import com.helpix.tests.restassured.TestBase;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class GetAllListingsOfUserTests extends TestBase {


    @Test
    public void GetAllListingOfUserTest() {
        List<ListingResponseDto> responseList = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getSavedLocalAccessToken())
                .when()
                .get("/listings/me")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList("", ListingResponseDto.class);

//        System.out.println(responseList);
        Assert.assertEquals(responseList.get(0).getAuthor().getUsername(), "Bob Carter", "Username does not match!");


    }

}

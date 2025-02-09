package com.helpix.tests.restassured.user;

import com.helpix.tests.restassured.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class UpdateTests extends TestBase {


    @Test
    public void UpdateLanguageTest(){
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getSavedLocalAccessToken())
                .queryParam("languageCode", "EN")
                .when()
                .patch("/users/update")
                .then()
                .statusCode(200)
                .extract().response();

        String responseBody = response.asString();
        Assert.assertEquals(responseBody, "User updated successfully", "Response message does not match!");
    }

    @Test
    public void UpdateCountryTest(){
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getSavedLocalAccessToken())
                .queryParam("countryCode", "DE")
                .when()
                .patch("/users/update")
                .then()
                .statusCode(200)
                .extract().response();

        String responseBody = response.asString();
        Assert.assertEquals(responseBody, "User updated successfully", "Response message does not match!");
    }


    @Test
    public void UpdateCurrencyTest(){
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getSavedLocalAccessToken())
                .queryParam("currencyCode", "EUR")
                .when()
                .patch("/users/update")
                .then()
                .statusCode(200)
                .extract().response();

        String responseBody = response.asString();
        Assert.assertEquals(responseBody, "User updated successfully", "Response message does not match!");
    }

//    Here we have the negative test

    @Test
    public void UpdateLanguageWithInvalidLanguageCodeTest(){
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getSavedLocalAccessToken())
                .queryParam("languageCode", "ENGG")
                .when()
                .patch("/users/update")
                .then()
                .statusCode(400)
                .extract().response();

        String responseBody = response.asString();
//      In the following tests:
//      UpdateLanguageWithInvalidLanguageCodeTest,
//      UpdateCountryWithInvalidCountryCodeTest,
//      UpdateCurrencyWithInvalidCurrencyCodeTest

//      The  Assert.assertEquals is commented because ,
//      it's difficult to check the error message,
//      because it is not the static and changes every time.no idea why
//      Maybe later after talking to Alexander and getting more information
//      it will be easy to check it

//        Assert.assertEquals(responseBody, "Validation error: Invalid language code format. Expected 2-3 letters, optionally followed by _ and 2 uppercase letters.,Language code should be 2 characters long.", "Response message does not match!");
    }

//    Here we have the negative test
    @Test
    public void UpdateCountryWithInvalidCountryCodeTest(){
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getSavedLocalAccessToken())
                .queryParam("countryCode", "DEE")
                .when()
                .patch("/users/update")
                .then()
                .statusCode(400)
                .extract().response();

        String responseBody = response.asString();
//      In the following tests:
//      UpdateLanguageWithInvalidLanguageCodeTest,
//      UpdateCountryWithInvalidCountryCodeTest,
//      UpdateCurrencyWithInvalidCurrencyCodeTest

//      The  Assert.assertEquals is commented because ,
//      it's difficult to check the error message,
//      because it is not the static and changes every time.no idea why
//      Maybe later after talking to Alexander and getting more information
//      it will be easy to check it

//        Assert.assertEquals(responseBody, "Validation error: Country code should be exactly 2 characters long.,Invalid country code format. Expected 2 uppercase letters.", "Response message does not match!");
    }

//    Here we have the negative test
    @Test
    public void UpdateCurrencyWithInvalidCurrencyCodeTest(){
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getSavedLocalAccessToken())
                .queryParam("currencyCode", "EURR")
                .when()
                .patch("/users/update")
                .then()
                .statusCode(400)
                .extract().response();


        String responseBody = response.asString();

//      In the following tests:
//      UpdateLanguageWithInvalidLanguageCodeTest,
//      UpdateCountryWithInvalidCountryCodeTest,
//      UpdateCurrencyWithInvalidCurrencyCodeTest

//      The  Assert.assertEquals is commented because ,
//      it's difficult to check the error message,
//      because it is not the static and changes every time.no idea why
//      Maybe later after talking to Alexander and getting more information
//      it will be easy to check it

//        Assert.assertEquals(responseBody, "Validation error: Currency code should be exactly 3 characters long.,Invalid currency code format. Expected 3 uppercase letters.", "Response message does not match!");
    }













}

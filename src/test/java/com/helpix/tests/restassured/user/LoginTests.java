package com.helpix.tests.restassured.user;

import com.helpix.dto.user.AuthRequestDto;
import com.helpix.dto.user.AuthResponseDto;
import com.helpix.tests.restassured.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class LoginTests extends TestBase {


    @Test
    public void AuthorizationWithValidCredentials() {
        AuthRequestDto auth = AuthRequestDto.builder()
                .authProvider(AUTH_PROVIDER)
                .accessToken(GOOGLE_ACCESS_TOKEN)
                .idToken(GOOGLE_ID_TOKEN)
                .userAgent(USER_AGENT)
                .clientId(CLIENT_ID)
                .build();


        AuthResponseDto responseDto = given()
                .contentType(ContentType.JSON)
                .body(auth)
                .when()
                .post("/users/auth")
                .then()
                .statusCode(200)
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue())
                .extract()
                .as(AuthResponseDto.class);


        System.out.println("Local Access Token: " + responseDto.getAccessToken());
        System.out.println("Local Refresh Token: " + responseDto.getRefreshToken());
        System.out.println("Expires in: " + responseDto.getExpiresIn());
        saveLocalAccessToken(responseDto.getAccessToken());
        saveLocalRefreshToken(responseDto.getRefreshToken());

    }


////    @Test
//    public void AuthorizationWithValidCredentials() throws IOException {
//        AuthRequestDto auth = AuthRequestDto.builder()
//                .authProvider(AUTH_PROVIDER)
//                .accessToken(GOOGLE_ACCESS_TOKEN)
//                .idToken(GOOGLE_ID_TOKEN)
//                .userAgent(USER_AGENT)
//                .clientId(CLIENT_ID)
//                .build();
//
//
//        AuthResponseDto responseDto = given()
//                .contentType(ContentType.JSON)
//                .body(auth)
//                .when()
//                .post("/users/auth")
//                .then()
//                .statusCode(200)
//                .body("accessToken", notNullValue())
//                .body("refreshToken", notNullValue())
//                .extract()
//                .as(AuthResponseDto.class);
//
//
//        System.out.println("Local Access Token: " + responseDto.getAccessToken());
//        System.out.println("Local Refresh Token: " + responseDto.getRefreshToken());
//        saveLocalAccessToken(responseDto.getAccessToken());
//        saveLocalRefreshToken(responseDto.getRefreshToken());
//
//    }


    @Test
    public void AuthorizationWithInvalidIdToken() throws IOException {
        AuthRequestDto auth = AuthRequestDto.builder()
                .authProvider(AUTH_PROVIDER)
                .accessToken(GOOGLE_ACCESS_TOKEN)
                .idToken(GOOGLE_INVALID_ID_TOKEN)
                .userAgent(USER_AGENT)
                .clientId(CLIENT_ID)
                .build();


        Response response = given()
                .contentType(ContentType.JSON)
                .body(auth)
                .when()
                .post("/users/auth")
                .then()
                .statusCode(400)
                .extract().response();

        String responseBody = response.getBody().asString();
        System.out.println("Raw Response: " + responseBody);
        Assert.assertEquals(responseBody, "Invalid ID token received");

    }
}

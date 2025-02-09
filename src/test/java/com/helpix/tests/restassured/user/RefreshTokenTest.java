package com.helpix.tests.restassured.user;

import com.helpix.dto.user.AuthResponseDto;
import com.helpix.dto.user.RefreshRequestDto;
import com.helpix.tests.restassured.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class RefreshTokenTest extends TestBase {

    @Test
    public void RefreshTokenTest(){

        RefreshRequestDto request= RefreshRequestDto.builder()
                .refreshToken(savedLocalRefreshToken)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                .clientId("client1")
                .build();

        AuthResponseDto responseDto = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/users/auth")
                .then()
                .statusCode(200)
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue())
                .extract()
                .as(AuthResponseDto.class);
    }
}

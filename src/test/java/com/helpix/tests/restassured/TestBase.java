package com.helpix.tests.restassured;

import com.helpix.fw.BaseHelper;
import com.helpix.dto.user.AuthRequestDto;
import com.helpix.dto.user.AuthResponseDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class TestBase {
    private BaseHelper baseHelper;
    private AuthResponseDto responseDto;
    public static final String AUTH_PROVIDER= "google";
    public static final String GOOGLE_ACCESS_TOKEN = "";
    public static final String GOOGLE_ID_TOKEN = "";
    public static final String USER_AGENT = "test.test";
    public static final String CLIENT_ID = "";
    public static final String GOOGLE_INVALID_ID_TOKEN = "";
    public static final String LOCAL_INVALID_ACCESS_TOKEN = "";

    @BeforeMethod
    public void ensureAuthenticated() throws IOException {
        if (savedLocalAccessToken == null) {
            System.out.println("Token missing! Performing authentication.");
            AuthorizationWithValidCredentials();
        } else {
            System.out.println("Using saved token.");
        }
    }
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://api-preview.helpix.io";
    }

    public static String savedLocalAccessToken;
    public static String savedLocalRefreshToken;

    public void saveLocalAccessToken(String token) {
        savedLocalAccessToken = token;
    }


    public void saveLocalRefreshToken(String token) {
        savedLocalRefreshToken = token;
    }

    public static String getSavedLocalAccessToken() {
        return savedLocalAccessToken;
    }

    public static String getSavedLocalRefreshToken() {
        return savedLocalRefreshToken;
    }


//    private static String savedLocalAccessToken;
//    private static String savedLocalRefreshToken;
//    protected BaseHelper baseHelper; // BaseHelper instance

    public void AuthorizationWithValidCredentials() throws IOException {
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
        saveLocalAccessToken(responseDto.getAccessToken());
        saveLocalRefreshToken(responseDto.getRefreshToken());
        baseHelper = new BaseHelper(responseDto.getAccessToken());
    }



    }




package com.helpix.tests.restassured;

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

//TODO
// 1#Issue
//  Here stored the variables, which are used for Google authorization,
//  after which we receive the Local Access and Refresh tokens
//  The problem is that the values for GOOGLE_ID_TOKEN is stored manually
//  and every time we must get from Postman the GOOGLE_ID_TOKEN and  copy/paste here
//  which is not ok. Can you help us with this automation this process?
    public static final String AUTH_PROVIDER= "google";
    public static final String GOOGLE_ACCESS_TOKEN = "ya29.a0AXeO80Qo0y_LMZFLEpHMA3mLoBV9oqJMACAE--ZBUiqeFGD1Ns2e1k-8tep7tCSXKXFoH1nqNls-NElRimmWZPDwotNHwdYQtGosOtL0HqWqEfKe-ZHYtb8OsqsKnkcw44baSPKXHQ8_B4HuabS4aOCtDXcKAV9Ms5iqdv2vaCgYKAXASAQ8SFQHGX2Mi6kXNNPV58mGJrGuL321JVw0175";
    public static final String GOOGLE_ID_TOKEN = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImVlYzUzNGZhNWI4Y2FjYTIwMWNhOGQwZmY5NmI1NGM1NjIyMTBkMWUiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI1NzE4MDA1NzU4NTctZm51Z2tuanU0Y3FhZjg0cXQyZWdtZThpNjhiY2V1MzcuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI1NzE4MDA1NzU4NTctZm51Z2tuanU0Y3FhZjg0cXQyZWdtZThpNjhiY2V1MzcuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDQ1MzI4NTAzNDU2Nzg4OTY5MDYiLCJlbWFpbCI6ImJvYi5jYXJ0ZXIudGVzdGVyQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhdF9oYXNoIjoic01GMTdsTzlHd3dOZUZQOE5JMlhfdyIsIm5hbWUiOiJCb2IgQ2FydGVyIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0xOcEY2M2xOM19sbEJxNzNsSW9Vb083b1M0MTBoWGJZXzUzSElWUzNxbHNSMkhtZz1zOTYtYyIsImdpdmVuX25hbWUiOiJCb2IiLCJmYW1pbHlfbmFtZSI6IkNhcnRlciIsImlhdCI6MTczOTExNDI1MywiZXhwIjoxNzM5MTE3ODUzfQ.PNbXwHtO1EtgLRVT8o1U2Pl-2qyQ1orv6V48c5iRr6iCgCIm61JgRngtqoCS5GbEwAQiccntnIiXEbP5JmWFyO5YCbnnBTN6bKoUgGBOllGnGKWZiKl3nvzPw-tfRJd9gLvqPR3dGPSyCD-v9k1KJ5gnMn0jvdpe2v6BxIWGz2UYCXjAKCFuWo_KLIKRDLnF_U0V1lbivtWvlDFfDyxWrAbEyqhdeHdUZMH_Yd-JE7dwvRzSl6N_tffOAjSyUNURH6rrPxVySIftK-i3ljS5yE3PZG36W0N0b4b1AwzNX4QJzRyXWmUMRmreu1yTbM8tkY17bkltEc1RsIINWsu2Xw";
    public static final String USER_AGENT = "test";
    public static final String CLIENT_ID = "test.test";
    public static final String GOOGLE_INVALID_ID_TOKEN = "yJhbGciOiJSUzI1NiIsImtpZCI6ImVlYzUzNGZhNWI4Y2FjYTIwMWNhOGQwZmY5NmI1NGM1NjIyMTBkMWUiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI1NzE4MDA1NzU4NTctZm51Z2tuanU0Y3FhZjg0cXQyZWdtZThpNjhiY2V1MzcuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI1NzE4MDA1NzU4NTctZm51Z2tuanU0Y3FhZjg0cXQyZWdtZThpNjhiY2V1MzcuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDQ1MzI4NTAzNDU2Nzg4OTY5MDYiLCJlbWFpbCI6ImJvYi5jYXJ0ZXIudGVzdGVyQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhdF9oYXNoIjoidmRRSnVoRl9zVnd3REZnN2ZvWW5kUSIsIm5hbWUiOiJCb2IgQ2FydGVyIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0xOcEY2M2xOM19sbEJxNzNsSW9Vb083b1M0MTBoWGJZXzUzSElWUzNxbHNSMkhtZz1zOTYtYyIsImdpdmVuX25hbWUiOiJCb2IiLCJmYW1pbHlfbmFtZSI6IkNhcnRlciIsImlhdCI6MTczODk1MjgwMywiZXhwIjoxNzM4OTU2NDAzfQ.FtyzTL2oiviR7wSQ1qcaBBKPxB_Tox5jPB9VDJKbrD6yGlCmyDXAnvWCbPJcdELewk79xFTOdMZA-IpplC5nyL1pnIXj7WDeq5ie7SKi6-5aKJMDA5UysTlJa_gpD_qElqqXxzccY-DnopLlsgjwdssmEHwPli0dfkagnPgGVjlpd5J5IqHwbY_Y7OVx0DBQ51QbjPP5EG7XIk5PKNvr_HgbMHLs8sZqFapErLFB2a2oa6rRGu2wJ76HQY9a1mt9lgPs3NijK44lkk3tM0eqy6JJMUGmAFLRn_8YZHGIAToPvwOhk1domAcqB-waGLFrPiN4lg3MIXGqIJnDVkYFaw";
    public static final String LOCAL_INVALID_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJWb2xvZHlteXIgU3RpbiIsImV4cCI6MTczNzY0MDU4MywibmFtZSI6IlZvbG9keW15ciBTdGluIiwiaWQiOjF9.Nf6ZJFUXFS0yknmec5tJ6_zPiftvT36ICS3S4XrsbG8";


    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://api-preview.helpix.io";
    }

    public static String savedLocalAccessToken;
    public static String savedLocalRefreshToken;

    protected void saveLocalAccessToken(String token) {
        savedLocalAccessToken = token;
    }


    protected void saveLocalRefreshToken(String token) {
        savedLocalRefreshToken = token;
    }

    protected String getSavedLocalAccessToken() {
        return savedLocalAccessToken;
    }

    protected String getSavedLocalRefreshToken() {
        return savedLocalRefreshToken;
    }
//TODO
// 2#Issue
// How to store the Access and Refresh Local Tokens,
// which we receive after AuthorizationWithValidCredentials()
// and are used for other Tests
// (.header("Authorization", "Bearer " + + getSavedLocalAccessToken())?
// The first decision was to create variable savedLocalAccessToken
// then write functions:
// saveLocalAccessToken() to save
// and getSavedLocalAccessToken() to retrieve it
// (these functions are used in the end of AuthorizationWithValidCredentials())
// here we get it but it doesn't save and it's impossible to use it in other tests
// The second decision to write @BeforeMethod, which is executed every time before the tests
// But as result it slows the speed of the tests

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

    }


    @BeforeMethod
    public void ensureAuthenticated() throws IOException {
        if (savedLocalAccessToken == null && savedLocalRefreshToken == null) {
            System.out.println(" Token missing! Perform authentication.");
            AuthorizationWithValidCredentials();
        } else {
            System.out.println(" The saved token is used.");
        }
    }
    }




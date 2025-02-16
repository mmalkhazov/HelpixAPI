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
    public static final String GOOGLE_ACCESS_TOKEN = "ya29.a0AXeO80Qo0y_LMZFLEpHMA3mLoBV9oqJMACAE--ZBUiqeFGD1Ns2e1k-8tep7tCSXKXFoH1nqNls-NElRimmWZPDwotNHwdYQtGosOtL0HqWqEfKe-ZHYtb8OsqsKnkcw44baSPKXHQ8_B4HuabS4aOCtDXcKAV9Ms5iqdv2vaCgYKAXASAQ8SFQHGX2Mi6kXNNPV58mGJrGuL321JVw0175";
    public static final String GOOGLE_ID_TOKEN = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjVkMTJhYjc4MmNiNjA5NjI4NWY2OWU0OGFlYTk5MDc5YmI1OWNiODYiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI1NzE4MDA1NzU4NTctZm51Z2tuanU0Y3FhZjg0cXQyZWdtZThpNjhiY2V1MzcuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI1NzE4MDA1NzU4NTctZm51Z2tuanU0Y3FhZjg0cXQyZWdtZThpNjhiY2V1MzcuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDQ1MzI4NTAzNDU2Nzg4OTY5MDYiLCJlbWFpbCI6ImJvYi5jYXJ0ZXIudGVzdGVyQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhdF9oYXNoIjoiMmJRZHVPckMxckpibkl6anJOMmMxdyIsIm5hbWUiOiJCb2IgQ2FydGVyIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0xOcEY2M2xOM19sbEJxNzNsSW9Vb083b1M0MTBoWGJZXzUzSElWUzNxbHNSMkhtZz1zOTYtYyIsImdpdmVuX25hbWUiOiJCb2IiLCJmYW1pbHlfbmFtZSI6IkNhcnRlciIsImlhdCI6MTczOTczOTM2NSwiZXhwIjoxNzM5NzQyOTY1fQ.p_OJqYgF4uYUSdT75nTZTA93ql-Q0c5Xbj0-ZZ5tRh1XNlRDSUpWUCBDlLibyB_ftUya-S6lS8tmyRI2y3ju4sG02qmfjeTRv52VA6kBGrIu_f1WIac2c8uaBYihr2lstElzUEzuB9p2_Gc1Tbd6QORONx-R2bED0BFwOlDxqgVx76u9c6KuovWLLZwl1LoflCC08xn3W28wmBtz3G5wjfMhzPTuJ4M4_h6HpdsBh4sbXFMWhCnjTtHR1AfVTiX34V7F1l82RNYKSb1ZmDyLLLOdJ9yYSmG2D5NW0L2APsX58XufJSlOMpqUuOGhFvHVUkqEuCSEIQBPQ95JM5jTug";
    public static final String USER_AGENT = "test.test";
    public static final String CLIENT_ID = "571800575857-fnugknju4cqaf84qt2egme8i68bceu37.apps.googleusercontent.com";
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




package com.helpix.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Scanner;

public class OAuth2Utils {


    private static final String CLIENT_ID = "571800575857-fnugknju4cqaf84qt2egme8i68bceu37.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "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";
    private static final String REDIRECT_URI = "http://localhost:8080/api/auth/callback/google"; // Должен совпадать с Google Console
    private static final String AUTH_URL = "https://accounts.google.com/o/oauth2/v2/auth";
    private static final String TOKEN_URL = "https://oauth2.googleapis.com/token";
    private static final String AUTH_SCOPE = "openid email profile";

    public static void main(String[] args) {
        // Step 1: Generate Google Auth URL
        String authUrl = AUTH_URL + "?client_id=" + CLIENT_ID
                + "&redirect_uri=" + REDIRECT_URI
                + "&response_type=code"
                + "&scope=" + AUTH_SCOPE
                + "&access_type=offline"
                + "&prompt=consent";

        System.out.println("Перейдите по этой ссылке и авторизуйтесь: ");
        System.out.println(authUrl);

        // Step 2: Wait for User to Enter Authorization Code
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите код из URL: ");
        String authCode = scanner.nextLine();

        // Step 3: Exchange Code for Access Token
        Response tokenResponse = RestAssured.given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("client_id", CLIENT_ID)
                .formParam("client_secret", CLIENT_SECRET)
                .formParam("code", authCode)
                .formParam("grant_type", "authorization_code")
                .formParam("redirect_uri", REDIRECT_URI)
                .post(TOKEN_URL);

        // Step 4: Print Access Token
        System.out.println("Access Token: " + tokenResponse.jsonPath().getString("access_token"));
        System.out.println("ID Token: " + tokenResponse.jsonPath().getString("id_token"));
    }
}

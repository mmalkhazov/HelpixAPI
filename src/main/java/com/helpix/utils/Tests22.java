package com.helpix.utils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Tests22 {

    @Test
    public void getGoogleTokens() {
        // Properly encode the scope parameter
        String scope = URLEncoder.encode(
                "openid https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile",
                StandardCharsets.UTF_8
        );

        // Construct the authorization URL with all required parameters
        String authUrl = "https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount" +
                "?client_id=571800575857-fnugknju4cqaf84qt2egme8i68bceu37.apps.googleusercontent.com" +
                "&redirect_uri=" + URLEncoder.encode("https://preview.helpix.io/api/auth/callback/google\"", StandardCharsets.UTF_8) +
                "&response_type=code" +
                "&scope=" + scope +
                "&access_type=offline" +
                "&prompt=consent";  // Added to ensure we get refresh token

        System.out.println("1. Open this URL in your browser:");
        System.out.println(authUrl);
        System.out.println("\n2. After authorization, copy the 'code' parameter from the redirect URL");

        // After getting the authorization code, exchange it for tokens
        System.out.println("\n3. Replace YOUR_AUTH_CODE below with the code you copied and run this test again");

        String authCode = "4%2F0ASVgi3LPBzUEAuQPwLJVvUMYFdS5l5uc8rhv5Cbdk_cDt89nPfzILDdRTM_ZCk14C8gfSA"; // Replace this with the code you get from the redirect URL

        Response response = RestAssured.given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("code", authCode)
                .formParam("client_id", "571800575857-fnugknju4cqaf84qt2egme8i68bceu37.apps.googleusercontent.com")
                .formParam("client_secret", "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")
                .formParam("redirect_uri", "https://oauth.pstmn.io/v1/callback")
                .formParam("grant_type", "authorization_code")
                .post("https://oauth2.googleapis.com/token");

        System.out.println("\nResponse status: " + response.getStatusCode());
        System.out.println("Response body: " + response.asPrettyString());
    }
}
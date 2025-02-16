package com.helpix.utils;


import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;

public class Tests {

    @Test
    public void getGoogleTokens() {
        // Use localhost as redirect URI
        String redirectUri = "http://localhost:8080/oauth2/callback";

        // Properly encode the scope parameter
        String scope = URLEncoder.encode(
                "openid https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile",
                StandardCharsets.UTF_8
        );

        // Construct the authorization URL with all required parameters
        String authUrl = "https://accounts.google.com/o/oauth2/v2/auth" +
                "?client_id=571800575857-fnugknju4cqaf84qt2egme8i68bceu37.apps.googleusercontent.com" +
                "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8) +
                "&response_type=code" +
                "&scope=" + scope +
                "&access_type=offline" +
                "&prompt=consent";  // Added to ensure we get refresh token

        System.out.println("1. Open this URL in your browser:");
        System.out.println(authUrl);
        System.out.println("\n2. After authorization, you'll see an error page because localhost isn't running");
        System.out.println("   That's OK! Copy the 'code' parameter from the URL in your browser's address bar");

        // After getting the authorization code, exchange it for tokens
        System.out.println("\n3. Replace YOUR_AUTH_CODE below with the code you copied and run this test again");

        String authCode = "ChVyZWRpcmVjdF91cmlfbWlzbWF0Y2gSsAEKWW91IGNhbid0IHNpZ24gaW4gdG8gdGhpcyBhcHAgYmVjYXVzZSBpdCBkb2Vzbid0IGNvbXBseSB3aXRoIEdvb2dsZSdzIE9BdXRoIDIuMCBwb2xpY3kuCgpJZiB5b3UncmUgdGhlIGFwcCBkZXZlbG9wZXIsIHJlZ2lzdGVyIHRoZSByZWRpcmVjdCBVUkkgaW4gdGhlIEdvb2dsZSBDbG91ZCBDb25zb2xlLgogIBptaHR0cHM6Ly9kZXZlbG9wZXJzLmdvb2dsZS5jb20vaWRlbnRpdHkvcHJvdG9jb2xzL29hdXRoMi93ZWItc2VydmVyI2F1dGhvcml6YXRpb24tZXJyb3JzLXJlZGlyZWN0LXVyaS1taXNtYXRjaCCQAyo1CgxyZWRpcmVjdF91cmkSJWh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9vYXV0aDIvY2FsbGJhY2sypAIIARKwAQpZb3UgY2FuJ3Qgc2lnbiBpbiB0byB0aGlzIGFwcCBiZWNhdXNlIGl0IGRvZXNuJ3QgY29tcGx5IHdpdGggR29vZ2xlJ3MgT0F1dGggMi4wIHBvbGljeS4KCklmIHlvdSdyZSB0aGUgYXBwIGRldmVsb3BlciwgcmVnaXN0ZXIgdGhlIHJlZGlyZWN0IFVSSSBpbiB0aGUgR29vZ2xlIENsb3VkIENvbnNvbGUuCiAgGm1odHRwczovL2RldmVsb3BlcnMuZ29vZ2xlLmNvbS9pZGVudGl0eS9wcm90b2NvbHMvb2F1dGgyL3dlYi1zZXJ2ZXIjYXV0aG9yaXphdGlvbi1lcnJvcnMtcmVkaXJlY3QtdXJpLW1pc21hdGNo&client_id=571800575857-fnugknju4cqaf84qt2egme8i68bceu37"; // Replace this with the code you get from the URL

        Response response = RestAssured.given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("code", authCode)
                .formParam("client_id", "571800575857-fnugknju4cqaf84qt2egme8i68bceu37.apps.googleusercontent.com")
                .formParam("client_secret", "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")
                .formParam("redirect_uri", redirectUri)
                .formParam("grant_type", "authorization_code")
                .post("https://oauth2.googleapis.com/token");

        System.out.println("\nResponse status: " + response.getStatusCode());
        System.out.println("Response body: " + response.asPrettyString());
    }
}

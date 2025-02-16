package com.helpix.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GoogleTokenTest {




        public static void main(String[] args) {
            // Кодируем параметры
            String clientId = "571800575857-d8qcnu15kos0eubgmnfd8g062r0sfooe.apps.googleusercontent.com";
            String redirectUri = "https://api-preview.helpix.io/auth/callback/google";
            String scope = "openid email profile";
            String responseType = "code";
            String prompt = "consent";
            String codeChallenge = "rvsZlyKL-dOZORUaP5_L4jYUDyTZTI2gonwlC9bAlH4"; // Если используешь PKCE
            String codeChallengeMethod = "S256"; // PKCE

            // Генерируем ссылку
            String authUrl = "https://accounts.google.com/o/oauth2/v2/auth" +
                    "?client_id=" + clientId +
                    "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8) +
                    "&scope=" + URLEncoder.encode(scope, StandardCharsets.UTF_8) +
                    "&response_type=" + responseType +
                    "&prompt=" + prompt +
                    "&code_challenge=" + codeChallenge +  // Убирай, если PKCE не используешь
                    "&code_challenge_method=" + codeChallengeMethod; // Убирай, если PKCE не используешь

            System.out.println(" Откройте эту ссылку в браузере для авторизации:");
            System.out.println(authUrl);
        }
}
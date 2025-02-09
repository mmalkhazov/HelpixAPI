package com.helpix.tests.restassured.user;

import com.helpix.dto.user.UserResponseDto;
import com.helpix.tests.restassured.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetProfileTestsWithIdTests extends TestBase {

    @Test
    public void GetProfileWithIdTest(){

//        System.out.println("The used Token " + getSavedLocalAccessToken());
//        System.out.println("Authorization Token: "+savedLocalAccessToken);
        UserResponseDto userProfile = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getSavedLocalAccessToken())
                .when()
                .get("/users/1")
                .then()
                .assertThat().statusCode(200)
                .extract().as(UserResponseDto.class);

        Assert.assertEquals(userProfile.getUsername(), "Oleksandr Firko", "Username does not match!");
        Assert.assertEquals(userProfile.getUserId(), 1, "User ID does not match!");

    }
//    Here we have the negative test

    @Test
    public void GetProfileWithIdWithInvalidTokenTest(){

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + LOCAL_INVALID_ACCESS_TOKEN)
                .when()
                .get("/users/1");

        response.then().statusCode(403);

    }

//    Here we have the negative test

    @Test
    public void GetProfileWithInvalidIdTest(){

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + savedLocalAccessToken)
                .when()
                .get("/users/7");

        response.then().statusCode(404);

    }

}

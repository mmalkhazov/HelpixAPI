package com.helpix.tests.restassured.user;

import com.helpix.dto.user.UserResponseDto;
import com.helpix.tests.restassured.TestBase;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetMyProfileTests extends TestBase {


    @Test
    public void GetMyProfileTest() {

        UserResponseDto userProfile = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getSavedLocalAccessToken())
                .when()
                .get("/users/me")
                .then()
                .assertThat().statusCode(200)
                .extract().as(UserResponseDto.class);


        Assert.assertEquals(userProfile.getUsername(), "Bob Carter", "Username does not match!");
        Assert.assertEquals(userProfile.getUserId(), 2, "User ID does not match!");

    }

//     Here we have the negative test
    @Test
    public void GetMyProfileWithInvalidTokenTest() {
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + LOCAL_INVALID_ACCESS_TOKEN)
                .when()
                .get("/users/me");

        response.then().statusCode(403);

    }


}

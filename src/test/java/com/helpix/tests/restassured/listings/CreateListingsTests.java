package com.helpix.tests.restassured.listings;

import com.helpix.dto.listings.ListingResponseDto;
import com.helpix.dto.listings.ListingsRequestDto;;
import com.helpix.fw.ListingHelper;
import com.helpix.tests.restassured.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.helpix.utils.TestDataFactory;
import java.io.IOException;


public class CreateListingsTests extends TestBase {



    private ListingHelper listingHelper;

    @BeforeClass
    public void setUp() throws IOException {
        ensureAuthenticated();
        listingHelper = new ListingHelper(getSavedLocalAccessToken());
    }

    @Test
    public void CreateListingPositiveTest() {
        ListingsRequestDto listingRequest = TestDataFactory.CreateValidListingsRequest();

        ListingResponseDto responseDto = listingHelper.createListing(listingRequest);

        Assert.assertNotNull(responseDto, "Listing creation failed!");
        Assert.assertNotNull(responseDto.getId(), "Listing ID should not be null!");
        Assert.assertEquals(responseDto.getAuthor().getUsername(), "Bob Carter", "Username does not match!");
    }

    @Test
    public void CreateListingNegativeWithInvalidAccessTokenTest() {
        ListingsRequestDto listingRequest = TestDataFactory.CreateValidListingsRequest();
        int statusCode = listingHelper.createListingWithInvalidAccessToken(listingRequest);

        Assert.assertEquals(statusCode, 403, "Expected status code 403 for invalid token.");
    }


    @Test
    public void CreateListingNegativeWithInvalidCredentialsTest() {
        ListingsRequestDto listingRequest = TestDataFactory.CreateInvalidListingsRequest();
        Response response = listingHelper.createListingResponse(listingRequest);
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

//        System.out.println("Response Status Code: " + statusCode);
//        System.out.println("Response Body: " + responseBody);

        Assert.assertEquals(statusCode, 400, "Expected status code 400 for invalid credentials.");
        Assert.assertTrue(responseBody.contains("Invalid email format"), "Expected email validation error.");
        Assert.assertTrue(responseBody.contains("Invalid phone number format"), "Expected phone validation error.");
    }

}

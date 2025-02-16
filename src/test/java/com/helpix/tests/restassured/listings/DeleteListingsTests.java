package com.helpix.tests.restassured.listings;

import com.helpix.dto.listings.ListingResponseDto;
import com.helpix.dto.listings.ListingsRequestDto;
import com.helpix.fw.ListingHelper;
import com.helpix.tests.restassured.TestBase;
import com.helpix.utils.TestDataFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.helpix.fw.ListingHelper.INVALID_LISTING_ID;


public class DeleteListingsTests extends TestBase {

    private static Integer savedListingId;
    private ListingHelper listingHelper;

    @BeforeClass
    public void setUp() throws IOException {
        ensureAuthenticated();
        listingHelper = new ListingHelper(getSavedLocalAccessToken());
    }

    @BeforeMethod
    public void precondition() {
        ListingsRequestDto listingRequest = TestDataFactory.CreateValidListingsRequest();

        ListingResponseDto responseDto = listingHelper.createListing(listingRequest);
        savedListingId = responseDto.getId();
    }

    @Test
    public void DeleteListingPositiveTest() {
        int statusCode = listingHelper.deleteListing(savedListingId);
        Assert.assertEquals(statusCode, 200, "Expected status code 200 for successful deletion.");

    }

    @Test
    public void DeleteListingNegativeWithInvalidAccessTokenTest() {
        int statusCode = listingHelper.deleteListingWithInvalidAccessToken(savedListingId);
        Assert.assertEquals(statusCode, 403, "Expected status code 403 for invalid token.");

    }

    @Test
    public void DeleteListingNegativeWithInvalidListingIdTest() {
        int statusCode = listingHelper.deleteListing(INVALID_LISTING_ID);
        Assert.assertEquals(statusCode, 404, "Expected status code 404 for invalid Listing ID.");
    }


}

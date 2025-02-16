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


public class ExtendListingTests extends TestBase {

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
    public void ExtendListingPositiveTest() {
        int statusCode = listingHelper.extendListing(savedListingId);
        Assert.assertEquals(statusCode, 200, "Expected status code 200 for successful deletion.");

    }

    @Test
    public void ExtendListingNegativeWithInvalidAccessTokenTest() {
        int statusCode = listingHelper.extendListingWithInvalidAccessToken(savedListingId);
        Assert.assertEquals(statusCode, 403, "Expected status code 403 for invalid token.");

    }

    @Test
    public void ExtendListingNegativeWithInvalidListingIdTest() {
        int statusCode = listingHelper.extendListing(INVALID_LISTING_ID);
        Assert.assertEquals(statusCode, 404, "Expected status code 404 for invalid Listing ID.");
    }
}








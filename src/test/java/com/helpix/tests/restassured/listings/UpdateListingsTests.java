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


public class UpdateListingsTests extends TestBase {

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
    public void UpdateListingPositiveTest() {
        ListingsRequestDto listingRequest = TestDataFactory.UpdatedListingsRequest();
        int statusCode = listingHelper.updateListing(listingRequest,savedListingId);
        Assert.assertEquals(statusCode, 200, "Expected status code 200 for successful deletion.");

    }

    @Test
    public void UpdateListingNegativeWithInvalidAccessTokenTest() {
        ListingsRequestDto listingRequest = TestDataFactory.UpdatedListingsRequest();
        int statusCode = listingHelper.updateListingWithInvalidAccessToken(listingRequest,savedListingId);
        Assert.assertEquals(statusCode, 403, "Expected status code 403 for invalid token.");

    }

    @Test
    public void UpdateListingNegativeWithInvalidListingIdTest() {
        ListingsRequestDto listingRequest = TestDataFactory.UpdatedListingsRequest();
        int statusCode = listingHelper.updateListing(listingRequest,INVALID_LISTING_ID);
        Assert.assertEquals(statusCode, 404, "Expected status code 404 for invalid Listing ID.");
    }

    @Test
    public void UpdateListingNegativeWithInvalidCategoryIdTest() {
        ListingsRequestDto listingRequest = TestDataFactory.UpdatedListingsRequestWithInvalidCategoryId();
        int statusCode = listingHelper.updateListing(listingRequest,savedListingId);
        Assert.assertEquals(statusCode, 400, "Expected status code 400 for invalid Listing ID.");
    }
}

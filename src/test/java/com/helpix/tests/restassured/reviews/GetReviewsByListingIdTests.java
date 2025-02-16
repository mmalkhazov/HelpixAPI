package com.helpix.tests.restassured.reviews;

import com.helpix.dto.listings.ListingResponseDto;
import com.helpix.dto.listings.ListingsRequestDto;
import com.helpix.dto.reviews.ReviewRequestDto;
import com.helpix.dto.reviews.ReviewResponseDto;
import com.helpix.fw.ListingHelper;
import com.helpix.fw.ReviewHelper;
import com.helpix.tests.restassured.TestBase;
import com.helpix.utils.TestDataFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.helpix.fw.ListingHelper.INVALID_LISTING_ID;

public class GetReviewsByListingIdTests extends TestBase {
    private static Integer savedListingId;
    private static Integer savedReviewId;

    private ReviewHelper reviewHelper;
    private ListingHelper listingHelper;

    @BeforeClass
    public void setUp() throws IOException {
        ensureAuthenticated();
        reviewHelper = new ReviewHelper(getSavedLocalAccessToken());
        listingHelper = new ListingHelper(getSavedLocalAccessToken());
    }


    @BeforeMethod
    public void precondition() {
        ListingsRequestDto listingRequest = TestDataFactory.CreateValidListingsRequest();
        ListingResponseDto responseListingDto = listingHelper.createListing(listingRequest);
        savedListingId = responseListingDto.getId();

        ReviewRequestDto reviewRequest = TestDataFactory.CreateValidReviewRequest();
        ReviewResponseDto responseReviewDto = reviewHelper.createReview(reviewRequest, savedListingId);
        savedReviewId = responseReviewDto.getId();

    }

    @Test
    public void GetReviewByListingIdWithoutAccessTokenPositiveTest() {
        int statusCode = reviewHelper.getReviewByListingIdWithInvalidAccessToken(savedListingId);
        Assert.assertEquals(statusCode, 200, "Expected status code 200 for successful deletion.");

    }

    @Test
    public void GetReviewByListingIdNegativeWithInvalidListingIdTest() {
        int statusCode = reviewHelper.getReviewByListingId(INVALID_LISTING_ID);
        Assert.assertEquals(statusCode, 200, "Expected status code 200 for successful deletion.");

    }




}

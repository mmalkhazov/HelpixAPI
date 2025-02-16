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
import java.util.ArrayList;
import java.util.List;

public class GetMyReviewsTests extends TestBase {

    private List<Integer> savedListingIds = new ArrayList<>();
    private List<Integer> savedReviewIds = new ArrayList<>();

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
        savedListingIds.clear();
        savedReviewIds.clear();


        for (int i = 0; i < 3; i++) {
            ListingsRequestDto listingRequest = TestDataFactory.CreateValidListingsRequest();
            ListingResponseDto listingResponse = listingHelper.createListing(listingRequest);
            int savedListingId = listingResponse.getId();
            savedListingIds.add(savedListingId);
            ReviewRequestDto reviewRequest = TestDataFactory.CreateValidReviewRequest();
            ReviewResponseDto responseReviewDto = reviewHelper.createReview(reviewRequest, savedListingId);
        }

//        ListingsRequestDto listingRequest = TestDataFactory.CreateValidListingsRequest();
//        ListingResponseDto responseListingDto = listingHelper.createListing(listingRequest);
//
//        ReviewRequestDto reviewRequest = TestDataFactory.CreateValidReviewRequest();
//        ReviewResponseDto responseReviewDto = reviewHelper.createReview(reviewRequest, savedListingId);


    }


    @Test
    public void GetMyReviewsPositiveTest() {
        int statusCode = reviewHelper.getMyReviews();
        Assert.assertEquals(statusCode, 200, "Expected status code 200.");

    }

    @Test
    public void GetMyReviewsWithInvalidAccessToken() {
        int statusCode = reviewHelper.getMyReviewsWithInvalidAccessToken();
        Assert.assertEquals(statusCode, 403, "Expected status code 403.");

    }


}

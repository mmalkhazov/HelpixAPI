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



public class CreateReviewTests extends TestBase {

    private static Integer savedListingId;
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

        ListingResponseDto responseDto = listingHelper.createListing(listingRequest);
        savedListingId = responseDto.getId();
    }


    @Test
    public void CreateReviewPositiveTest() {
        ReviewRequestDto reviewRequest = TestDataFactory.CreateValidReviewRequest();

        ReviewResponseDto responseDto = reviewHelper.createReview(reviewRequest, savedListingId);

        Assert.assertNotNull(responseDto, "Review creation failed: response is null.");
        Assert.assertNotNull(responseDto.getId(), "Review ID should not be null!");
        Assert.assertEquals(responseDto.getRating(), 4, "Rating does not match!");
        Assert.assertEquals(responseDto.getComment(), "Great product!", "Comment does not match!");
    }


    @Test
    public void CreateReviewNegativeWithInvalidAccessTokenTest() {
        ReviewRequestDto reviewRequest = TestDataFactory.CreateValidReviewRequest();
        int statusCode = reviewHelper.createReviewWithInvalidAccessToken(reviewRequest, savedListingId);
        Assert.assertEquals(statusCode, 403, "Expected status code 403 for invalid token.");
    }


    @Test
    public void CreateReviewNegativeWithInvalidRatingTest() {
        ReviewRequestDto reviewRequest = TestDataFactory.CreateReviewRequestWithInvalidRating();
        int statusCode = reviewHelper.createReviewWithInvalidAccessToken(reviewRequest, savedListingId);


//        System.out.println("Response Status Code: " + statusCode);
//        System.out.println("Response Body: " + responseBody);

        Assert.assertEquals(statusCode, 400, "Expected status code 400 for invalid credentials.");

    }

    @Test
    public void CreateReviewNegativeWithInvalidCommentTest() {
        ReviewRequestDto reviewRequest = TestDataFactory.CreateReviewRequestWithInvalidComment();
        int statusCode = reviewHelper.createReviewWithInvalidAccessToken(reviewRequest, savedListingId);


//        System.out.println("Response Status Code: " + statusCode);
//        System.out.println("Response Body: " + responseBody);

        Assert.assertEquals(statusCode, 400, "Expected status code 400 for invalid credentials.");

    }

//    @Test
//    public void CreateReviewNegativeWithInvalidListingIdTest() {
//        ReviewRequestDto reviewRequest = TestDataFactory.CreateValidReviewRequest();
////        int statusCode = reviewHelper.createReview(reviewRequest, INVALID_LISTING_ID);
//
////        int statusCode = response.getStatusCode(); // Предположим, что этот метод существует
//
////        Assert.assertEquals(statusCode, 400, "Expected status code 400 for invalid credentials.");
//    }
}

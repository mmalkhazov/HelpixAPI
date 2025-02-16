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
import static com.helpix.fw.ReviewHelper.INVALID_REVIEW_ID;

public class UpdateReviewTests extends TestBase {
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
    public void UpdateReviewPositiveTest() {
        ReviewRequestDto reviewRequest = TestDataFactory.UpdatedValidReviewRequest();
        int statusCode = reviewHelper.updateReview(reviewRequest,savedReviewId);
        Assert.assertEquals(statusCode, 200, "Expected status code 200 for successful update.");

    }
    @Test
    public void UpdateReviewNegativeWithInvalidAccessTokenTest() {
        ReviewRequestDto reviewRequest = TestDataFactory.UpdatedValidReviewRequest();
        int statusCode = reviewHelper.updateReviewWithInvalidAccessToken(reviewRequest,savedReviewId);
        Assert.assertEquals(statusCode, 403, "Expected status code 403 for invalid token.");

    }

    @Test
    public void UpdateListingNegativeWithInvalidReviewIdTest() {
        ReviewRequestDto reviewRequest = TestDataFactory.UpdatedValidReviewRequest();
        int statusCode = reviewHelper.updateReview(reviewRequest,INVALID_REVIEW_ID);
        Assert.assertEquals(statusCode, 404, "Expected status code 404 for invalid Listing ID.");
    }

    @Test
    public void UpdateListingNegativeWithInvalidRatingTest() {
        ReviewRequestDto reviewRequest = TestDataFactory.CreateReviewRequestWithInvalidRating();
        int statusCode = reviewHelper.updateReview(reviewRequest,savedReviewId);
        Assert.assertEquals(statusCode, 403, "Expected status code 403 for invalid Listing ID.");
    }


    @Test
    public void UpdateListingNegativeWithInvalidCommentTest() {
        ReviewRequestDto reviewRequest = TestDataFactory.CreateReviewRequestWithInvalidComment();
        int statusCode = reviewHelper.updateReview(reviewRequest,savedReviewId);
        Assert.assertEquals(statusCode, 403, "Expected status code 403 for invalid Listing ID.");
    }
}

package com.helpix.fw;

import com.helpix.dto.listings.ListingResponseDto;
import com.helpix.dto.listings.ListingsRequestDto;
import com.helpix.dto.reviews.ReviewRequestDto;
import com.helpix.dto.reviews.ReviewResponseDto;
import io.restassured.response.Response;

public class ReviewHelper extends BaseHelper{

    public static final String REVIEW_ENDPOINT ="/reviews";
    public static final int INVALID_REVIEW_ID=-1;

    public ReviewHelper(String accessToken) {
        super(accessToken);
    }



    public ReviewResponseDto createReview(ReviewRequestDto reviewRequest, int listingId) {
        Response response = sendPostRequest(REVIEW_ENDPOINT+ "/listings/" + listingId, reviewRequest);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to create listing. Status: " + response.getStatusCode());
        }

        return response.as(ReviewResponseDto.class);
    }


    public int createReviewWithInvalidAccessToken(ReviewRequestDto reviewRequest, int listingId) {
        Response response = sendInvalidPostRequest(REVIEW_ENDPOINT+ "/listings/" + listingId, reviewRequest);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }

    public int getReviewByListingId(int listingId) {
        Response response = sendGetByIdRequest(REVIEW_ENDPOINT+ "/listings",  listingId);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }

    public int getReviewByListingIdWithInvalidAccessToken(int listingId) {
        Response response = sendInvalidGetByIdRequest(REVIEW_ENDPOINT+ "/listings", listingId);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }

    public int getMyReviews() {
        Response response = sendGetRequest(REVIEW_ENDPOINT + "/users");

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }

    public int getMyReviewsWithInvalidAccessToken() {
        Response response = sendInvalidGetRequest(REVIEW_ENDPOINT+ "/users");

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }





    public int updateReview(ReviewRequestDto reviewRequest, int reviewId) {
        Response response = sendUpdateRequest(REVIEW_ENDPOINT, reviewRequest,reviewId);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }

    public int updateReviewWithInvalidAccessToken(ReviewRequestDto reviewRequest, int reviewId) {
        Response response = sendInvalidUpdateRequest(REVIEW_ENDPOINT,reviewRequest, reviewId);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }


    public int deleteReview(int reviewId) {
        Response response = sendDeleteRequest(REVIEW_ENDPOINT, reviewId);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }
    public int deleteReviewWithInvalidAccessToken(int reviewId) {
        Response response = sendInvalidDeleteRequest(REVIEW_ENDPOINT, reviewId);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }
}

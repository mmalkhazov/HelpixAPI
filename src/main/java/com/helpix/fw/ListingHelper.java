package com.helpix.fw;

import com.helpix.dto.listings.ListingResponseDto;
import com.helpix.dto.listings.ListingsRequestDto;
import io.restassured.response.Response;

public class ListingHelper extends BaseHelper{

private static final String LISTINGS_ENDPOINT ="/listings";

public static final int INVALID_LISTING_ID=12;

    public ListingHelper(String authToken) {
        super(authToken);
    }

    public ListingResponseDto createListing(ListingsRequestDto listingRequest) {
        Response response = sendPostRequest(LISTINGS_ENDPOINT, listingRequest);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to create listing. Status: " + response.getStatusCode());
        }


        return response.as(ListingResponseDto.class);
    }

    public int createListingWithInvalidAccessToken(ListingsRequestDto listingRequest) {
        Response response = sendInvalidPostRequest(LISTINGS_ENDPOINT, listingRequest);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }

    public Response createListingResponse(ListingsRequestDto listingRequest) {
        return sendPostRequest(LISTINGS_ENDPOINT, listingRequest);
    }

    public int getByIdListing(int listingId) {
        Response response = sendGetByIdRequest(LISTINGS_ENDPOINT, listingId);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }

    public int getByIdListingWithInvalidAccessToken(int listingId) {
        Response response = sendInvalidGetByIdRequest(LISTINGS_ENDPOINT, listingId);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }


    public int getMyListings() {
        Response response = sendGetRequest(LISTINGS_ENDPOINT);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }

    public int getMyListingsWithInvalidAccessToken() {
        Response response = sendInvalidGetRequest(LISTINGS_ENDPOINT);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }

    public int updateListing(ListingsRequestDto listingRequest, int listingId) {
        Response response = sendUpdateRequest(LISTINGS_ENDPOINT, listingRequest,listingId);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }
    public int updateListingWithInvalidAccessToken(ListingsRequestDto listingRequest, int listingId) {
        Response response = sendInvalidUpdateRequest(LISTINGS_ENDPOINT, listingRequest,listingId);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }


    public int deleteListing(int listingId) {
        Response response = sendDeleteRequest(LISTINGS_ENDPOINT, listingId);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }
    public int deleteListingWithInvalidAccessToken(int listingId) {
        Response response = sendInvalidDeleteRequest(LISTINGS_ENDPOINT, listingId);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }


    public int deactivateListing(int listingId) {
        Response response = sendDeactivateRequest(LISTINGS_ENDPOINT, listingId);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }

    public int deactivateListingWithInvalidAccessToken(int listingId) {
        Response response = sendInvalidDeactivateRequest(LISTINGS_ENDPOINT, listingId);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }


    public int extendListing(int listingId) {
        Response response = sendExtendRequest(LISTINGS_ENDPOINT, listingId);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }

    public int extendListingWithInvalidAccessToken(int listingId) {
        Response response = sendInvalidExtendRequest(LISTINGS_ENDPOINT, listingId);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        return response.getStatusCode();
    }



}

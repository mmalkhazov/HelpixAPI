package com.helpix.fw;

import io.restassured.response.Response;

public class UserHelper extends BaseHelper{
    public UserHelper(String accessToken) {
        super(accessToken);
    }

    private static final String USER_ENDPOINT ="/users";


//    public int getUserProfileById(int listingId) {
//        Response response = sendGetByIdRequest(USER_ENDPOINT, listingId);
//
//        System.out.println("Response Status Code: " + response.getStatusCode());
//        System.out.println("Response Body: " + response.getBody().asString());
//
//        return response.getStatusCode();
//    }
//
//    public int getByIdListingWithInvalidAccessToken(int listingId) {
//        Response response = sendInvalidGetByIdRequest(USER_ENDPOINT, listingId);
//
//        System.out.println("Response Status Code: " + response.getStatusCode());
//        System.out.println("Response Body: " + response.getBody().asString());
//
//        return response.getStatusCode();
//    }
//
//
//    public int getMyListings() {
//        Response response = sendGetRequest(USER_ENDPOINT);
//
//        System.out.println("Response Status Code: " + response.getStatusCode());
//        System.out.println("Response Body: " + response.getBody().asString());
//
//        return response.getStatusCode();
//    }
//
//    public int getMyListingsWithInvalidAccessToken() {
//        Response response = sendInvalidGetRequest(USER_ENDPOINT);
//
//        System.out.println("Response Status Code: " + response.getStatusCode());
//        System.out.println("Response Body: " + response.getBody().asString());
//
//        return response.getStatusCode();
//    }
//




}

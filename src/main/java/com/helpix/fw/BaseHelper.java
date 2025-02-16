package com.helpix.fw;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class BaseHelper {


    private String accessToken;
    private String invalidAccessToken = "";


    public BaseHelper(String accessToken) {
        this.accessToken = accessToken;
    }


    private RequestSpecification createBaseRequest() {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken);
    }



    protected Response sendPostRequest(String endpoint, Object body) {
        return createBaseRequest()
                .body(body)
                .when()
                .post(endpoint);
    }



    protected Response sendGetByIdRequest(String endpoint,  int id) {
        return createBaseRequest()
                .when()
                .get(endpoint + "/" + id);
    }

    protected Response sendGetRequest(String endpoint) {
        return createBaseRequest()
                .when()
                .get(endpoint + "/" +"me");
    }

    protected Response sendUpdateRequest(String endpoint,Object body, int id) {
        return createBaseRequest()
                .body(body)
                .when()
                .put(endpoint + "/" + id);
    }



    protected Response sendDeleteRequest(String endpoint, int id) {
        return createBaseRequest()
                .when()
                .delete(endpoint + "/" + id);
    }


    protected Response sendDeactivateRequest(String endpoint, int id) {
        return createBaseRequest()
                .when()
                .patch(endpoint + "/" + id+ "/deactivate");
    }



    protected Response sendExtendRequest(String endpoint, int id) {
        return createBaseRequest()
                .when()
                .patch(endpoint + "/" + id+ "/extend");
    }
    

    protected Response sendInvalidPostRequest(String endpoint, Object body) {
        return createInvalidBaseRequest()
                .body(body)
                .when()
                .post(endpoint);
    }

    private RequestSpecification createInvalidBaseRequest() {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + invalidAccessToken);
    }

    protected Response sendInvalidGetByIdRequest(String endpoint, int id) {
        return createInvalidBaseRequest()
                .when()
                .get(endpoint + "/" + id);
    }

        protected Response sendInvalidGetRequest(String endpoint) {
        return createInvalidBaseRequest()
                .when()
                .get(endpoint + "/" +"me");
    }

    protected Response sendInvalidUpdateRequest(String endpoint,Object body, int id) {
        return createInvalidBaseRequest()
                .body(body)
                .when()
                .put(endpoint + "/" + id);
    }



    protected Response sendInvalidDeleteRequest(String endpoint, int id) {
        return createInvalidBaseRequest()
                .when()
                .delete(endpoint + "/" + id);
    }

  


    protected Response sendInvalidDeactivateRequest(String endpoint, int id) {
        return createInvalidBaseRequest()
                .when()
                .patch(endpoint + "/" + id+ "/deactivate");
    }
    

    protected Response sendInvalidExtendRequest(String endpoint, int id) {
        return createInvalidBaseRequest()
                .when()
                .patch(endpoint + "/" + id+ "/extend");
    }

    protected Response sendInvalidGetRequest(String endpoint, int id) {
        return createInvalidBaseRequest()
                .when()
                .get(endpoint + "/" + id);
    }

}

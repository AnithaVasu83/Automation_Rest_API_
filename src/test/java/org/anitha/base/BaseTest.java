package org.anitha.base;
import org.anitha.endpoint.ApiConstants;
import org.anitha.module.PayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.http.*;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    public RequestSpecification requestSpecification;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;
    public PayLoad payload;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        System.out.println("Setting up BaseTest...");

        payload=new PayLoad();

        requestSpecification=RestAssured.given()
                                         .baseUri(ApiConstants.BASE_URL)
                                         .contentType(ContentType.JSON) //tell API request body is in JSON format.we can also use .addHeader() only if we use mutiple headers.
                                              //no a best practice if we only use contentType
                                         .log().all();
        //requestSpecification= new RequestSpecBuiler()
        //        .setBaseUri(ApiConstants.BASE_URL)
        //        .addHeader("content-Type","application/json")
        //        .build().log.all();

    }
    public String getToken(){
        requestSpecification.basePath(ApiConstants.AUTH_URL)
                             .log().all();

        response=RestAssured.given()
                              .spec(requestSpecification).body(payload.setAuthPayload())
                               .when()
                               .post();
        String token=payload.authPayloadResponse(response.asString());
        return token;

    }
}

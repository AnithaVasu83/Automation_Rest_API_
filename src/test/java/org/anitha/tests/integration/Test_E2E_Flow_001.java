package org.anitha.tests.integration;
import org.anitha.endpoint.ApiConstants;
import org.anitha.module.PayLoad;
import org.anitha.pojo.*;
import org.anitha.base.BaseTest;
import io.restassured.RestAssured;
import io.qameta.allure.Owner;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.ITestContext;
import static org.assertj.core.api.Assertions.assertThat;
public class Test_E2E_Flow_001 extends BaseTest {
    //create booking using POST
    //GET the booking by ID
    //Update the booking by ID
    //Delete the booking by ID
    @Test(groups = "regression" , priority = 1)
    @Owner("AnithaK")
    @Description("#TC-1 Verify the booking is created")
    public void createBooking(ITestContext iTestContext){
        requestSpecification.basePath(ApiConstants.CREATE_UPDATE_GET_BOOKING);
        response=RestAssured.given()
                .spec(requestSpecification)
                .body(payload.createPayloadBooking())
                .when()
                .post();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
        BookingResponse bookingData=payload.PayloadResponse(response.asString());//asString() converts response to String
        assertThat(bookingData.getBooking().getFirstname()).isEqualTo("Jim");
        assertThat(bookingData.getBookingid()).isNotNull().isGreaterThan(0);//.isNotBlank() is oly gor string not Integer or Long
                                                                                  //ensures bookingid is valid positive number
        iTestContext.setAttribute("bookingid",bookingData.getBookingid());//"bookingid" is just the name you assign in ITestcontext.
    }

    @Test(groups = "regression" , priority = 2)
    @Owner("AnithaK")
    @Description("TC #2 Verify that booking is created by ID")
    public void getBookingByid(ITestContext iTestContext){
        Integer bookingid=(Integer)iTestContext.getAttribute("bookingid");//Interger bookingid also just the variable name so change.its scope is only in this method
        String basepathGET=ApiConstants.CREATE_UPDATE_GET_BOOKING+"/"+bookingid;
        requestSpecification.basePath(basepathGET);
        response=RestAssured.given()
                .spec(requestSpecification)
                            .when()
                            .get();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
        Booking bookingData=payload.updatePayloadResponse(response.asString());
        assertThat(bookingData.getFirstname()).isEqualTo("Jim");
        assertThat(bookingData.getFirstname()).isNotNull().isNotBlank();
    }
    @Test(groups = "regression" , priority = 3)
    @Owner("Anithak")
    @Description("TC#3-Verify the updated booking by ID")
    public void updateBooking(ITestContext iTestContext){
        Integer bookingId=(Integer) iTestContext.getAttribute("bookingid");
        String token=getToken();
        iTestContext.setAttribute("token",token);
        String basepathPUT= ApiConstants.CREATE_UPDATE_GET_BOOKING+"/"+bookingId;
        requestSpecification.basePath(basepathPUT);
        response=  RestAssured.given()
                .spec(requestSpecification)
                .cookie("token", token)
                .body(payload.updatePayloadbooking())
                .when()
                                              .put();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
        Booking bookingData= payload.updatePayloadResponse(response.asString());
        assertThat(bookingData.getFirstname()).isNotNull().isNotBlank();
        assertThat(bookingData.getFirstname()).isEqualTo("Anitha");
        assertThat(bookingData.getLastname()).isEqualTo("Brownie");

    }
    @Test(groups = "regression" , priority = 4)
    @Owner("AnithaK")
    @Description("TC#4- verify delete booking by ID")
    public void deleteBooking(ITestContext iTestContext){
        Integer bookingid=(Integer)iTestContext.getAttribute("bookingid");
        String token=(String) iTestContext.getAttribute("token");
        requestSpecification.basePath(ApiConstants.CREATE_UPDATE_GET_BOOKING+"/"+bookingid);
        response=RestAssured.given()
                .spec(requestSpecification)
                .cookie("token",token)
                .when()
                .delete();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(201);
    }
}

package org.anitha.module;

import com.google.gson.Gson;
import org.anitha.pojo.*;

public class PayLoad {
    Gson gson;

    //creating or update booking
    public String createPayloadBooking(){
        Booking booking=new Booking();
        booking.setFirstname("Jim");
        booking.setLastname("Brown");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);
        BookingDates bookingdates=new BookingDates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        System.out.println(booking);
        //java to Json
        gson=new Gson();
        String jsonPayload=gson.toJson(booking);
        System.out.println(jsonPayload);
        return jsonPayload;
    }
    //json to java
    public BookingResponse PayloadResponse(String responseString)
    {
        gson=new Gson();
        BookingResponse bookingResponse=gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }
    //create auth
    public String setAuthPayload(){
        Auth auth=new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        System.out.println(auth);
        //java to Json
        gson=new Gson();
        String jsonAuthpayload=gson.toJson(auth);
        System.out.println(jsonAuthpayload);
        return jsonAuthpayload;
    }
    //json to java object
    public String authPayloadResponse(String responseToken)
    {
        gson=new Gson();
        TokenResponse tokenResponse =gson.fromJson(responseToken, TokenResponse.class);
        return tokenResponse.getToken();
    }
    //update booking
    public String updatePayloadbooking(){
        Booking booking=new Booking();
        booking.setFirstname("Anitha");
        booking.setLastname("Brownie");
        booking.setTotalprice(115);
        booking.setDepositpaid(true);
        BookingDates bookingdates=new BookingDates();
        bookingdates.setCheckin("2024-01-01");
        bookingdates.setCheckout("2025-01-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("dinner");
        System.out.println(booking);
        return gson.toJson(booking);
    }
    public Booking updatePayloadResponse(String getResponse){
        gson =new Gson();
        Booking booking=gson.fromJson(getResponse , Booking.class);
        return booking;
    }


}

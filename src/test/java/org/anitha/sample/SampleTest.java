package org.anitha.sample;


import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.Assert;

public class SampleTest {
    //sample testing to ensure the working of dependecies
    @Test(groups = "qa",priority = 1)
    @Owner("Anitha")
    @Description("Verify the booking is created")
    public void sampleCreateBooking()
    {
        Assert.assertTrue(true,"creating sample");
    }
    @Test(groups="qa",priority=2)
    @Owner("Anitha")
    @Description("Verify the created booking by ID")
    public void getBooking()
    {
        Assert.assertTrue(true,"getting the booking by ID");
    }
    @Test(groups="qa",priority=4)
    @Owner("Anitha")
    @Description("Verify the booking is updated")
    public void updateBooking()
    {
        Assert.assertTrue(true,"updatng the booking");
    }
    @Test(groups="qa",priority=3)
    public void deleteBooking()
    {
        Assert.assertTrue(true);
    }





}

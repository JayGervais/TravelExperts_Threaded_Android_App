package com.example.day10_assignment_v1.booking;

import android.icu.text.DateFormat;
import java.util.Date;

public class Booking
{
    private Integer BookingId;
    private Date BookingDate;
    private String BookingNo;
    private String CustomerId;
    private Integer TravelerCount;
    private String TravelType;
    private String BookingDetail;
    private String Region;
    private String TripType;
    private String Destination;
    private Integer BasePrice;
    private Integer AgencyCommission;

    public Booking(Integer bookingId, String bookingNo, Date bookingDate)
    {
        BookingId = bookingId;
        BookingNo = bookingNo;
        BookingDate = bookingDate;
    }

    @Override
    public String toString()
    {
        return BookingNo + ": " + BookingDate;
    }


    public Integer getBookingId()
    {
        return BookingId;
    }

    public Date getBookingDate()
    {
        return BookingDate;
    }

    public String getBookingNo()
    {
        return BookingNo;
    }

    public String getCustomerId()
    {
        return CustomerId;
    }

    public Integer getTravelerCount()
    {
        return TravelerCount;
    }

    public String getTravelType()
    {
        return TravelType;
    }

    public String getBookingDetail()
    {
        return BookingDetail;
    }

    public String getRegion()
    {
        return Region;
    }

    public String getTripType()
    {
        return TripType;
    }

    public String getDestination()
    {
        return Destination;
    }

    public Integer getBasePrice()
    {
        return BasePrice;
    }

    public Integer getAgencyCommission()
    {
        return AgencyCommission;
    }

    public void setBookingId(Integer bookingId)
    {
        BookingId = bookingId;
    }

    public void setBookingDate(Date bookingDate)
    {
        BookingDate = bookingDate;
    }

    public void setBookingNo(String bookingNo)
    {
        BookingNo = bookingNo;
    }

    public void setCustomerId(String customerId)
    {
        CustomerId = customerId;
    }

    public void setTravelerCount(Integer travelerCount)
    {
        TravelerCount = travelerCount;
    }

    public void setTravelType(String travelType)
    {
        TravelType = travelType;
    }

    public void setBookingDetail(String bookingDetail)
    {
        BookingDetail = bookingDetail;
    }

    public void setRegion(String region)
    {
        Region = region;
    }

    public void setTripType(String tripType)
    {
        TripType = tripType;
    }

    public void setDestination(String destination)
    {
        Destination = destination;
    }

    public void setBasePrice(Integer basePrice)
    {
        BasePrice = basePrice;
    }

    public void setAgencyCommission(Integer agencyCommission)
    {
        AgencyCommission = agencyCommission;
    }
}

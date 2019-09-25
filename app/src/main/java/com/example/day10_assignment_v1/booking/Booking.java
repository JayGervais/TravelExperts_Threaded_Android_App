package com.example.day10_assignment_v1.booking;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Booking implements Serializable
{
    private Integer BookingId;
    private Date BookingDate;
    private String BookingNo;
    private String CustomerId;
    private Integer TravelerCount;
    private String Description;
    private String TravelType;
    private String BookingDetail;
    private String Region;
    private String TripType;
    private String Destination;
    private BigDecimal BasePrice;
    private BigDecimal AgencyCommission;

    public Booking(Integer bookingId,
                   String bookingNo,
                   Date bookingDate,
                   Integer travelerCount,
                   String description,
                   BigDecimal basePrice,
                   BigDecimal agencyCommission)
    {

        BookingId = bookingId;
        BookingNo = bookingNo;
        BookingDate = bookingDate;
        TravelerCount = travelerCount;
        Description = description;
        BasePrice = basePrice;
        AgencyCommission = agencyCommission;
    }

    public Booking(Integer bookingId, Date bookingDate)
    {
        BookingId = bookingId;
        BookingDate = bookingDate;
    }

    @Override
    public String toString()
    {
        // format dates
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateFormat = formatter.format(BookingDate);
        // format currency values
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
        String baseFormat = currencyFormat.format(BasePrice);
        String commissionFormat = currencyFormat.format(AgencyCommission);

        return dateFormat + "\n" + Description + "\n" +
                "Base: " + baseFormat + ", Commission: " + commissionFormat;
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
    public String getDescription()
    {
        return Description;
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
    public BigDecimal getBasePrice()
    {
        return BasePrice;
    }
    public BigDecimal getAgencyCommission()
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
    public void setTravelerCount(String description)
    {
        Description = description;
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
    public void setBasePrice(BigDecimal basePrice)
    {
        BasePrice = basePrice;
    }
    public void setAgencyCommission(BigDecimal agencyCommission)
    {
        AgencyCommission = agencyCommission;
    }
}

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
    private Integer CustomerId;
    private Integer TravelerCount;
    private String Description;
    private String TravelType;
    private String BookingDetail;
    private String Region;
    private String TripType;
    private String Destination;
    private BigDecimal BasePrice;
    private BigDecimal AgencyCommission;
    private Date StartDate;
    private Date EndDate;
    private String ClassName;

    public Booking(Integer bookingId,
                   String bookingNo,
                   Date bookingDate,
                   Integer travelerCount,
                   String description,
                   String destination,
                   BigDecimal basePrice,
                   BigDecimal agencyCommission,
                   Date startDate,
                   Date endDate,
                   String className,
                   Integer customerId)
    {
        BookingId = bookingId;
        BookingNo = bookingNo;
        BookingDate = bookingDate;
        TravelerCount = travelerCount;
        Description = description;
        Destination = destination;
        BasePrice = basePrice;
        AgencyCommission = agencyCommission;
        StartDate = startDate;
        EndDate = endDate;
        ClassName = className;
        CustomerId = customerId;
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

        return dateFormat + " - " + Destination + "\n" +
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
    public Integer getCustomerId()
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
    public Date getStartDate()
    {
        return StartDate;
    }
    public Date getEndDate()
    {
        return EndDate;
    }
    public String getClassName()
    {
        return ClassName;
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
    public void setCustomerId(Integer customerId)
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
    public void setStartDate(Date startDate)
    {
        StartDate = startDate;
    }
    public void setEndDate(Date endDate)
    {
        EndDate = endDate;
    }
    public void setDescription(String description)
    {
        Description = description;
    }
    public void setClassName(String className)
    {
        ClassName = className;
    }
}

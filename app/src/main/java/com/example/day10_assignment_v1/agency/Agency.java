package com.example.day10_assignment_v1.agency;

public class Agency
{
    Integer AgencyId;
    String AgncyAddress;
    String AgncyCity;
    String AgncyProv;
    String AgncyPostal;
    String AgncyCountry;
    String AgncyPhone;
    String AgncyFax;

    public Agency(Integer agencyId, String agncyAddress, String agncyCity, String agncyProv, String agncyPostal, String agncyCountry, String agncyPhone, String agncyFax)
    {
        AgencyId = agencyId;
        AgncyAddress = agncyAddress;
        AgncyCity = agncyCity;
        AgncyProv = agncyProv;
        AgncyPostal = agncyPostal;
        AgncyCountry = agncyCountry;
        AgncyPhone = agncyPhone;
        AgncyFax = agncyFax;
    }

    public Agency(Integer agencyId, String agncyAddress, String agncyCity)
    {
        AgencyId = agencyId;
        AgncyAddress = agncyAddress;
        AgncyCity = agncyCity;
    }

    @Override
    public String toString()
    {
        return AgncyAddress + ", " + AgncyCity;
    }

    public Integer getAgencyId()
    {
        return AgencyId;
    }

    public String getAgncyAddress()
    {
        return AgncyAddress;
    }

    public String getAgncyCity()
    {
        return AgncyCity;
    }

    public String getAgncyProv()
    {
        return AgncyProv;
    }

    public String getAgncyPostal()
    {
        return AgncyPostal;
    }

    public String getAgncyCountry()
    {
        return AgncyCountry;
    }

    public String getAgncyPhone()
    {
        return AgncyPhone;
    }

    public String getAgncyFax()
    {
        return AgncyFax;
    }

    public void setAgencyId(Integer agencyId)
    {
        AgencyId = agencyId;
    }

    public void setAgncyAddress(String agncyAddress)
    {
        AgncyAddress = agncyAddress;
    }

    public void setAgncyCity(String agncyCity)
    {
        AgncyCity = agncyCity;
    }

    public void setAgncyProv(String agncyProv)
    {
        AgncyProv = agncyProv;
    }

    public void setAgncyPostal(String agncyPostal)
    {
        AgncyPostal = agncyPostal;
    }

    public void setAgncyCountry(String agncyCountry)
    {
        AgncyCountry = agncyCountry;
    }

    public void setAgncyPhone(String agncyPhone)
    {
        AgncyPhone = agncyPhone;
    }

    public void setAgncyFax(String agncyFax)
    {
        AgncyFax = agncyFax;
    }
}

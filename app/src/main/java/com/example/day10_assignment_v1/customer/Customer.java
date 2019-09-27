package com.example.day10_assignment_v1.customer;

public class Customer
{
    Integer CustId;
    String CustFirstName;
    String CustLastName;
    String CustAddress;
    String CustCity;
    String CustProv;
    String CustPostal;
    String CustCountry;
    String CustBusPhone;
    String CustHomePhone;
    String CustEmail;
    String AgentId;

    public Customer(Integer custId, String custFirstName, String custLastName,
                    String custHomePhone, String custEmail)
    {
        CustId = custId;
        CustFirstName = custFirstName;
        CustLastName = custLastName;
        CustHomePhone = custHomePhone;
        CustEmail = custEmail;
    }

    @Override
    public String toString()
    {
        return CustFirstName + " " + CustLastName;
    }

    public Integer getCustId()
    {
        return CustId;
    }

    public String getCustFirstName()
    {
        return CustFirstName;
    }

    public String getCustLastName()
    {
        return CustLastName;
    }

    public String getCustAddress()
    {
        return CustAddress;
    }

    public String getCustCity()
    {
        return CustCity;
    }

    public String getCustProv()
    {
        return CustProv;
    }

    public String getCustPostal()
    {
        return CustPostal;
    }

    public String getCustCountry()
    {
        return CustCountry;
    }

    public String getCustBusPhone()
    {
        return CustBusPhone;
    }

    public String getCustHomePhone()
    {
        return CustHomePhone;
    }

    public String getCustEmail()
    {
        return CustEmail;
    }

    public String getAgentId()
    {
        return AgentId;
    }

    public void setCustId(Integer custId)
    {
        CustId = custId;
    }

    public void setCustFirstName(String custFirstName)
    {
        CustFirstName = custFirstName;
    }

    public void setCustLastName(String custLastName)
    {
        CustLastName = custLastName;
    }

    public void setCustAddress(String custAddress)
    {
        CustAddress = custAddress;
    }

    public void setCustCity(String custCity)
    {
        CustCity = custCity;
    }

    public void setCustProv(String custProv)
    {
        CustProv = custProv;
    }

    public void setCustPostal(String custPostal)
    {
        CustPostal = custPostal;
    }

    public void setCustCountry(String custCountry)
    {
        CustCountry = custCountry;
    }

    public void setCustBusPhone(String custBusPhone)
    {
        CustBusPhone = custBusPhone;
    }

    public void setCustHomePhone(String custHomePhone)
    {
        CustHomePhone = custHomePhone;
    }

    public void setCustEmail(String custEmail)
    {
        CustEmail = custEmail;
    }

    public void setAgentId(String agentId)
    {
        AgentId = agentId;
    }
}

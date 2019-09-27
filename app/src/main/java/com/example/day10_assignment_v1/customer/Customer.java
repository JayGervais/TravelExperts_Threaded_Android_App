package com.example.day10_assignment_v1.Customer;

public class Customer {
    private Integer CustId;
    private String CustFirstName;
    private String CustLastName;
    private String CustAddress;
    private String CustCity;
    private String CustProv;
    private String CustPostal;
    private String CustCountry;
    private String CustHomePhone;
    private String CustBusPhone;
    private String CustEmail;
    private Integer AgentId;

    public Customer(Integer custId, String custFirstName, String custLastName, String custAddress, String custCity, String custProv, String custPostal, String custCountry, String custHomePhone, String custBusPhone, String custEmail, Integer agentId)
    {
        CustId = custId;
        CustFirstName = custFirstName;
        CustLastName = custLastName;
        CustAddress = custAddress;
        CustCity = custCity;
        CustProv = custProv;
        CustPostal = custPostal;
        CustCountry = custCountry;
        CustHomePhone = custHomePhone;
        CustBusPhone = custBusPhone;
        CustEmail = custEmail;
        AgentId = agentId;
    }

    public Customer(Integer custId, String custFirstName, String custLastName, String custHomePhone, String custEmail)
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
        return CustId + " " + CustFirstName + " " + CustLastName+ " " + CustAddress + " " + CustCity
                + " " + CustProv + " " + CustPostal + " " + CustCountry + " " + CustHomePhone
                + " " + CustBusPhone + " " + CustEmail + " " + AgentId;
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

    public String getCustHomePhone()
    {
        return CustHomePhone;
    }

    public String getCustBusPhone()
    {
        return CustBusPhone;
    }

    public String getCustEmail()
    {
        return CustEmail;
    }

    public Integer getAgentId()
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

    public void setCustHomePhone(String custHomePhone)
    {
        CustHomePhone = custHomePhone;
    }

    public void setCustBusPhone(String custBusPhone)
    {
        CustBusPhone = custBusPhone;
    }

    public void setCustEmail(String custEmail)
    {
        CustEmail = custEmail;
    }

    public void setAgentId(Integer agentId)
    {
        AgentId = agentId;
    }
}

package com.example.day10_assignment_v1.agent;

import java.io.Serializable;

public class Agent implements Serializable
{
    private Integer AgentId;
    private String AgtFirstName;
    private String AgtMiddleInitial;
    private String AgtLastName;
    private String AgtBusPhone;
    private String AgtEmail;
    private String AgtPosition;
    private Integer AgencyId;

    public Agent(Integer agentId,
                 String agtFirstName,
                 String agtMiddleInitial,
                 String agtLastName,
                 String agtBusPhone,
                 String agtEmail,
                 String agtPosition,
                 Integer agencyId)
    {
        AgentId = agentId;
        AgtFirstName = agtFirstName;
        AgtMiddleInitial = agtMiddleInitial;
        AgtLastName = agtLastName;
        AgtBusPhone = agtBusPhone;
        AgtEmail = agtEmail;
        AgtPosition = agtPosition;
        AgencyId = agencyId;
    }

    public Agent(String agtFirstName, String agtLastName, String agtPosition)
    {
        AgtFirstName = agtFirstName;
        AgtLastName = agtLastName;
        AgtPosition = agtPosition;
    }

    public Integer getAgentId()
    {
        return AgentId;
    }
    public String getAgtFirstName()
    {
        return AgtFirstName;
    }
    public String getAgtMiddleInitial()
    {
        return AgtMiddleInitial;
    }
    public String getAgtLastName()
    {
        return AgtLastName;
    }
    public String getAgtBusPhone()
    {
        return AgtBusPhone;
    }
    public String getAgtEmail()
    {
        return AgtEmail;
    }
    public String getAgtPosition()
    {
        return AgtPosition;
    }
    public Integer getAgencyId()
    {
        return AgencyId;
    }

    public void setAgentId(int agentId)
    {
        AgentId = agentId;
    }
    public void setAgtFirstName(String agtFirstName)
    {
        AgtFirstName = agtFirstName;
    }
    public void setAgtMiddleInitial(String agtMiddleInitial)
    {
        AgtMiddleInitial = agtMiddleInitial;
    }
    public void setAgtLastName(String agtLastName)
    {
        AgtLastName = agtLastName;
    }
    public void setAgtBusPhone(String agtBusPhone)
    {
        AgtBusPhone = agtBusPhone;
    }
    public void setAgtEmail(String agtEmail)
    {
        AgtEmail = agtEmail;
    }
    public void setAgtPosition(String agtPosition)
    {
        AgtPosition = agtPosition;
    }
    public void setAgencyId(int agencyId)
    {
        AgencyId = agencyId;
    }

    @Override
    public String toString()
    {
        return AgtFirstName + " " + AgtLastName + ", " + AgtPosition;
    }

}

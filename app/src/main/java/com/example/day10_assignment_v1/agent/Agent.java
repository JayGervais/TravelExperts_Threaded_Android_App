package com.example.day10_assignment_v1.agent;

import android.os.Parcel;
import android.os.Parcelable;

public class Agent implements Parcelable
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

    public Agent(Integer agentId, String agtFirstName, String agtLastName, String agtPosition)
    {
        AgentId = agentId;
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

    public void setAgentId(Integer agentId)
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
    public void setAgencyId(Integer agencyId)
    {
        AgencyId = agencyId;
    }

    @Override
    public String toString()
    {
        return AgtFirstName + " " + AgtLastName + ", " + AgtPosition;
    }


//    @Override
//    public String toString()
//    {
//        return "Agent{" +
//                "AgentId=" + AgentId +
//                ", AgtFirstName='" + AgtFirstName + '\'' +
//                ", AgtMiddleInitial='" + AgtMiddleInitial + '\'' +
//                ", AgtLastName='" + AgtLastName + '\'' +
//                ", AgtBusPhone='" + AgtBusPhone + '\'' +
//                ", AgtEmail='" + AgtEmail + '\'' +
//                ", AgtPosition='" + AgtPosition + '\'' +
//                ", AgencyId=" + AgencyId +
//                '}';
//    }

    // Parcelling part
    public Agent(Parcel in){
        String[] data = new String[8];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        AgentId = Integer.valueOf(data[0]);
        AgtFirstName = data[1];
        AgtMiddleInitial = data[2];
        AgtLastName = data[3];
        AgtBusPhone = data[4];
        AgtEmail = data[5];
        AgtPosition = data[6];
        AgencyId = Integer.valueOf(data[7]);
    }

    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeStringArray(new String[] {
                String.valueOf(AgentId), AgtFirstName, AgtMiddleInitial, AgtLastName,
                AgtBusPhone, AgtEmail, AgtPosition, String.valueOf(AgencyId)
        });
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Agent createFromParcel(Parcel in) {
            return new Agent(in);
        }
        public Agent[] newArray(int size) {
            return new Agent[size];
        }
    };

}

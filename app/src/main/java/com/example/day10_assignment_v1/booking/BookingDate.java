package com.example.day10_assignment_v1.booking;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.day10_assignment_v1.agent.Agent;

public class BookingDate implements Parcelable
{
    String StartDate;
    String EndDate;

    public BookingDate(String startDate, String endDate)
    {
        StartDate = startDate;
        EndDate = endDate;
    }

    public String getStartDate()
    {
        return StartDate;
    }
    public String getEndDate()
    {
        return EndDate;
    }
    public void setStartDate(String startDate)
    {
        StartDate = startDate;
    }
    public void setEndDate(String endDate)
    {
        EndDate = endDate;
    }

    @Override
    public String toString()
    {
        return StartDate + " " + EndDate;
    }

    // Parcelling part
    public BookingDate(Parcel in){
        String[] data = new String[2];
        in.readStringArray(data);
        StartDate = data[0];
        EndDate = data[1];
    }

    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeStringArray(new String[] {
                StartDate, EndDate
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

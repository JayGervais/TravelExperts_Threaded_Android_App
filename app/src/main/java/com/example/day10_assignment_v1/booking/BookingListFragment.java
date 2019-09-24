package com.example.day10_assignment_v1.booking;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.day10_assignment_v1.R;

import static com.example.day10_assignment_v1.R.id.fragmentBookingList;
import static com.example.day10_assignment_v1.R.id.tvBookingDate;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingListFragment extends Fragment
{
    TextView tvBookingDate, tvBookingNumber, tvTravelerCount, tvDestination, tvBasePrice, tvAgencyCommission;

    public BookingListFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        tvBookingDate = getActivity().findViewById(R.id.tvBookingDate);
        tvBookingNumber = getActivity().findViewById(R.id.tvBookingNumber);
        tvTravelerCount = getActivity().findViewById(R.id.tvTravelerCount);
        tvDestination = getActivity().findViewById(R.id.tvDestination);
        tvBasePrice = getActivity().findViewById(R.id.tvBasePrice);
        tvAgencyCommission = getActivity().findViewById(R.id.tvAgencyCommission);

        BookingDBHelper.BookingFragmentListData("https://infastory.com/api/booking_data.php", getView().getContext(), tvBookingDate, tvBookingNumber);
    }


}

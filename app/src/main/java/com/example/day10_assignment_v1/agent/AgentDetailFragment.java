package com.example.day10_assignment_v1.agent;


import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.day10_assignment_v1.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgentDetailFragment extends Fragment
{
    EditText etAgentId, etAgtFirstName, etAgtMiddleInitial, etAgtLastName, etAgtBusPhone, etAgtEmail, etAgtPosition;
    Spinner spinAgencies;

    public AgentDetailFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agent_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        etAgentId = getActivity().findViewById(R.id.etAgentId);
        etAgtFirstName = getActivity().findViewById(R.id.etAgtFirstName);
        etAgtMiddleInitial = getActivity().findViewById(R.id.etAgtMiddleInitial);
        etAgtLastName = getActivity().findViewById(R.id.etAgtLastName);
        etAgtBusPhone = getActivity().findViewById(R.id.etAgtBusPhone);
        etAgtEmail = getActivity().findViewById(R.id.etAgtEmail);
        etAgtPosition = getActivity().findViewById(R.id.etAgtPosition);
        spinAgencies = getActivity().findViewById(R.id.spinAgencies);
    }


}

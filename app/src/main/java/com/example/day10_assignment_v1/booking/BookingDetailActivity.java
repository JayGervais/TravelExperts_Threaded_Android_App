package com.example.day10_assignment_v1.booking;

import android.content.Context;
import android.net.Uri;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.day10_assignment_v1.Customer.CustomerDB;
import com.example.day10_assignment_v1.DBHelper;
import com.example.day10_assignment_v1.R;
import com.example.day10_assignment_v1.agent.Agent;
import com.example.day10_assignment_v1.agent.AgentDB;

public class BookingDetailActivity extends AppCompatActivity
{
    TextView tvBookingDate, tvBookingNumber, tvTravelerCount, tvDestination,
            tvBasePrice, tvAgencyCommission, tvDescription, tvStartDate, tvEndDate, tvClassName,
            tvCustFirstName, tvCustLastName, tvCustEmail, tvCustPhone;

    Button btnAgent;

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_detail);

        // back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Booking booking = (Booking) getIntent().getSerializableExtra("booking");

        tvBookingDate = findViewById(R.id.tvBookingDate);
        tvBookingNumber = findViewById(R.id.tvBookingNumber);
        tvTravelerCount = findViewById(R.id.tvTravelerCount);
        tvDestination = findViewById(R.id.tvDestination);
        tvBasePrice = findViewById(R.id.tvBasePrice);
        tvAgencyCommission = findViewById(R.id.tvAgencyCommission);
        tvDescription = findViewById(R.id.tvDescription);
        tvStartDate = findViewById(R.id.tvStartDate);
        tvEndDate = findViewById(R.id.tvEndDate);
        tvClassName = findViewById(R.id.tvClassName);

        // customer view
        tvCustFirstName = findViewById(R.id.tvCustFirstName);
        tvCustLastName = findViewById(R.id.tvCustLastName);
        tvCustEmail = findViewById(R.id.tvCustEmail);
        tvCustPhone = findViewById(R.id.tvCustPhone);

        // agent view
        btnAgent = findViewById(R.id.btnAgent);

        // string builder for api call
        Uri.Builder bookingURL = new Uri.Builder();
        bookingURL.scheme("https").authority(DBHelper.apiAuth())
                .appendPath("api")
                .appendPath("booking_data_select.php")
                .appendQueryParameter("BookingId", String.valueOf(booking.getBookingId()));
        String bookingAPI = bookingURL.build().toString();

        BookingDB.BookingData(bookingAPI, this, tvBookingDate, tvBookingNumber, tvTravelerCount,
                tvDestination, tvBasePrice, tvAgencyCommission, tvDescription, tvStartDate, tvEndDate,
                tvClassName);

        Uri.Builder customerURL = new Uri.Builder();
        customerURL.scheme("https").authority(DBHelper.apiAuth())
                .appendPath("api")
                .appendPath("customer_data_select.php")
                .appendQueryParameter("CustomerId", String.valueOf(booking.getCustomerId()));
        String customerAPI = customerURL.build().toString();

        CustomerDB.SelectedCustomerData(customerAPI, this, tvCustFirstName, tvCustLastName,
                tvCustPhone, tvCustEmail);

        final Agent agent = (Agent) getIntent().getSerializableExtra("agent");

        Uri.Builder agentURL = new Uri.Builder();
        agentURL.scheme("https").authority(DBHelper.apiAuth())
                .appendPath("api")
                .appendPath("agent_data_select.php")
                .appendQueryParameter("CustomerId", String.valueOf(booking.getCustomerId()));
        String agentAPI = customerURL.build().toString();

        //AgentDB.ThisAgent(agentAPI, this, String.valueOf(booking.getCustomerId()));



        btnAgent.setText("Agent");
    }
}
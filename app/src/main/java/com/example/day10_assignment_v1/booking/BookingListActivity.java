package com.example.day10_assignment_v1.booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.example.day10_assignment_v1.DBHelper;
import com.example.day10_assignment_v1.MainActivity;
import com.example.day10_assignment_v1.R;
import com.example.day10_assignment_v1.agent.AgentListActivity;

public class BookingListActivity extends AppCompatActivity
{
    ListView listBookings;
    TextView txtBookingDate, txtBookingDescription, txtBasePrice, txtCommission, tvBookingTotal,
            tvCommissionTotal;

    // date selector text fields
    TextView startDate, endDate;
    // button for showing selected dates
    Button btnShowDates;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);

        txtBookingDate = findViewById(R.id.txtBookingDate);
        txtBookingDescription = findViewById(R.id.txtBookingDescription);
        txtBasePrice = findViewById(R.id.tvBasePrice);
        txtCommission = findViewById(R.id.txtCommission);
        tvBookingTotal = findViewById(R.id.tvBookingTotal);
        tvCommissionTotal = findViewById(R.id.tvCommissionTotal);

        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);
        btnShowDates = findViewById(R.id.btnShowDates);
        listBookings = findViewById(R.id.listBookings);

        listBookings.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(getApplicationContext(), BookingDetailActivity.class);
                intent.putExtra("booking", (Booking) listBookings.getItemAtPosition(position));
                startActivity(intent);
            }
        });

        // get booking date selection values
        BookingDateSelect.BookDate(this, startDate, endDate, btnShowDates);
        // gets intent for date selection
        Intent intent = getIntent();

        if (intent.hasExtra("startingDate") && intent.hasExtra("endingDate"))
        {
            btnShowDates.setText("Clear");
            btnShowDates.setEnabled(true);

            String startingDate = intent.getStringExtra("startingDate");
            String endingDate = intent.getStringExtra("endingDate");

            startDate.setText(startingDate);
            endDate.setText(endingDate);

            // url builder for date select query
            Uri.Builder dateBuilder = new Uri.Builder();
            dateBuilder.scheme("https").authority(DBHelper.apiAuth())
                    .appendPath("api")
                    .appendPath("booking_data_dates.php")
                    .appendQueryParameter("StartDate", startingDate)
                    .appendQueryParameter("EndDate", endingDate);
            String myDateSelectUrl = dateBuilder.build().toString();

            BookingDB.BookingListData(myDateSelectUrl, this, listBookings,
                    tvBookingTotal, tvCommissionTotal);
        }
        else
        {
            btnShowDates.setEnabled(false);

            // gets all booking data
            BookingDB.BookingListData(DBHelper.apiURL() + "/api/booking_data.php",
                    this, listBookings, tvBookingTotal, tvCommissionTotal);
        }
    }

    // main menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_home:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.menu_agents:
                startActivity(new Intent(this, AgentListActivity.class));
                return true;
            case R.id.menu_bookings:
                startActivity(new Intent(this, BookingListActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showDates(View view)
    {
        Intent intent = getIntent();

        if (intent.hasExtra("startingDate") && intent.hasExtra("endingDate"))
        {
            startActivity(new Intent(this, BookingListActivity.class));
        }
        else
        {
            BookingDate bookingDate = new BookingDate(String.valueOf(startDate.getText()), String.valueOf(endDate.getText()));
            String startingDate = bookingDate.StartDate;
            String endingDate = bookingDate.EndDate;

            intent.putExtra("startingDate", startingDate);
            intent.putExtra("endingDate", endingDate);
            finish();
            startActivity(intent);
        }
    }
}

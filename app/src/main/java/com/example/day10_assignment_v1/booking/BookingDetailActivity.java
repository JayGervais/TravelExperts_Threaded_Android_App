package com.example.day10_assignment_v1.booking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.day10_assignment_v1.R;

public class BookingDetailActivity extends AppCompatActivity
{

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
    }
}

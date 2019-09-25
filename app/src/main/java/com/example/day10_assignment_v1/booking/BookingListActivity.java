package com.example.day10_assignment_v1.booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.day10_assignment_v1.MainActivity;
import com.example.day10_assignment_v1.R;
import com.example.day10_assignment_v1.agent.AgentListActivity;

public class BookingListActivity extends AppCompatActivity
{
    ListView listBookings;
    TextView txtBookingDate, txtBookingDescription, txtBasePrice, txtCommission;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);

        txtBookingDate = findViewById(R.id.txtBookingDate);
        txtBookingDescription = findViewById(R.id.txtBookingDescription);
        txtBasePrice = findViewById(R.id.tvBasePrice);
        txtCommission = findViewById(R.id.txtCommission);

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

        BookingDB.BookingListData("https://infastory.com/api/booking_data.php",
                this, listBookings);
    }

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
}

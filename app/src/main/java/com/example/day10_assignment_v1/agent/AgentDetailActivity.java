package com.example.day10_assignment_v1.agent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.day10_assignment_v1.DBHelper;
import com.example.day10_assignment_v1.R;
import com.example.day10_assignment_v1.booking.Booking;
import com.example.day10_assignment_v1.booking.BookingDB;
import com.example.day10_assignment_v1.booking.BookingDetailActivity;

public class AgentDetailActivity extends AppCompatActivity
{
    TextView tvAgtFirstName, tvAgtMiddleInitial, tvAgtLastName,
            tvAgtBusPhone, tvAgtEmail, tvAgtPosition, tvAgency;

    TextView tvTotalSales, tvTotalCommission;

    Button buttonEdit;

    ListView listAgentSales;

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
        setContentView(R.layout.activity_agent_detail);

        // back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // tvAgentId = findViewById(R.id.tvAgentId);
        tvAgtFirstName = findViewById(R.id.tvAgntFirstName);
        tvAgtMiddleInitial = findViewById(R.id.tvAgntMiddleInitial);
        tvAgtLastName = findViewById(R.id.tvAgntLastName);
        tvAgtBusPhone = findViewById(R.id.tvAgntPhone);
        tvAgtEmail = findViewById(R.id.tvAgntEmail);
        tvAgtPosition = findViewById(R.id.tvAgntPosition);
        tvAgency = findViewById(R.id.tvAgntAgency);

        tvTotalSales = findViewById(R.id.tvTotalSales);
        tvTotalCommission = findViewById(R.id.tvTotalCommission);
        listAgentSales = findViewById(R.id.listAgentSales);

        // buttons
        buttonEdit = findViewById(R.id.buttonEdit);

        final Agent agentObj = getIntent().getParcelableExtra("agent");

        final Integer agentId = agentObj.getAgentId();
        final String agtFirsName = agentObj.getAgtFirstName();
        final String agtMiddleInitial = agentObj.getAgtMiddleInitial();
        final String agtLastName = agentObj.getAgtLastName();
        final String agtBusPhone = agentObj.getAgtBusPhone();
        final String agtEmail = agentObj.getAgtEmail();
        final String agtPos = agentObj.getAgtPosition();
        final Integer agencyId = agentObj.getAgencyId();

        // agent booking data list
        Uri.Builder agentBookingURL = new Uri.Builder();
        agentBookingURL.scheme("https").authority(DBHelper.apiAuth())
                .appendPath("api")
                .appendPath("agent_booking_data.php")
                .appendQueryParameter("AgentId", String.valueOf(agentId));
        String agentBookingAPI = agentBookingURL.build().toString();

        BookingDB.BookingListData(agentBookingAPI, this, listAgentSales,
                tvTotalSales, tvTotalCommission);

        listAgentSales.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(getApplicationContext(), BookingDetailActivity.class);
                intent.putExtra("booking", (Booking) listAgentSales.getItemAtPosition(position));
                startActivity(intent);
            }
        });

        tvAgtFirstName.setText(agtFirsName);
        if (!agtMiddleInitial.equals("null"))
        {
            tvAgtMiddleInitial.setText(agtMiddleInitial);
        }
        tvAgtLastName.setText(agtLastName);
        tvAgtBusPhone.setText(agtBusPhone);
        tvAgtEmail.setText(agtEmail);
        tvAgtPosition.setText(agtPos);

        // set text for agency
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https").authority(DBHelper.apiAuth())
                .appendPath("api")
                .appendPath("agent_spinner_select.php")
                .appendQueryParameter("AgencyId", String.valueOf(agentObj.getAgencyId()));
        String myUrl = builder.build().toString();

        AgentDB.GetAgentAgencyText(myUrl,
                AgentDetailActivity.this, tvAgency);

        buttonEdit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AgentDetailActivity.this, AgentEditActivity.class);
                intent.putExtra("agentId", String.valueOf(agentId));
                intent.putExtra("agtFirstName", agtFirsName);
                intent.putExtra("agtMiddleInitial", agtMiddleInitial);
                intent.putExtra("agtLastName", agtLastName);
                intent.putExtra("agtBusPhone", agtBusPhone);
                intent.putExtra("agtEmail", agtEmail);
                intent.putExtra("agtPos", agtPos);
                intent.putExtra("agencyId", String.valueOf(agencyId));
                startActivity(intent);
            }
        });
    }
}

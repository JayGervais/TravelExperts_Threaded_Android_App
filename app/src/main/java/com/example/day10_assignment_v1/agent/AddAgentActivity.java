package com.example.day10_assignment_v1.agent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.day10_assignment_v1.MainActivity;
import com.example.day10_assignment_v1.R;
import com.example.day10_assignment_v1.agency.Agency;

public class AddAgentActivity extends AppCompatActivity
{
    EditText etAgtFirstName, etAgtMiddleInitial, etAgtLastName, etAgtBusPhone, etAgtEmail, etAgtPosition;
    Button btnSave, btnCancel;
    Spinner spinnerAgencies;

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
        setContentView(R.layout.activity_add_agent);

        // back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etAgtFirstName = findViewById(R.id.etAgtFirstName);
        etAgtMiddleInitial = findViewById(R.id.etAgtMiddleInitial);
        etAgtLastName = findViewById(R.id.etAgtLastName);
        etAgtBusPhone = findViewById(R.id.etAgtBusPhone);
        etAgtEmail = findViewById(R.id.etAgtEmail);
        etAgtPosition = findViewById(R.id.etAgtPosition);

        spinnerAgencies = findViewById(R.id.spinnerAgencies);

        // buttons
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        spinnerAgencies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Agency agency = (Agency) spinnerAgencies.getSelectedItem();
                // Toast.makeText(AddAgentActivity.this, String.valueOf(agency.getAgencyId()), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        // save button click listener
        btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Agency agency = (Agency) spinnerAgencies.getSelectedItem();
                AgentDBHelper.UpdateAgentData(null, etAgtFirstName.getText().toString(),
                        etAgtMiddleInitial.getText().toString(),
                        etAgtLastName.getText().toString(),
                        etAgtBusPhone.getText().toString(),
                        etAgtEmail.getText().toString(),
                        etAgtPosition.getText().toString(),
                        String.valueOf(agency.getAgencyId()),
                        //etAgencyId.getText().toString(),
                        "sait_oosd_2019_updateSecret",
                        "https://infastory.com/api/agent_add.php");
                Toast.makeText(AddAgentActivity.this, "New Agent Added", Toast.LENGTH_LONG).show();
                Intent savedIntent = new Intent(AddAgentActivity.this, AgentListActivity.class);
                AddAgentActivity.this.startActivity(savedIntent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent savedIntent = new Intent(AddAgentActivity.this, AgentListActivity.class);
                AddAgentActivity.this.startActivity(savedIntent);
            }
        });

        AgentDBHelper.GetAgencyData("https://infastory.com/api/agency_dropdown.php", this, spinnerAgencies);
    }


}

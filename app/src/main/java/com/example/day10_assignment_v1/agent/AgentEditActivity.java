package com.example.day10_assignment_v1.agent;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.example.day10_assignment_v1.DBHelper;
import com.example.day10_assignment_v1.R;
import com.example.day10_assignment_v1.agency.Agency;
import com.example.day10_assignment_v1.agency.AgencyDB;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class AgentEditActivity  extends AppCompatActivity
{
    EditText etAgentId, etAgtFirstName, etAgtMiddleInitial, etAgtLastName,
            etAgtBusPhone, etAgtEmail, etAgtPosition, etAgency;
    Spinner spinAgencies;
    Button btnSave, btnDelete, btnCancel;

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
        setContentView(R.layout.activity_agent_edit);

        // back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etAgentId = findViewById(R.id.etAgentId);
        etAgtFirstName = findViewById(R.id.etAgtFirstName);
        etAgtMiddleInitial = findViewById(R.id.etAgtMiddleInitial);
        etAgtLastName = findViewById(R.id.etAgtLastName);
        etAgtBusPhone = findViewById(R.id.etAgtBusPhone);
        etAgtEmail = findViewById(R.id.etAgtEmail);
        etAgtPosition = findViewById(R.id.etAgtPosition);
        spinAgencies = findViewById(R.id.spinAgencies);
        etAgency = findViewById(R.id.etAgency);

        // buttons
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent intent = getIntent();
        String agentId = intent.getStringExtra("agentId");
        String agtFirstName = intent.getStringExtra("agtFirstName");
        String agtMiddleInitial = intent.getStringExtra("agtMiddleInitial");
        String agtLastName = intent.getStringExtra("agtLastName");
        String agtBusPhone = intent.getStringExtra("agtBusPhone");
        String agtEmail = intent.getStringExtra("agtEmail");
        String agtPos = intent.getStringExtra("agtPos");
        String agencyId = intent.getStringExtra("agencyId");

        // agency fields
        etAgentId.setText(String.valueOf(agentId));
        etAgtFirstName.setText(agtFirstName);
        if (!agtMiddleInitial.equals("null"))
        {
            etAgtMiddleInitial.setText(agtMiddleInitial);
        }
        etAgtLastName.setText(agtLastName);
        etAgtBusPhone.setText(agtBusPhone);
        etAgtEmail.setText(agtEmail);
        etAgtPosition.setText(agtPos);

        // set spinner data
        spinAgencies.setVisibility(View.INVISIBLE);

        // set text for agency
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https").authority(DBHelper.apiAuth())
                .appendPath("api")
                .appendPath("agent_spinner_select.php")
                .appendQueryParameter("AgencyId", String.valueOf(agencyId));
        String myUrl = builder.build().toString();

        AgentDB.GetAgentListString(myUrl,
                AgentEditActivity.this, etAgency);

        spinAgencies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Agency agentSelect = (Agency) spinAgencies.getSelectedItem();
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
                // getAgentTextData();
                Agency agentSelect = (Agency) spinAgencies.getSelectedItem();
                AgentDB.UpdateAgent(etAgentId.getText().toString(),
                        etAgtFirstName.getText().toString(),
                        etAgtMiddleInitial.getText().toString(),
                        etAgtLastName.getText().toString(),
                        etAgtBusPhone.getText().toString(),
                        etAgtEmail.getText().toString(),
                        etAgtPosition.getText().toString(),
                        String.valueOf(agentSelect.getAgencyId()),
                        "api_updateAgent_activitySecret",
                        DBHelper.apiURL() + "/api/agent_update.php",
                        AgentEditActivity.this);
                Toast.makeText(AgentEditActivity.this, "Changes Saved", Toast.LENGTH_LONG).show();
                Intent savedIntent = new Intent(AgentEditActivity.this, AgentDetailActivity.class);
                AgentEditActivity.this.startActivity(savedIntent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new AlertDialog.Builder(AgentEditActivity.this)
                        .setTitle("Delete Agent")
                        .setMessage("Are you sure you want to delete Agent, " + etAgtFirstName.getText() + " " + etAgtLastName.getText() + "?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton)
                            {
                                AgentDB.DeleteAgent(etAgentId.getText().toString(),
                                        "api_secretKey_deleteAgent",
                                        DBHelper.apiURL() + "/api/agent_delete.php",
                                        AgentEditActivity.this);
                                Toast.makeText(AgentEditActivity.this, "Agent Deleted Successfully", Toast.LENGTH_LONG).show();
                                Intent savedIntent = new Intent(AgentEditActivity.this, AgentListActivity.class);
                                AgentEditActivity.this.startActivity(savedIntent);
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent savedIntent = new Intent(AgentEditActivity.this, AgentListActivity.class);
                AgentEditActivity.this.startActivity(savedIntent);
            }
        });

        AgencyDB.GetAgencyDataDropdown(DBHelper.apiURL() + "/api/agency_dropdown.php", this, spinAgencies);
    }

}

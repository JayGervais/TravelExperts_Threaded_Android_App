package com.example.day10_assignment_v1.agent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.day10_assignment_v1.R;
import com.example.day10_assignment_v1.agency.Agency;

public class AgentDetailActivity extends AppCompatActivity
{
    EditText etAgentId, etAgtFirstName, etAgtMiddleInitial, etAgtLastName,
            etAgtBusPhone, etAgtEmail, etAgtPosition, etAgency;
    Spinner spinAgencies;
    Button btnSave, btnEdit, btnDelete, btnCancel;

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
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        final Agent agent = (Agent) getIntent().getSerializableExtra("agent");

        etAgentId.setText(String.valueOf(agent.getAgentId()));
        etAgtFirstName.setText(agent.getAgtFirstName());
        if (!agent.getAgtMiddleInitial().equals("null"))
        {
            etAgtMiddleInitial.setText(agent.getAgtMiddleInitial());
        }
        etAgtLastName.setText(agent.getAgtLastName());
        etAgtBusPhone.setText(agent.getAgtBusPhone());
        etAgtEmail.setText(agent.getAgtEmail());
        etAgtPosition.setText(agent.getAgtPosition());

        // set spinner data
        spinAgencies.setVisibility(View.INVISIBLE);

        // set text for agency
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https").authority("infastory.com")
                .appendPath("api")
                .appendPath("agent_spinner_select.php")
                .appendQueryParameter("AgencyId", String.valueOf(agent.getAgencyId()));
        String myUrl = builder.build().toString();

        AgentDB.GetAgentListString(myUrl,
                AgentDetailActivity.this, etAgency);

        textFieldEnabled(false);
        btnSave.setEnabled(false);
        btnDelete.setEnabled(false);
        btnCancel.setEnabled(false);
        etAgency.setEnabled(false);

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

        btnEdit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                textFieldEnabled(true);
                btnSave.setEnabled(true);
                btnCancel.setEnabled(true);
                btnEdit.setEnabled(false);
                btnDelete.setEnabled(true);
                spinAgencies.setVisibility(View.VISIBLE);
                spinAgencies.setSelection(agent.getAgencyId()-1);
                etAgency.setVisibility(View.INVISIBLE);
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
                        "https://infastory.com/api/agent_update.php",
                        AgentDetailActivity.this);
                Toast.makeText(AgentDetailActivity.this, "Changes Saved", Toast.LENGTH_LONG).show();
                Intent savedIntent = new Intent(AgentDetailActivity.this, AgentListActivity.class);
                AgentDetailActivity.this.startActivity(savedIntent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new AlertDialog.Builder(AgentDetailActivity.this)
                        .setTitle("Delete Agent")
                        .setMessage("Are you sure you want to delete Agent, " + etAgtFirstName.getText() + " " + etAgtLastName.getText() + "?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton)
                            {
                                AgentDB.DeleteAgent(etAgentId.getText().toString(),
                                        "api_secretKey_deleteAgent",
                                        "https://infastory.com/api/agent_delete.php",
                                        AgentDetailActivity.this);
                                Toast.makeText(AgentDetailActivity.this, "Agent Deleted Successfully", Toast.LENGTH_LONG).show();
                                Intent savedIntent = new Intent(AgentDetailActivity.this, AgentListActivity.class);
                                AgentDetailActivity.this.startActivity(savedIntent);
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent savedIntent = new Intent(AgentDetailActivity.this, AgentListActivity.class);
                AgentDetailActivity.this.startActivity(savedIntent);
            }
        });

        AgentDB.GetAgencyData("https://infastory.com/api/agency_dropdown.php", this, spinAgencies);
    }

    public void textFieldEnabled(boolean enabled)
    {
        etAgtFirstName.setEnabled(enabled);
        etAgtMiddleInitial.setEnabled(enabled);
        etAgtLastName.setEnabled(enabled);
        etAgtBusPhone.setEnabled(enabled);
        etAgtEmail.setEnabled(enabled);
        etAgtPosition.setEnabled(enabled);
        spinAgencies.setEnabled(enabled);
    }

}

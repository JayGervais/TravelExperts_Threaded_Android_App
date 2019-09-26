package com.example.day10_assignment_v1.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.day10_assignment_v1.R;
import com.example.day10_assignment_v1.agent.Agent;
import com.example.day10_assignment_v1.agent.AgentDBHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomerListActivity extends AppCompatActivity {
    ListView lvCustomers;
    Button btnNewCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);


        lvCustomers = findViewById(R.id.lvCustomers);
        btnNewCustomer = findViewById(R.id.btnNewCustomer);

        CustomerDBHelper.GetCustomerListData("https://infastory.com/api/customer_data.php", this, lvCustomers);
    }
}

package com.example.day10_assignment_v1.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.day10_assignment_v1.R;

public class CustomerListActivity extends AppCompatActivity {
    ListView lvCustomers;
    Button btnNewCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);


        lvCustomers = findViewById(R.id.lvCustomers);
        btnNewCustomer = findViewById(R.id.btnNewCustomer);


    }
}

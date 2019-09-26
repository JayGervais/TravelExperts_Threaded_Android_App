package com.example.day10_assignment_v1.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.day10_assignment_v1.R;

public class CustomerDetailActivity extends AppCompatActivity {

    EditText etCustomerId, etCustFirstName, etCustLastName, etCustAddress,
            etCustCity, etCustProv, etCustPostal, etCustCountry, etCustHomePhone,
            etCustBusPhone, etCustEmail,etAgency;
    Button btnSaveCustomer, btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);

        etCustomerId = findViewById(R.id.etCustomerId);
        etCustFirstName=findViewById(R.id.etCustFirstName);
        etCustLastName = findViewById(R.id.etCustLastName);
        etCustAddress=findViewById(R.id.etCustAddress);
        etCustCity = findViewById(R.id.etCustCity);
        etCustProv=findViewById(R.id.etCustProv);
        etCustPostal = findViewById(R.id.etCustPostal);
        etCustCountry=findViewById(R.id.etCustCountry);
        etCustHomePhone = findViewById(R.id.etCustHomePhone);
        etCustBusPhone=findViewById(R.id.etBusPhone);
        etCustEmail=findViewById(R.id.etCustEmail);
        etCustFirstName=findViewById(R.id.etCustFirstName);
        etAgency=findViewById(R.id.etAgency);
        btnSaveCustomer=findViewById(R.id.btnNewCustomer);
        btnCancel=findViewById(R.id.btnCancelCust);
    }

}
package com.example.day10_assignment_v1.customer;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.day10_assignment_v1.DBHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomerDB
{
    public static void SelectedCustomerData(final String urlWebService, final Context cont,
                                            final TextView tvCustFirstName, final TextView tvCustLastName,
                                            final TextView tvCustPhone, final  TextView tvCustEmail)
    {
        class DownloadJSON extends AsyncTask<Void, Void, String>
        {
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
                try
                {
                    JSONArray jsonArray = new JSONArray(s);
                    String[] customers = new String[jsonArray.length()];
                    String[] cFName = new String[jsonArray.length()];
                    String[] cLName = new String[jsonArray.length()];
                    String[] cPhone = new String[jsonArray.length()];
                    String[] cEmail = new String[jsonArray.length()];

                    ArrayAdapter<Customer> arrayAdapter = new ArrayAdapter<>(cont, android.R.layout.simple_list_item_1);
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject obj = jsonArray.getJSONObject(i);

                        arrayAdapter.add(new Customer(Integer.parseInt(obj.getString("CustomerId")),
                                obj.getString("CustFirstName"),
                                obj.getString("CustLastName"),
                                obj.getString("CustHomePhone"),
                                obj.getString("CustEmail")));

                        customers[i] = obj.getString("CustFirstName") + " " + obj.getString("CustLastName");

                        // set variables
                        cFName[i] = obj.getString("CustFirstName");
                        cLName[i] = obj.getString("CustLastName");
                        cPhone[i] = obj.getString("CustHomePhone");
                        cEmail[i] = obj.getString("CustEmail");
                    }

                    // set text fields
                    tvCustFirstName.setText(cFName[0]);
                    tvCustLastName.setText(cLName[0]);
                    tvCustPhone.setText(cPhone[0]);
                    tvCustEmail.setText(cEmail[0]);

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
            @Override
            protected String doInBackground(Void... voids)
            {
                return DBHelper.urlInputStream(urlWebService);
            }
        }
        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
    }
}
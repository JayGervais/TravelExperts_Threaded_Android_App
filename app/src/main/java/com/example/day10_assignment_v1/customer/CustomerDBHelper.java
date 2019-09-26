package com.example.day10_assignment_v1.customer;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomerDBHelper {
    public static void GetCustomerListData(final String urlWebService, final Context cont, final ListView list)
    {
        class DownloadJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONArray jsonArray = new JSONArray(s);
                    String[] customers = new String[jsonArray.length()];
                    ArrayAdapter<Customer> arrayAdapter = new ArrayAdapter<>(cont, android.R.layout.simple_list_item_1);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);

                        arrayAdapter.add(new Customer(Integer.parseInt(obj.getString("CustomerId")),
                                obj.getString("CustFirstName"),
                                obj.getString("CustLastName"),
                                obj.getString("CustAddress"),
                                obj.getString("CustCity"),
                                obj.getString("CustProv"),
                                obj.getString("CustPostal"),
                                obj.getString("CustCountry"),
                                obj.getString("CustHomePhone"),
                                obj.getString("CustBusPhone"),
                                obj.getString("CustEmail"),
                                Integer.parseInt(obj.getString("AgentId"))));

                        customers[i] = obj.getString("CustFirstName") + " " + obj.getString("CustLastName");
                    }
                    list.setAdapter(arrayAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

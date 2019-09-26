package com.example.day10_assignment_v1.agency;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.day10_assignment_v1.DBHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AgencyDB
{
    // gets agency data to display on agent drop down spinner
    public static void GetAgencyDataDropdown(final String urlWebService, final Context context, final Spinner spinner)
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
                    String[] agency = new String[jsonArray.length()];
                    ArrayAdapter<Agency> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1);
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject obj = jsonArray.getJSONObject(i);

                        arrayAdapter.add(new Agency(
                                Integer.parseInt(obj.getString("AgencyId")),
                                obj.getString("AgncyAddress"),
                                obj.getString("AgncyCity")));

                        agency[i] = obj.getString("AgencyId") + " " + obj.getString("AgncyAddress") + " " + obj.getString("AgncyCity");
                    }
                    spinner.setAdapter(arrayAdapter);
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

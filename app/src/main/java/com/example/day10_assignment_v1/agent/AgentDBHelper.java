package com.example.day10_assignment_v1.agent;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.day10_assignment_v1.agency.Agency;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AgentDBHelper
{
    // update agent function used for both creating and updating agents (using volley)
    public static void UpdateAgent(final String agentId, final String agtFirstName, final String agtMiddleInitial,
                                   final String agtLastName, final String agtBusPhone, final String agtEmail,
                                   final String agtPosition, final String agencyId, final String apiSecret,
                                   final String url, final Context context)
    {
        final StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String ServerResponse)
            {
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.dismiss();
            }
        },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError volleyError)
                    {
                        ProgressDialog progressDialog = new ProgressDialog(context);
                        progressDialog.dismiss();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> parameters = new HashMap<>();
                if (agentId != null)
                {
                    parameters.put("agentId", agentId);
                }
                parameters.put("agtFirstName", agtFirstName);
                parameters.put("agtMiddleInitial", agtMiddleInitial);
                parameters.put("agtLastName", agtLastName);
                parameters.put("agtBusPhone", agtBusPhone);
                parameters.put("agtEmail", agtEmail);
                parameters.put("agtPosition", agtPosition);
                parameters.put("agencyId", agencyId);
                parameters.put("apiSecret", apiSecret);
                return parameters;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }

    // delete agent function (using volley)
    public static void DeleteAgent(final String agentId, final String apiSecret,
                                   final String url, final Context context)
    {
        final StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String ServerResponse)
            {
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.dismiss();
            }
        },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError volleyError)
                    {
                        ProgressDialog progressDialog = new ProgressDialog(context);
                        progressDialog.dismiss();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> parameters = new HashMap<>();
                parameters.put("agentId", agentId);
                parameters.put("apiSecret", apiSecret);
                return parameters;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }

    // get agent data and display in list view
    public static void GetAgentListData(final String urlWebService, final Context cont, final ListView list)
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
                    String[] agents = new String[jsonArray.length()];
                    ArrayAdapter<Agent> arrayAdapter = new ArrayAdapter<>(cont, android.R.layout.simple_list_item_1);
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject obj = jsonArray.getJSONObject(i);

                        arrayAdapter.add(new Agent(Integer.parseInt(obj.getString("AgentId")),
                                obj.getString("AgtFirstName"),
                                obj.getString("AgtMiddleInitial"),
                                obj.getString("AgtLastName"),
                                obj.getString("AgtBusPhone"),
                                obj.getString("AgtEmail"),
                                obj.getString("AgtPosition"),
                                Integer.parseInt(obj.getString("AgencyId"))));

                        agents[i] = obj.getString("AgtFirstName") + " " + obj.getString("AgtLastName");
                    }
                    list.setAdapter(arrayAdapter);
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids)
            {
                try
                {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null)
                    {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e)
                {
                    return null;
                }
            }
        }
        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
    }

    // get agency data to display in drop down menu
    public static void GetAgencyData(final String urlWebService, final Context context, final Spinner spinner)
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
                try
                {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null)
                    {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e)
                {
                    return null;
                }
            }
        }
        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
    }

    // saves agency text for agent
    public static void GetAgentListString(final String urlWebService, final Context cont, final EditText txt)
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
                    ArrayAdapter<Agency> arrayAdapter = new ArrayAdapter<>(cont, android.R.layout.simple_list_item_1);
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject obj = jsonArray.getJSONObject(i);

                        arrayAdapter.add(new Agency(Integer.parseInt(
                                obj.getString("AgencyId")),
                                obj.getString("AgncyAddress"),
                                obj.getString("AgncyCity")));

                        agency[i] = obj.getString("AgncyAddress") + ", " + obj.getString("AgncyCity");
                    }
                    txt.setText(agency[0]);
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids)
            {
                try
                {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null)
                    {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e)
                {
                    return null;
                }
            }
        }
        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
    }

}
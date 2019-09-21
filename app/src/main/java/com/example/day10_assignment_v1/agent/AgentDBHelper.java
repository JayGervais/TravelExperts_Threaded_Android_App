package com.example.day10_assignment_v1.agent;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.day10_assignment_v1.agency.Agency;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AgentDBHelper
{
    // load agent data
    public static void UpdateAgentData(final String agentId, final String agtFirstName, final String agtMiddleInitial,
                                       final String agtLastName, final String agtBusPhone, final String agtEmail,
                                       final String agtPosition, final String agencyId, final String apiSecret, final String url)
    {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String>
        {
            @Override
            protected String doInBackground(String... params)
            {
                List<NameValuePair> values = new ArrayList<>();
                values.add(new BasicNameValuePair("agentId", agentId));
                values.add(new BasicNameValuePair("agtFirstName", agtFirstName));
                values.add(new BasicNameValuePair("agtMiddleInitial", agtMiddleInitial));
                values.add(new BasicNameValuePair("agtLastName", agtLastName));
                values.add(new BasicNameValuePair("agtBusPhone", agtBusPhone));
                values.add(new BasicNameValuePair("agtEmail", agtEmail));
                values.add(new BasicNameValuePair("agtPosition", agtPosition));
                values.add(new BasicNameValuePair("agencyId", agencyId));
                values.add(new BasicNameValuePair("apiSecret", apiSecret));

                try
                {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(url);
                    httpPost.setEntity(new UrlEncodedFormEntity(values));
                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    HttpEntity httpEntity = httpResponse.getEntity();

                } catch (ClientProtocolException e)
                {
                } catch (IOException e)
                {
                    return "Could not update database";
                }
                return "Agent Updated Successfully";
            }

            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(agencyId, agtFirstName, agtMiddleInitial, agtLastName, agtBusPhone, agtEmail, agtPosition, agencyId, apiSecret);
    }

    // delete agent data
    public static void DeleteAgent(final String agentId, final String apiSecret)
    {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String>
        {
            @Override
            protected String doInBackground(String... params)
            {
                List<NameValuePair> values = new ArrayList<>();
                values.add(new BasicNameValuePair("agentId", agentId));
                values.add(new BasicNameValuePair("apiSecret", apiSecret));

                try
                {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("https://infastory.com/api/agent_delete.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(values));
                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    HttpEntity httpEntity = httpResponse.getEntity();

                } catch (ClientProtocolException e)
                {
                } catch (IOException e)
                {
                    return "Could not update database";
                }
                return "Agent Deleted Successfully";
            }

            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(agentId, apiSecret);
    }

    // get agency data for dropdown menu
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
                    loadAgencyDataIntoListView(s, context, spinner);
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

    private static void loadAgencyDataIntoListView(String json, Context context, Spinner spinner) throws JSONException
    {
        JSONArray jsonArray = new JSONArray(json);
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
    }

    // get agent list data
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
                    loadAgentListView(s, cont, list);
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

    private static void loadAgentListView(String json, Context cont, ListView list) throws JSONException
    {
        JSONArray jsonArray = new JSONArray(json);
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
    }

    // saves agency text for agent
    public static void GetAgentListData(final String urlWebService, final Context cont, final EditText txt)
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
                    getTextAgencyData(s, cont, txt);
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

    private static void getTextAgencyData(String json, Context cont, EditText txt) throws JSONException
    {
        JSONArray jsonArray = new JSONArray(json);
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
    }
}

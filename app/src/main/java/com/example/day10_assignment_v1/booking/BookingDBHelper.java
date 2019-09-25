package com.example.day10_assignment_v1.booking;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class BookingDBHelper
{
    // get booking summary data to display in fragment
    public static void BookingListData(final String urlWebService, final Context cont, final ListView list)
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
                    String[] bookings = new String[jsonArray.length()];

                    ArrayAdapter<Booking> arrayAdapter = new ArrayAdapter<>(cont, android.R.layout.simple_list_item_1);
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject obj = jsonArray.getJSONObject(i);

                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                        arrayAdapter.add(new Booking(obj.getInt("BookingId"),
                                obj.getString("BookingNo"),
                                formatter.parse(obj.getString("BookingDate")),
                                Integer.parseInt(obj.getString("TravelerCount")),
                                obj.getString("Description"),
                                BigDecimal.valueOf(obj.getDouble("BasePrice")),
                                BigDecimal.valueOf(obj.getDouble("AgencyCommission"))));

                        bookings[i] = obj.getInt("BookingId") + obj.getString("BookingDate");
                    }

                    list.setAdapter(arrayAdapter);

                } catch (JSONException e)
                {
                    e.printStackTrace();
                } catch (ParseException e)
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


    public static void BookingData(final String urlWebService, final Context cont,
                                   final TextView tvBookingDate, final TextView tvBookingNo,
                                   final TextView tvTravelerCount, final  TextView tvDestination,
                                   final  TextView tvBasePrice, final  TextView tvAgencyCommission)
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
                    String[] bookings = new String[jsonArray.length()];
                    String[] bDate = new String[jsonArray.length()];
                    String[] bNo = new String[jsonArray.length()];
                    String[] bTravelers = new String[jsonArray.length()];
                    String[] bDest = new String[jsonArray.length()];
                    String[] bBPrice = new String[jsonArray.length()];
                    String[] bComm = new String[jsonArray.length()];

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    ArrayAdapter<Booking> arrayAdapter = new ArrayAdapter<>(cont, android.R.layout.simple_list_item_1);
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject obj = jsonArray.getJSONObject(i);

                        arrayAdapter.add(new Booking(obj.getInt("BookingId"),
                                obj.getString("BookingNo"),
                                formatter.parse(obj.getString("BookingDate")),
                                Integer.parseInt(obj.getString("TravelerCount")),
                                obj.getString("Description"),
                                BigDecimal.valueOf(obj.getDouble("BasePrice")),
                                BigDecimal.valueOf(obj.getDouble("AgencyCommission"))));

                        // set variables
                        bookings[i] = obj.getInt("BookingId") + obj.getString("BookingDate");

                        // date format
                        String bDateFormat = String.valueOf(formatter.parse(obj.getString("BookingDate")));
                        bDate[i] = bDateFormat;

                        bNo[i] = obj.getString("BookingNo");
                        bTravelers[i] = obj.getString("TravelerCount");
                        bDest[i] = obj.getString("Destination");

                        // currency formatting
                        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
                        String baseFormat = currencyFormat.format(BigDecimal.valueOf(obj.getDouble("BasePrice")));
                        String bComFormat = currencyFormat.format(BigDecimal.valueOf(obj.getDouble("AgencyCommission")));
                        bBPrice[i] = baseFormat;
                        bComm[i] = bComFormat;
                    }

                    // set text fields
                    tvBookingDate.setText(bDate[0]);
                    tvBookingNo.setText(bNo[0]);
                    tvTravelerCount.setText(bTravelers[0]);
                    tvDestination.setText(bDest[0]);
                    tvBasePrice.setText(bBPrice[0]);
                    tvAgencyCommission.setText(bComm[0]);

                } catch (JSONException e)
                {
                    e.printStackTrace();
                } catch (ParseException e)
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

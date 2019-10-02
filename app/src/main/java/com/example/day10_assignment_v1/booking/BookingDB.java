package com.example.day10_assignment_v1.booking;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.day10_assignment_v1.DBHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class BookingDB
{
    // get booking summary data to display in fragment
    public static void BookingListData(final String urlWebService, final Context cont,
                                       final ListView list, final TextView baseTotal,
                                       final TextView commTotal)
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
                    Double[] bookingTotal = new Double[jsonArray.length()];
                    Double[] commissionTotal = new Double[jsonArray.length()];
                    Double finalBookingSum = 0.0;
                    Double finalCommissionSum = 0.0;

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
                                obj.getString("Destination"),
                                BigDecimal.valueOf(obj.getDouble("BasePrice")),
                                BigDecimal.valueOf(obj.getDouble("AgencyCommission")),
                                formatter.parse(obj.getString("TripStart")),
                                formatter.parse(obj.getString("TripEnd")),
                                obj.getString("ClassName"),
                                obj.getInt("CustomerId")
                                ));

                        bookings[i] = obj.getInt("BookingId") + obj.getString("BookingDate");

                        // get total sum of bookings
                        bookingTotal[i] = obj.getDouble("BasePrice");
                        finalBookingSum = finalBookingSum + bookingTotal[i];
                        // get total commission sum
                        commissionTotal[i] = obj.getDouble("AgencyCommission");
                        finalCommissionSum = finalCommissionSum + commissionTotal[i];
                    }
                    list.setAdapter(arrayAdapter);

                    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
                    String finalBookingFormat = currencyFormat.format(finalBookingSum);
                    String finalCommissionFormat = currencyFormat.format(finalCommissionSum);
                    baseTotal.setText(String.valueOf(finalBookingFormat));
                    commTotal.setText(String.valueOf(finalCommissionFormat));

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
                return DBHelper.urlInputStream(urlWebService);
            }
        }
        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
    }

    // redo this for easier re-usability **
    public static void BookingData(final String urlWebService, final Context cont,
                                   final TextView tvBookingDate, final TextView tvBookingNo,
                                   final TextView tvTravelerCount, final  TextView tvDestination,
                                   final  TextView tvBasePrice, final  TextView tvAgencyCommission,
                                   final TextView tvDescription, final TextView tvStartDate,
                                   final TextView tvEndDate, final TextView tvClassName)
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
                    String[] bDesc = new String[jsonArray.length()];
                    String[] bSDate = new String[jsonArray.length()];
                    String[] bEDate = new String[jsonArray.length()];
                    String[] bClass = new String[jsonArray.length()];

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
                                obj.getString("Destination"),
                                BigDecimal.valueOf(obj.getDouble("BasePrice")),
                                BigDecimal.valueOf(obj.getDouble("AgencyCommission")),
                                formatter.parse(obj.getString("TripStart")),
                                formatter.parse(obj.getString("TripEnd")),
                                obj.getString("ClassName"),
                                obj.getInt("CustomerId")
                                ));

                        // set variables
                        bookings[i] = obj.getInt("BookingId") + obj.getString("BookingDate");
                        // dates
                        bDate[i] = obj.getString("BookingDate").substring(0, 10);
                        bSDate[i] = obj.getString("TripStart").substring(0, 10);
                        bEDate[i] = obj.getString("TripEnd").substring(0, 10);

                        bNo[i] = obj.getString("BookingNo");
                        bTravelers[i] = obj.getString("TravelerCount");
                        bDest[i] = obj.getString("Destination");
                        bDesc[i] = obj.getString("Description");
                        bClass[i] = obj.getString("ClassName");

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
                    tvDescription.setText(bDesc[0]);
                    tvStartDate.setText(bSDate[0]);
                    tvEndDate.setText(bEDate[0]);
                    tvClassName.setText(bClass[0]);

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
                return DBHelper.urlInputStream(urlWebService);
            }
        }
        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
    }
}

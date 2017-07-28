package com.example.admin.movieappv2;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Admin on 28/07/2017.
 */

public class VolleyLib {
    Context context;
    ArrayList<Movie> mylist;
    Adapter myadapter;
    RequestQueue requestQueue;
ArrayList<Movie>FavouriteList;

    public Adapter getMyadapter() {
        return myadapter;
    }

    public ArrayList<Movie> getMylist() {
        return mylist;
    }

    public VolleyLib(Context context, RequestQueue requestQueue) {
        this.context = context;
        mylist = new ArrayList<>();
        FavouriteList= new ArrayList<>();
        this.requestQueue = requestQueue;
        myadapter = new Adapter(mylist, context);

    }

    public void volleyStringRquest(String Url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Url, null,

                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray;
                            jsonArray = null;
                            jsonArray = response.getJSONArray("results");
                            if (mylist.size() > 0)
                                mylist.clear();
                            myadapter.notifyDataSetChanged();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Movie movie = new Movie();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                movie.setMovie_id(jsonObject.getString("id"));
                                movie.setName(jsonObject.getString("title"));
                                movie.setImageUrl(jsonObject.getString("poster_path"));
                                movie.setOverview(jsonObject.getString("overview"));
                                movie.setRelease_date(jsonObject.getString("release_date"));
                                mylist.add(movie);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
//                            textView.setText("JSONException");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        textView.setText("onErrorResponse");
                    }
                }


        );
        requestQueue.add(jsonObjectRequest);
    }

//    public ArrayList<Movie> SearchByid(final String id){
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Url, null,
//
//                new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray jsonArray;
//                            JSONObject jsonObject;
//                            jsonArray = null;
//                            jsonArray = response.getJSONArray("results");
//                            if (mylist.size() > 0)
//                                mylist.clear();
//                            myadapter.notifyDataSetChanged();
//                            for (int i = 0; i < jsonArray.length(); i++) {
//
//                                Movie movie = new Movie();
//                                jsonObject = jsonArray.getJSONObject(i);
//                                if(jsonObject.get("id")==id)
//                                {
//                                    movie.setMovie_id(jsonObject.getString("id"));
//                                    movie.setName(jsonObject.getString("title"));
//                                    movie.setImageUrl(jsonObject.getString("poster_path"));
//                                    movie.setOverview(jsonObject.getString("overview"));
//                                    movie.setRelease_date(jsonObject.getString("release_date"));
//                                    FavouriteList.add(movie);
//                                }
//
//
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
////                            textView.setText("JSONException");
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
////                        textView.setText("onErrorResponse");
//                    }
//                }
//
//
//        );
//        requestQueue.add(jsonObjectRequest);
//        return FavouriteList;
//    }


}

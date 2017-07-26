package com.example.admin.movieappv2;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ImageView imageView;
    ArrayList<Movie> mylist;
    TextView textView;
    Adapter myadapter;
    RequestQueue requestQueue;
    Intent intent;
    Toolbar toolbar;
    String TopRatedUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setTitle("Top Rated");
        toolbar = (Toolbar) findViewById(R.id.firsttoolbar);
        setSupportActionBar(toolbar);
        setTitle("Top Rated");
        //  ed_MovieName=(EditText)findViewById(R.id.ET_MovieName);
        // ed_MovieRate=(EditText)findViewById(R.id.ET_MovieRate);
        //ed_MovieDescription=(EditText)findViewById(R.id.ET_MovieDescription);

//        imageView = (ImageView) findViewById(R.id.FirstMovieImage);

        //textView = (TextView) findViewById(R.id.Test);

        requestQueue = Volley.newRequestQueue(this);
        recyclerView = (RecyclerView) findViewById(R.id.RV_myView);
        intent = new Intent(this, Main3Activity.class);
        //startActivity(intent);
        TopRatedUrl = "https://api.themoviedb.org/3/movie/top_rated?api_key=a2a6577bddba0b92a5bb9571adf4f098";

        volleyStringRquest(TopRatedUrl);


        mylist = new ArrayList<>();


        myadapter = new Adapter(mylist, this);


        recyclerView.setAdapter(myadapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        // Toast toast = Toast.makeText(this, String.valueOf(mylist.size()),Toast.LENGTH_SHORT);

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
                            textView.setText("JSONException");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("onErrorResponse");
                    }
                }


        );
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.toprated) {
            TopRatedUrl = "https://api.themoviedb.org/3/movie/top_rated?api_key=a2a6577bddba0b92a5bb9571adf4f098";
            volleyStringRquest(TopRatedUrl);
            setTitle("Top Rated");
        } else if (item.getItemId() == R.id.mostpopular) {
            TopRatedUrl = "https://api.themoviedb.org/3/movie/popular?api_key=a2a6577bddba0b92a5bb9571adf4f098";
            volleyStringRquest(TopRatedUrl);
            setTitle("Most Popular");
        }
        return super.onOptionsItemSelected(item);
    }
}



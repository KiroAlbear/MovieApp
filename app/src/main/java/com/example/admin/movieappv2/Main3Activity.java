package com.example.admin.movieappv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    Bundle bundle;
    TextView moviename;
    Movie movie;
    TextView overview;
    ImageView smallimage;
    TextView releasedate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);
//
        moviename = (TextView) findViewById(R.id.MovieName);
        overview = (TextView) findViewById(R.id.overView);
        smallimage = (ImageView) findViewById(R.id.smallImage);
        releasedate = (TextView) findViewById(R.id.releasDate);

        bundle = getIntent().getExtras();

        movie = (Movie) getIntent().getSerializableExtra("obj");


        moviename.setText(movie.getName());

        overview.setText(movie.getOverview());

        releasedate.setText(movie.release_date);
        Picasso.with(this).load("http://image.tmdb.org/t/p/w185" + movie.getImageUrl()).into(this.smallimage);

//        moviename = (TextView) findViewById(R.id.MovieName);
//
//       int position = Integer.parseInt(intent.get("position").toString());
////
//
//
        // mylist = (ArrayList<Movie>) getIntent().getParcelableExtra("List");
//
//        moviename.setText(mylist.get(position).getName());
//        if (str.length()==1)
//
//            else if (str.length()>1){
//            moviename.setText("more than");
//        }


        // Log.e("This is my position",str);


    }
}

package com.example.admin.movieappv2;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Main3Activity extends AppCompatActivity {
    Helper helper;
    Bundle bundle;
    TextView moviename;
    Movie movie;
    TextView overview;
    ImageView smallimage;
    TextView releasedate;
    Button favourite;
    boolean pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.movie_details);
        favourite = (Button) findViewById(R.id.favourit);
        moviename = (TextView) findViewById(R.id.MovieName);
        overview = (TextView) findViewById(R.id.overView);
        smallimage = (ImageView) findViewById(R.id.smallImage);
        releasedate = (TextView) findViewById(R.id.releasDate);
        helper = new Helper(this);
        bundle = getIntent().getExtras();
         pressed =false;
        movie = (Movie) getIntent().getSerializableExtra("obj");


        moviename.setText(movie.getName());

        overview.setText(movie.getOverview());

        releasedate.setText(movie.release_date);
        //  ((Editable) releasedate.getText()).insert(23, movie.release_date);
        Picasso.with(this).load("http://image.tmdb.org/t/p/w185" + movie.getImageUrl()).into(this.smallimage);

//        moviename = (TextView) findViewById(R.id.MovieName);
        favourite.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // favourite.setBackgroundDrawable(getResources().getDrawable(R.drawable.round_button));
                    // favourite.setBackgroundColor(Color.parseColor("#5b5f66"));


                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if((helper.IsMovieExist(movie.getMovie_id()))){
                        Toast t =  Toast.makeText(getBaseContext(),"Already Exist", Toast.LENGTH_SHORT);
                        t.show();
                    }
                    else
                    {
                        InsertData(movie.getMovie_id(),movie.getName(),movie.getOverview(),movie.getRelease_date(),movie.getImageUrl());
                    }

                    // favourite.setBackgroundColor(Color.parseColor("#ed00e4cd"));
                    //favourite.setBackgroundDrawable(getResources().getDrawable(R.drawable.round_button));
                    //  image.setBounds(0,0,80,80);
                    favourite.setBackgroundResource(R.drawable.round_button);
//            favourite.setCompoundDrawables(null,null,image ,null);

                }
                return false;
            }
        });

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

    public void InsertData(String id,String Name,String overView,String DataReleased,String imageURL) {
        helper.Insert(id,Name,overView,DataReleased,imageURL);
        Toast toast =  Toast.makeText(this,"Added To Favourite", Toast.LENGTH_SHORT);
       toast.show();
    }

}

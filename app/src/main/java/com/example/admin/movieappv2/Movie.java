package com.example.admin.movieappv2;


import java.io.Serializable;

public class Movie implements Serializable {
     int Rate;
     String Name;
     String overview;
     String imageUrl;
     String movie_id;
    String release_date;

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int popularity;



    public void setRate(int rate) {Rate = rate;}

    public void setName(String name) {Name = name;}

    public void setOverview(String overview) {this.overview = overview;}

    public void setImageUrl(String url) {imageUrl=url;}

    public void setMovie_id(String id) {
        movie_id = id;
    }

    public void setPopularity(int popularity){this.popularity=popularity;}

    public int getRate() {return Rate;}

    public String getName() {return Name;}

    public String getOverview() {
        return overview;
    }

    public String getImageUrl(){return imageUrl;}

    public String getMovie_id() {return movie_id;}

    public int getPopularity(){return popularity;}
}

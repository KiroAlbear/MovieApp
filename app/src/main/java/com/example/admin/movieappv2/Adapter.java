package com.example.admin.movieappv2;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    ArrayList<Movie> mylist;
    Context context;

    public Adapter(ArrayList<Movie> list, Context context) {
        this.context = context;
        mylist = list;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        // holder.imageView.setImageResource(R.drawable.trueimg);
        // holder.imageView.setImageURI(Uri.parse( "http://image.tmdb.org/t/p/w185/"+mylist.get(position).getImageUrl()));

        Picasso.with(context).load("http://image.tmdb.org/t/p/w185" + mylist.get(position).getImageUrl()).into(holder.imageView);


//          Picasso.with(context).load("http://image.tmdb.org/t/p/w185" + mylist.get(position+1).getImageUrl()).into(holder.imageView_2);
//          holder.TextView_name_2.setText(mylist.get(position).getName());


//        position++;
//

        // Log.v("tessssssst*** ", "http://image.tmdb.org/t/p/w185" + mylist.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }


    class Holder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {
        ImageView imageView;
        TextView TextView_name;

        View vi;

        public Holder(View view) {
            super(view);
            vi = view;
            view.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.FirstMovieImage);


//            imageView_2= (ImageView) itemView.findViewById(R.id.SecotMovieImage);
//            TextView_name_2= (TextView) itemView.findViewById(R.id.SecondtMovieName);
            //    imageView = itemView.findViewById(R.id.IV_poster);
            //  TextView_name = itemView.findViewById(R.id.TV_MovieName);
            // TextView_rate =itemView.findViewById(R.id.TV_MovieRateSec);
            //TextView_overview=itemView.findViewById(R.id.TV_MovieRateSec);
        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(v.getContext(), Main3Activity.class);
            int position = getAdapterPosition();
            // intent.putExtra("position", getAdapterPosition());


            intent.putExtra("position",position);
            intent.putExtra("obj",mylist.get(position));

//           intent.putExtras("List",mylist);
            v.getContext().startActivity(intent);

        }
    }

}



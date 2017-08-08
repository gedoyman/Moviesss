package com.littleaozora.hendra.moviessss;

/**
 * Created by Hendra on 7/28/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hendra on 5/21/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    private List<Movie> listItems;
    private Context context;

    public MyAdapter(List<Movie> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return  vh;
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        //holder.mTextView.setText(mDataset[position]);
        final Movie listItem = listItems.get(position);

        holder.mTextView.setText(listItem.getTitle());
        holder.mTextView2.setText(listItem.getOverview());

        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w300/"+listItem.getPoster_path())
                .into(holder.mImageView);

        holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "You clicked "+listItem.getHead(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("judul", listItem.getTitle());
                intent.putExtra("deskripsi", listItem.getOverview());
                intent.putExtra("image", listItem.getPoster_path());
                context.startActivity(intent);
            }
        });

    }

    @Override
    //public int getItemCount(){return mDataset.length;}
    public int getItemCount(){return listItems.size();}

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        public TextView mTextView;
        public TextView mTextView2;
        public ImageView mImageView;
        public RelativeLayout mRelativeLayout;
        public MyViewHolder(View v){
            super(v);

            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextView = (TextView) v.findViewById(R.id.tv_text1);
            mTextView2 = (TextView) v.findViewById(R.id.tv_text2);
            mImageView = (ImageView) v.findViewById(R.id.iv_image);
            mRelativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);
        }
    }
}

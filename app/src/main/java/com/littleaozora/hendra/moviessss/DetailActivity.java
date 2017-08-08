package com.littleaozora.hendra.moviessss;

/**
 * Created by Hendra on 7/28/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    public TextView mTextView;
    public TextView mTextView2;
    public ImageView mImageView;

    private Context context;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        String judul = getIntent().getStringExtra("judul");
        String deskripsi = getIntent().getStringExtra("deskripsi");
        String image = getIntent().getStringExtra("image");

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle(judul);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        mTextView = (TextView) findViewById(R.id.judul_detail);
        mTextView2 = (TextView) findViewById(R.id.deskripsi_detail);
        mImageView = (ImageView) findViewById(R.id.image_detail);

        //mTextView.setText(judul);
        mTextView2.setText(deskripsi);

        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w640/"+image)
                .into(mImageView);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

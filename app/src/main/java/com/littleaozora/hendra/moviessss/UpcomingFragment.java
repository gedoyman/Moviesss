package com.littleaozora.hendra.moviessss;

/**
 * Created by Hendra on 7/28/2017.
 */

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baoyz.widget.PullRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;


public class UpcomingFragment extends Fragment {
    private List<Movie> listItems;


    private static final String URL_DATA1 = "https://api.themoviedb.org/3/movie/upcoming?";
    private static final String URL_DATA2 = "api_key=522d8d0d0923444cda7b6094c2ba4886";
    private static final String URL_DATA3 = "&language=en-US&page=";
    public RecyclerView rv;


    private EndlessRecyclerViewScrollListener scrollListener;

    public UpcomingFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstnceState){
        super.onCreate(savedInstnceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_upcoming, container, false);


        rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);



        rv.setHasFixedSize(true);



        final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        loadRecyclerViewData();

        final int[] hal = {2};
        scrollListener = new EndlessRecyclerViewScrollListener(llm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                loadMoreRecyclerViewData(hal[0]);
                hal[0]++;
            }
        };

        rv.addOnScrollListener(scrollListener);

        listItems = new ArrayList<>();





        final PullRefreshLayout layout = (PullRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        // listen refresh event
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadRecyclerViewData();
                // refresh complete
                layout.setRefreshing(false);
            }
        });


        return rootView;
    }



    private void loadRecyclerViewData(){

        final ProgressDialog progressDialog = new ProgressDialog(this.getActivity());
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA1 + "" + URL_DATA2+ "" + URL_DATA3+"1", new Response.Listener<String>() {




            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("results");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject o = array.getJSONObject(i);
                        //ListItem item = new ListItem(o.getString("title"), o.getString("overview"), o.getString("poster_path"));
                        Movie movie = new Movie(
                                o.getInt("id"),
                                o.getString("poster_path"),
                                o.getString("adult"),
                                o.getString("overview"),
                                o.getString("release_date"),
                                o.getString("title"),
                                o.getString("backdrop_path"),
                                o.getString("popularity"),
                                o.getInt("vote_count"),
                                o.getString("vote_average"),
                                "Now Playing"
                        );

                        listItems.add(movie);


                    }
                    MyAdapter adapter = new MyAdapter(listItems, getContext());
                    rv.setAdapter(new AlphaInAnimationAdapter(adapter));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity());
        requestQueue.add(stringRequest);

    }

    private void loadMoreRecyclerViewData(int hal){
        final ProgressDialog progressDialog = new ProgressDialog(this.getActivity());
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA1+""+URL_DATA2+""+URL_DATA3+""+hal, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("results");

                    for(int i = 0; i<array.length(); i++){
                        JSONObject o = array.getJSONObject(i);
                        Movie movie = new Movie(
                                o.getInt("id"),
                                o.getString("poster_path"),
                                o.getString("adult"),
                                o.getString("overview"),
                                o.getString("release_date"),
                                o.getString("title"),
                                o.getString("backdrop_path"),
                                o.getString("popularity"),
                                o.getInt("vote_count"),
                                o.getString("vote_average"),
                                "Now Playing"
                        );


                        listItems.add(movie);
                    }
                    MyAdapter adapter = new MyAdapter(listItems, getContext());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity());
        requestQueue.add(stringRequest);
    }
}

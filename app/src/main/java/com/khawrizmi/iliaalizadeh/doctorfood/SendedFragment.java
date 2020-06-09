package com.khawrizmi.iliaalizadeh.doctorfood;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class SendedFragment extends Fragment {


    public SendedFragment() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;
    AdviceAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    List<FetchAdvice> array;

    SwipeRefreshLayout swipeRefreshLayout;
    EndlessRecyclerViewScrollListener scrollListener;
    SQLiteHandler db;
    TextView advtitle;
    RelativeLayout relativeLayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sended,container,false);
        db = new SQLiteHandler(getActivity().getApplicationContext());
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.sended_swipe);
        recyclerView = (RecyclerView) view.findViewById(R.id.sended_recyclerview);
        linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.VERTICAL, false);
        array = new ArrayList<>();
        adapter = new AdviceAdapter(getActivity().getBaseContext(), array);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        loadData(0);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadData(page);
            }
        };
        recyclerView.addOnScrollListener(scrollListener);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                array = new ArrayList<FetchAdvice>();
                loadData(0);
                scrollListener.resetState();
            }
        });
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity().getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                advtitle=view.findViewById(R.id.adv_title);
                relativeLayout=view.findViewById(R.id.ex_re_lay);
                relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(),dataActivity.class);
                        i.putExtra("data",array.get(position).data.toString());
                        i.putExtra("title",array.get(position).title.toString());
                        startActivity(i);
                    }
                });

                advtitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(),dataActivity.class);
                        i.putExtra("data",array.get(position).data.toString());
                        i.putExtra("title",array.get(position).title.toString());
                        startActivity(i);

                    }
                });

            }
        }));
        return view;
    }
    public void loadData(int page) {
        NetUtils.get("?data=advices&limit=10&page=" + (page+1) + "&drname=" + db.getUserDetails().get("name") , null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                JSONArray jarray = response;
                try {
                    for (int i = 0; i < jarray.length(); i++) {
                        array.add(new FetchAdvice(jarray.getJSONObject(i)));
                    }
                    adapter.update(array);
                    swipeRefreshLayout.setRefreshing(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                try {

                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }

        });
    }

}

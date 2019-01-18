package com.dicoding.paul.vertilogic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.dicoding.paul.vertilogic.adapter.UserAdapter;
import com.dicoding.paul.vertilogic.model.UserModel;
import com.dicoding.paul.vertilogic.presenter.UserPresenter;
import com.dicoding.paul.vertilogic.view.UserView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements UserView {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rv_main) RecyclerView recyclerView;
    @BindView(R.id.pb_main) ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Set toolbar and show progressBar
        setSupportActionBar(toolbar);
        progressBar.setVisibility(View.VISIBLE);

        //Create an object of UserPresenter, then send the data to userView
        final UserPresenter presenter = new UserPresenter(this);
        presenter.new StartLoader(this, getSupportLoaderManager());
    }

    @Override
    public void showUser(ArrayList<UserModel> data) {
        //Binding views
        ButterKnife.bind(this);

        //Create an object of Adapter, retrieve data, and set the data on adapter
        UserAdapter adapter = new UserAdapter(this);
        adapter.setUserModelArrayList(data);
        adapter.notifyDataSetChanged();

        //Set recyclerView and attach the adapter on it
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        //Clear progressBar
        progressBar.setVisibility(View.GONE);
    }
}

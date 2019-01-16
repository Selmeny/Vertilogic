package com.dicoding.paul.vertilogic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.dicoding.paul.vertilogic.adapter.UserAdapter;
import com.dicoding.paul.vertilogic.apiloader.UserAsyncLoader;
import com.dicoding.paul.vertilogic.model.UserModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<UserModel>> {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rv_main) RecyclerView recyclerView;
    @BindView(R.id.pb_main) ProgressBar progressBar;

    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        progressBar.setVisibility(View.VISIBLE);

        //Initiate the loader
        getSupportLoaderManager().initLoader(1, null, this);

        //Show the data through user interface
        showRecyclerview();
    }

    @NonNull
    @Override
    public Loader<ArrayList<UserModel>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new UserAsyncLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<UserModel>> loader, ArrayList<UserModel> data) {
        adapter.setUserModelArrayList(data);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<UserModel>> loader) {
        adapter.setUserModelArrayList(null);
        progressBar.setVisibility(View.GONE);
    }

    private void showRecyclerview() {
        ButterKnife.bind(this);
        adapter = new UserAdapter(this);
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }
}

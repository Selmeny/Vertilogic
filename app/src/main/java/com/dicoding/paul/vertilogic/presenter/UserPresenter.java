package com.dicoding.paul.vertilogic.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.dicoding.paul.vertilogic.apiloader.UserAsyncLoader;
import com.dicoding.paul.vertilogic.model.UserModel;
import com.dicoding.paul.vertilogic.view.UserView;

import java.util.ArrayList;

public class UserPresenter  {
    private UserView userView;

    public UserPresenter(UserView userView) {
        this.userView = userView;
    }

    //A sub-class to retrieve data and load it into arrayList
    public class StartLoader implements LoaderManager.LoaderCallbacks<ArrayList<UserModel>> {
        private Context context;

        //Need to pass loader manager as a parameter so we can call it outside activity.class
        public StartLoader(Context context, LoaderManager manager) {
            this.context = context;
            manager.initLoader(1, null, this);
        }

        @NonNull
        @Override
        public Loader<ArrayList<UserModel>> onCreateLoader(int i, @Nullable Bundle bundle) {
            //Initiate UserAsyncLoader
            return new UserAsyncLoader(context);
        }

        @Override
        public void onLoadFinished(@NonNull Loader<ArrayList<UserModel>> loader, ArrayList<UserModel> data) {
            //Set the data to UserView, which we will retrieve later
            userView.showUser(data);
        }

        @Override
        public void onLoaderReset(@NonNull Loader<ArrayList<UserModel>> loader) {
            userView.showUser(null);
        }
    }
}

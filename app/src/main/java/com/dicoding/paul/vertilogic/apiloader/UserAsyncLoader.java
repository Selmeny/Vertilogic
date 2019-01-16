package com.dicoding.paul.vertilogic.apiloader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.dicoding.paul.vertilogic.model.UserModel;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class UserAsyncLoader extends AsyncTaskLoader<ArrayList<UserModel>> {
    private ArrayList<UserModel> userModelArrayList;
    private boolean mHasResult;
    private static final String URL = "http://jsonplaceholder.typicode.com/users";
    private String TAG = UserAsyncLoader.class.getSimpleName();

    public UserAsyncLoader(@NonNull Context context) {
        super(context);
        onContentChanged();
    }

    //Check if userModelArrayList is empty or not
    @Override
    protected void onStartLoading() {
        if (takeContentChanged()) {
            forceLoad();
        } else if (mHasResult) {
            deliverResult(userModelArrayList);
        }
    }

    @Override
    public void deliverResult(final ArrayList<UserModel> data) {
        userModelArrayList = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult) {
            onReleaseResources(userModelArrayList);
            userModelArrayList = null;
            mHasResult = false;
        }
    }

    @Nullable
    @Override
    public ArrayList<UserModel> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<UserModel> arrayList = new ArrayList<>();
        client.get(URL, new AsyncHttpResponseHandler() {

            //Set SynchMode = true because we need all data completely loaded before set into adapter
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
                Log.d(TAG, "onStart is called");
            }

            //Parse result into Model then put all of it into arrayList
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONArray list = new JSONArray(result);

                    for (int i=0; i<list.length(); i++) {
                        JSONObject user = list.getJSONObject(i);
                        UserModel model = new UserModel(user);
                        arrayList.add(model);

                        Log.d(TAG, "User added: " + model.getName());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Loading failed");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e(TAG, "Loading failed");
            }
        });

        return arrayList;
    }

    private void onReleaseResources (ArrayList<UserModel> data) {
        userModelArrayList = data;
        userModelArrayList.clear();
        Log.d(TAG, "Arraylist has been cleared");
    }
}

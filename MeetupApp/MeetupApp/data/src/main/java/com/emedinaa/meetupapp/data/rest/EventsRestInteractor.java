package com.emedinaa.meetupapp.data.rest;

import android.util.Log;

import com.emedinaa.meetupapp.domain.callback.StorageCallback;
import com.emedinaa.meetupapp.domain.interactors.EventsInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by eduardomedina on 7/12/16.
 */
public class EventsRestInteractor implements EventsInteractor {

    private static final String TAG = "EventsRestInteractor";

    @Override
    public void events(String group_urlname, final StorageCallback storageCallback) {
        Call<Object> call= ApiClient.getMyApiClient().events(group_urlname);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if(response.isSuccessful()){

                    //notesSuccess(response.body());
                    Log.v(TAG, "response.body "+response.body());
                }else {

                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                String json="Error ";
                try {
                    json= new StringBuffer().append(t.getMessage()).toString();
                }catch (NullPointerException e) {}
                Log.v(TAG, "json >>>> " + json);

            }
        });
    }

    @Override
    public void pastEvents(String group_urlname, StorageCallback storageCallback) {
        Call<Object> call= ApiClient.getMyApiClient().pastEvents(group_urlname);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if(response.isSuccessful()){

                    //notesSuccess(response.body());
                    Log.v(TAG, "response.body "+response.body());
                }else {

                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                String json="Error ";
                try {
                    json= new StringBuffer().append(t.getMessage()).toString();
                }catch (NullPointerException e) {}
                Log.v(TAG, "json >>>> " + json);

            }
        });
    }
}

package com.emedinaa.meetupapp.domain.callback;

/**
 * Created by eduardo on 11/12/16.
 */
public interface StorageCallback {

    void onSuccess(Object object);
    void onFailure(Exception e);
}

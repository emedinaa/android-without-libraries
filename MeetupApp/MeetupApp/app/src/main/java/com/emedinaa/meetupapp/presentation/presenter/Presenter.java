package com.emedinaa.meetupapp.presentation.presenter;

/**
 * Created by emedinaa on 12/12/15.
 */
public interface Presenter<V> {

    void attachedView(V view);
    void detachView();
}

package com.emedinaa.meetupapp.presentation.presenter;

/**
 * Created by eduardo on 21/11/16.
 */
public abstract class BasePresenter<V> implements Presenter<V> {

    protected V view;

    @Override
    public void attachedView(V view) {
        this.view= view;
    }

    @Override
    public void detachView() {
        this.view= null;
    }
}

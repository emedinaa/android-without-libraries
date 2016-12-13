package com.emedinaa.meetupapp.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.emedinaa.meetupapp.R;
import com.emedinaa.meetupapp.common.media.ImageLoader;
import com.emedinaa.meetupapp.common.media.ImageLoaderHelper;
import com.emedinaa.meetupapp.domain.entity.Meetup;
import com.emedinaa.meetupapp.domain.entity.Member;
import com.emedinaa.meetupapp.domain.entity.Photo;
import com.pedrogomez.renderers.Renderer;

/**
 * Created by eduardo on 11/12/16.
 */
public class EventRenderer extends Renderer<Meetup> {

    private TextView tviName;
    private final ImageLoaderHelper imageLoader;

    public EventRenderer(ImageLoaderHelper imageLoader) {
        this.imageLoader = imageLoader;
    }

    @Override
    protected void setUpView(View rootView) {
        tviName= (TextView) rootView.findViewById(R.id.tviName);
    }

    @Override
    protected void hookListeners(View rootView) {

    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View inflatedView = inflater.inflate(R.layout.row_meetup, parent, false);
        //ButterKnife.inject(this, inflatedView);
        return inflatedView;
    }

    @Override
    public void render() {
        Meetup meetup = getContent();
        renderThumbnail(meetup);
        renderTitle(meetup);
        renderType(meetup);
    }

    private void renderType(Meetup meetup) {
    }

    private void renderTitle(Meetup meetup) {
        tviName.setText(meetup.getName());
    }

    private void renderThumbnail(Meetup meetup) {
    }
}

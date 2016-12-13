package com.emedinaa.meetupapp.presentation.adapter;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emedinaa.meetupapp.R;
import com.emedinaa.meetupapp.common.media.ImageLoaderHelper;
import com.emedinaa.meetupapp.domain.entity.Meetup;
import com.pedrogomez.renderers.Renderer;

/**
 * Created by eduardo on 11/12/16.
 */
public class EventRenderer extends Renderer<Meetup> {

    private static final String TAG ="EventRenderer" ;
    private TextView tviName,tviPlace,tviAddress,tviDesc;
    private TextView tviDate,tviUrl;
    private final ImageLoaderHelper imageLoader;

    public EventRenderer(ImageLoaderHelper imageLoader) {
        this.imageLoader = imageLoader;
    }

    @Override
    protected void setUpView(View rootView) {
        tviName= (TextView) rootView.findViewById(R.id.tviName);
        tviPlace= (TextView) rootView.findViewById(R.id.tviPlace);
        tviAddress= (TextView) rootView.findViewById(R.id.tviAddress);
        tviDesc= (TextView) rootView.findViewById(R.id.tviDesc);
        tviDate= (TextView) rootView.findViewById(R.id.tviDate);
        tviUrl= (TextView) rootView.findViewById(R.id.tviUrl);
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
        Log.v(TAG, "meetup "+meetup);
        renderThumbnail(meetup);
        renderTitle(meetup);
    }


    private void renderTitle(Meetup meetup) {
        String name= meetup.getName();
        String place= meetup.getVenue().getName();
        String address= meetup.getVenue().getAddress();
        String desc= meetup.getDescription();
        String url= meetup.getUrl();
        tviName.setText(name);
        tviPlace.setText(place);
        tviAddress.setText(address);
        tviDesc.setText(Html.fromHtml(proccessDesc(desc)));
        tviUrl.setText(url);
    }
    private String proccessDesc(String desc){
        if(desc==null) return "";
        if(desc.length()>160){
            return desc.substring(0,160)+"...";
        }
        return desc;
    }

    private void renderThumbnail(Meetup meetup) {
    }
}

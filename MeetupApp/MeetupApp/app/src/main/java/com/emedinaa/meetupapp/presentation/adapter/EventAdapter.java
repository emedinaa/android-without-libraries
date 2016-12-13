package com.emedinaa.meetupapp.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emedinaa.meetupapp.R;
import com.emedinaa.meetupapp.domain.entity.Meetup;

import java.util.List;

/**
 * Created by eduardomedina on 13/12/16.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private List<Meetup> meetups;

    public EventAdapter(List<Meetup> meetups) {
        this.meetups = meetups;
        //imageLoaderHelper= new ImageLoaderHelper(ImageLoaderHelper.GLIDE);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tviName,tviPlace,tviAddress,tviDesc;
        private TextView tviDate,tviUrl;

        public ViewHolder(View rootView) {
            super(rootView);
            tviName= (TextView) rootView.findViewById(R.id.tviName);
            tviPlace= (TextView) rootView.findViewById(R.id.tviPlace);
            tviAddress= (TextView) rootView.findViewById(R.id.tviAddress);
            tviDesc= (TextView) rootView.findViewById(R.id.tviDesc);
            tviDate= (TextView) rootView.findViewById(R.id.tviDate);
            tviUrl= (TextView) rootView.findViewById(R.id.tviUrl);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_meetup, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Meetup meetup = meetups.get(position);
        if (meetup != null) {
            String name= meetup.getName();
            String place= meetup.getVenue().getName();
            String address= meetup.getVenue().getAddress();
            String desc= meetup.getDescription();
            String url= meetup.getUrl();
            holder.tviName.setText(name);
            holder.tviPlace.setText(place);
            holder.tviAddress.setText(address);
            holder.tviDesc.setText(Html.fromHtml(desc));
            holder.tviUrl.setText(url);
        }
    }

    @Override
    public int getItemCount() {
        return meetups.size();
    }

}

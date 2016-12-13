package com.emedinaa.meetupapp.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.emedinaa.meetupapp.R;
import com.emedinaa.meetupapp.domain.entity.Member;

import java.util.List;

/**
 * Created by eduardomedina on 13/12/16.
 */
public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {

    private List<Member> members;


    public MemberAdapter(List<Member> members) {
        this.members = members;
        //imageLoaderHelper= new ImageLoaderHelper(ImageLoaderHelper.GLIDE);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView iviMember;
        private TextView tviName,tviType;

        public ViewHolder(View rootView) {
            super(rootView);
            iviMember= (ImageView) rootView.findViewById(R.id.iviMember);
            tviName= (TextView) rootView.findViewById(R.id.tviName);
            tviType= (TextView) rootView.findViewById(R.id.tviType);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_member, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Member member = members.get(position);
        if (member != null) {
            String name= member.getName();
            String status= member.getStatus();
            String type = "Miembro";
            if(status.equals("active")){
                type= "Miembro";
            }
            holder.tviName.setText(name);
            holder.tviType.setText(type);
        }
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

}

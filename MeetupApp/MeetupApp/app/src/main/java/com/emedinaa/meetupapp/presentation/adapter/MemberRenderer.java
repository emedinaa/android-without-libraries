package com.emedinaa.meetupapp.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.emedinaa.meetupapp.R;
import com.emedinaa.meetupapp.common.media.ImageLoader;
import com.emedinaa.meetupapp.domain.entity.Member;
import com.emedinaa.meetupapp.domain.entity.Photo;
import com.pedrogomez.renderers.Renderer;

/**
 * Created by eduardo on 11/12/16.
 */
public class MemberRenderer extends Renderer<Member> {

    private ImageView iviMember;
    private TextView tviName,tviType;
    private final ImageLoader imageLoader;

    public MemberRenderer(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    @Override
    protected void setUpView(View rootView) {
        iviMember= (ImageView) rootView.findViewById(R.id.iviMember);
        tviName= (TextView) rootView.findViewById(R.id.tviName);
        tviType= (TextView) rootView.findViewById(R.id.tviType);
    }

    @Override
    protected void hookListeners(View rootView) {}

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View inflatedView = inflater.inflate(R.layout.row_member, parent, false);
        //ButterKnife.inject(this, inflatedView);
        return inflatedView;
    }

    @Override
    public void render() {
        Member member = getContent();
        renderThumbnail(member);
        renderTitle(member);
        renderType(member);
    }

    private void renderType(Member member) {
        String status= member.getStatus();
        String type = "Miembro";
        if(status.equals("active")){
            type= "Miembro";
        }
        tviType.setText(type);
    }

    private void renderTitle(Member member) {
        tviName.setText(member.getName());
    }

    private void renderThumbnail(Member member) {
        Photo photo= member.getPhoto();
        String url= photo.getThumb_link();
        imageLoader.loadCircle(url,iviMember);
    }
}

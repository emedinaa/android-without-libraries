package com.emedinaa.meetupapp.presentation.view;

import com.emedinaa.meetupapp.domain.entity.Meetup;
import com.emedinaa.meetupapp.domain.entity.Member;

import java.util.List;

/**
 * Created by eduardo on 12/12/16.
 */
public interface EventView extends BaseView{

    void renderMeetups(List<Meetup> meetups);
    void emptyMeetups();
    void showMessage(String message);
}

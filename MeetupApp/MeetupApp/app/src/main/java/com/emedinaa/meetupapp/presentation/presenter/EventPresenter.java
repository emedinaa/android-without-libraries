package com.emedinaa.meetupapp.presentation.presenter;

import com.emedinaa.meetupapp.domain.callback.StorageCallback;
import com.emedinaa.meetupapp.domain.entity.Meetup;
import com.emedinaa.meetupapp.domain.interactors.EventsInteractor;
import com.emedinaa.meetupapp.presentation.view.EventView;

import java.util.List;

/**
 * Created by eduardo on 12/12/16.
 */
public class EventPresenter extends BasePresenter<EventView> {


    private final EventsInteractor eventsInteractor;

    public EventPresenter(EventsInteractor eventsInteractor) {
        this.eventsInteractor = eventsInteractor;
    }

    public void getPastEvents(String groupName){
        view.showLoading();
        this.eventsInteractor.pastEvents(groupName,restCallback);
    }
    public void getUpcomingEvent(String groupName){
        view.showLoading();
        this.eventsInteractor.events(groupName,restCallback);
    }

    private StorageCallback restCallback= new StorageCallback() {
        @Override
        public void onSuccess(Object object) {
            List<Meetup> meetups= (List<Meetup>)(object);
            if(meetups==null || meetups.size()==0){
                view.emptyMeetups();
            }else {
                view.renderMeetups(meetups);
            }
            view.hideLoading();
        }

        @Override
        public void onFailure(Exception e) {
            String message= e.getMessage();
            view.showMessage(message);
            view.hideLoading();
        }
    };

}

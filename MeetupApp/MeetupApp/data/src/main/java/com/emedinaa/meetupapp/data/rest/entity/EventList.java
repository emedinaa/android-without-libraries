package com.emedinaa.meetupapp.data.rest.entity;

import com.emedinaa.meetupapp.data.entity.MeetupEntity;
import com.emedinaa.meetupapp.domain.entity.Meetup;

import java.util.List;

public class EventList extends BaseResponse {
    public List<MeetupEntity> results;

    public List<MeetupEntity> getResults() {
        return results;
    }

    public void setResults(List<MeetupEntity> results) {
        this.results = results;
    }
}

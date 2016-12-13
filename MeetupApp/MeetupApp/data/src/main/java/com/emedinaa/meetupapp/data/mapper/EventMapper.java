package com.emedinaa.meetupapp.data.mapper;

import com.emedinaa.meetupapp.data.entity.GroupEntity;
import com.emedinaa.meetupapp.data.entity.MeetupEntity;
import com.emedinaa.meetupapp.domain.entity.Group;
import com.emedinaa.meetupapp.domain.entity.Meetup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eduardo on 11/12/16.
 */
public class EventMapper {

    public List<Meetup> transformList(List<MeetupEntity> meetupEntities){
        List<Meetup> meetups= new ArrayList<>();
        if(meetupEntities==null) return meetups;
        for (MeetupEntity meetupEntity:meetupEntities) {
            Meetup meetup= transform(meetupEntity);
            meetups.add(meetup);
        }
        return  meetups;
    }

    public Meetup transform(MeetupEntity meetupEntity){

        Meetup meetup= new Meetup();
        if(meetupEntity==null) return meetup;
        meetup.setId(meetupEntity.getId());
        meetup.setName(meetupEntity.getName());
        meetup.setStatus(meetupEntity.getStatus());
        meetup.setTime(meetupEntity.getTime());
        meetup.setWaitlist_count(meetupEntity.getWaitlist_count());
        meetup.setYes_rsvp_coun(meetupEntity.getYes_rsvp_coun());
        meetup.setLink(meetupEntity.getLink());
        meetup.setDescription(meetupEntity.getDescription());
        meetup.setVisibility(meetupEntity.getVisibility());
        meetup.setGroup(transformGroup(meetupEntity.getGroup()));

        return  meetup;
    }

    private Group transformGroup(GroupEntity groupEntity){
        Group group = new Group();
        if(groupEntity==null)return group;
        group.setName(groupEntity.getName());
        group.setCreated(groupEntity.getCreated());
        group.setId(groupEntity.getId());
        group.setJoin_mode(groupEntity.getJoin_mode());
        group.setLat(groupEntity.getLat());
        group.setLon(groupEntity.getLon());
        group.setUrlname(groupEntity.getUrlname());
        group.setWho(groupEntity.getWho());

        return group;
    }

}
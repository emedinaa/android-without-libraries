package com.emedinaa.meetupapp.domain.interactors;

import com.emedinaa.meetupapp.domain.callback.StorageCallback;

/**
 * Created by eduardomedina on 7/12/16.
 */
public interface EventsInteractor {

    void events(String group_urlname, StorageCallback callback);
    void pastEvents(String group_urlname, StorageCallback callback);
}

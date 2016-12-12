package com.emedinaa.meetupapp.domain.interactors;

import com.emedinaa.meetupapp.domain.callback.StorageCallback;

/**
 * Created by eduardo on 11/12/16.
 */
public interface MembersInteractor {

    void membersByGroup(String group_urlname, StorageCallback callback);
}

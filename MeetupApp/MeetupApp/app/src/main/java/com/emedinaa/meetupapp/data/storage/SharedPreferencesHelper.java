package com.emedinaa.meetupapp.data.storage;


import com.emedinaa.meetupapp.model.entity.User;

/**
 * Created by eduardomedina on 15/11/16.
 */
public interface SharedPreferencesHelper {

    void saveEmail(String email);
    String email();

    void saveUser(User user);
    User user();

    void clear();
}

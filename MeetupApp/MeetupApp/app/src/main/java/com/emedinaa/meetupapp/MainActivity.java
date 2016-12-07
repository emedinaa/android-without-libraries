package com.emedinaa.meetupapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.emedinaa.meetupapp.data.rest.EventsRestInteractor;
import com.emedinaa.meetupapp.domain.EventsInteractor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testService();
    }

    private void testService() {
        EventsInteractor eventsInteractor= new EventsRestInteractor();
        eventsInteractor.events("Android-Dev-Peru");
    }
}

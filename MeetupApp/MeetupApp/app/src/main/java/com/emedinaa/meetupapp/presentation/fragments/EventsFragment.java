package com.emedinaa.meetupapp.presentation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.emedinaa.meetupapp.R;
import com.emedinaa.meetupapp.common.media.ImageLoaderHelper;
import com.emedinaa.meetupapp.common.ui.MarginDecoration;
import com.emedinaa.meetupapp.data.mapper.EventMapper;
import com.emedinaa.meetupapp.data.rest.EventsRestInteractor;
import com.emedinaa.meetupapp.domain.entity.Meetup;
import com.emedinaa.meetupapp.presentation.adapter.EventAdapter;
import com.emedinaa.meetupapp.presentation.adapter.EventRenderer;
import com.emedinaa.meetupapp.presentation.presenter.EventPresenter;
import com.emedinaa.meetupapp.presentation.view.EventView;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {} interface
 * to handle interaction events.
 * Use the {@link EventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsFragment extends Fragment implements EventView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final String GROUP="Android-Dev-Peru";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentListener mListener;
    private RecyclerView rviEvents;
    private ProgressBar pBar;
    private List<Meetup> meetups;
    private EventPresenter eventPresenter;
    private String eventType=null;

    public EventsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventsFragment newInstance(String param1, String param2) {
        EventsFragment fragment = new EventsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

            eventType= getArguments().getString("EVENTS");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_events, container, false);
        rviEvents= (RecyclerView) view.findViewById(R.id.rviEvents);
        pBar=(ProgressBar)view.findViewById(R.id.pBar);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rviEvents.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rviEvents.setLayoutManager(mLayoutManager);
        int margin= getResources().getDimensionPixelSize(R.dimen.row_margin);
        rviEvents.addItemDecoration(new MarginDecoration(margin));

        EventMapper eventMapper= new EventMapper();
        eventPresenter= new EventPresenter(new EventsRestInteractor(eventMapper));
        eventPresenter.attachedView(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        if(eventType!=null){
            if(eventType.equals("upcoming")){
                eventPresenter.getUpcomingEvent(GROUP);
            }else if(eventType.equals("past")){
                eventPresenter.getPastEvents(GROUP);
            }
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentListener) {
            mListener = (OnFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void renderMeetups(List<Meetup> meetups) {
        this.meetups= meetups;

        Renderer<Meetup> renderer = new EventRenderer(new ImageLoaderHelper(ImageLoaderHelper.PICASSO));
        RendererBuilder<Meetup> rendererBuilder = new RendererBuilder<Meetup>(renderer);
        RVRendererAdapter<Meetup> rendererAdapter= new RVRendererAdapter<Meetup>(rendererBuilder,this.meetups);
        rviEvents.setAdapter(rendererAdapter);

        //renderMeetupsAdapter(meetups);
    }

    private void renderMeetupsAdapter(List<Meetup> meetups){
        rviEvents.setAdapter(new EventAdapter(meetups));
    }

    @Override
    public void emptyMeetups() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showLoading() {
        pBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pBar.setVisibility(View.GONE);
    }
}

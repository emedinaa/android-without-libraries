package com.emedinaa.meetupapp.presentation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.emedinaa.meetupapp.R;
import com.emedinaa.meetupapp.common.media.ImageLoaderHelper;
import com.emedinaa.meetupapp.common.ui.MarginDecoration;
import com.emedinaa.meetupapp.data.mapper.MemberMapper;
import com.emedinaa.meetupapp.data.rest.MembersRestInteractor;
import com.emedinaa.meetupapp.domain.entity.Member;
import com.emedinaa.meetupapp.presentation.adapter.EndlessRecyclerViewScrollListener;
import com.emedinaa.meetupapp.presentation.adapter.MemberRenderer;
import com.emedinaa.meetupapp.presentation.presenter.MemberPresenter;
import com.emedinaa.meetupapp.presentation.view.MemberView;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {} interface
 * to handle interaction events.
 * Use the {@link MembersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MembersFragment extends Fragment implements MemberView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "MembersFragment";
    private final String GROUP="Android-Dev-Peru";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int offset=0;

    private OnFragmentListener mListener;
    private RecyclerView rviMembers;
    private SwipeRefreshLayout swMembers;
    private List<Member> members;
    private ProgressBar pBar;

    private MemberPresenter memberPresenter;
    private RendererBuilder<Member> rendererBuilder;
    private RVRendererAdapter<Member> rendererAdapter=null;

    private EndlessRecyclerViewScrollListener scrollListener;

    public MembersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MembersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MembersFragment newInstance(String param1, String param2) {
        MembersFragment fragment = new MembersFragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_members, container, false);
        rviMembers= (RecyclerView) view.findViewById(R.id.rviMembers);
        pBar=(ProgressBar)view.findViewById(R.id.pBar);
        swMembers=(SwipeRefreshLayout) view.findViewById(R.id.swMembers);

        Renderer<Member> renderer = new MemberRenderer(new ImageLoaderHelper(ImageLoaderHelper.PICASSO));
        rendererBuilder= new RendererBuilder<Member>(renderer);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rviMembers.setHasFixedSize(true);

        // use a linear layout manager
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        scrollListener= new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.v(TAG, "page "+page);
                loadNextDataFromApi();
            }
        };

        rviMembers.setLayoutManager(mLayoutManager);
        int margin= getResources().getDimensionPixelSize(R.dimen.row_margin);
        rviMembers.addItemDecoration(new MarginDecoration(margin));
        rviMembers.addOnScrollListener(scrollListener);

        swMembers.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });

        MemberMapper memberMapper= new MemberMapper();
        memberPresenter= new MemberPresenter(new MembersRestInteractor(memberMapper));
        memberPresenter.attachedView(this);
    }

    private void refreshContent() {
        offset=0;
        if(members!=null) this.members.clear();
        if(rendererAdapter!=null) rendererAdapter.notifyDataSetChanged();
        scrollListener.resetState();
        swMembers.setRefreshing(false);
        getMembers();
    }

    public void loadNextDataFromApi() {
        memberPresenter.getMembers(offset,GROUP);
    }

    @Override
    public void onResume() {
        super.onResume();
        getMembers();
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

    public void getMembers() {
        //memberPresenter.getMembers(GROUP);
        memberPresenter.getMembers(offset,GROUP);
    }

    @Override
    public void renderMembers(List<Member> members) {
        if(rendererAdapter==null){
            this.members= members;
            rendererAdapter= new RVRendererAdapter<Member>(rendererBuilder,members);
            rviMembers.setAdapter(rendererAdapter);
        }else{
            this.members.addAll(members);
            //rendererAdapter= new RVRendererAdapter<Member>(rendererBuilder,members);
            //rviMembers.setAdapter(rendererAdapter);
            rendererAdapter.notifyDataSetChanged();
        }
        offset++;

    }

    @Override
    public void emptyMembers() {

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

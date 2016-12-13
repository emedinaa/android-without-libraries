package com.emedinaa.meetupapp.presentation.presenter;

import com.emedinaa.meetupapp.domain.callback.StorageCallback;
import com.emedinaa.meetupapp.domain.entity.Member;
import com.emedinaa.meetupapp.domain.interactors.MembersInteractor;
import com.emedinaa.meetupapp.presentation.view.MemberView;

import java.util.List;

/**
 * Created by eduardo on 12/12/16.
 */
public class MemberPresenter extends BasePresenter<MemberView> {

    private final MembersInteractor membersInteractor;

    public MemberPresenter(MembersInteractor membersInteractor) {
        this.membersInteractor = membersInteractor;
    }

    public void getMembers(String groupName){
        view.showLoading();
        this.membersInteractor.membersByGroup(groupName,restCallback);
    }


    private StorageCallback restCallback= new StorageCallback() {
        @Override
        public void onSuccess(Object object) {
            view.renderMembers((List<Member>)(object));
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

package com.emedinaa.meetupapp.presentation.view;

import com.emedinaa.meetupapp.domain.entity.Member;

import java.util.List;

/**
 * Created by eduardo on 12/12/16.
 */
public interface MemberView  extends BaseView{
    void renderMembers(List<Member> members);
    void emptyMembers();
    void showMessage(String message);
}

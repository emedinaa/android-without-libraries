package com.emedinaa.meetupapp.data.rest;

import android.util.Log;

import com.emedinaa.meetupapp.data.mapper.MemberMapper;
import com.emedinaa.meetupapp.data.rest.entity.MemberResponse;
import com.emedinaa.meetupapp.domain.callback.StorageCallback;
import com.emedinaa.meetupapp.domain.interactors.MembersInteractor;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by eduardo on 11/12/16.
 */
public class MembersRestInteractor implements MembersInteractor {

    private static final String TAG = "MembersRestI";
    private final  MemberMapper memberMapper;

    public MembersRestInteractor(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public void membersByGroup(String group_urlname, final StorageCallback storageCallback) {
        Map<String, String> options = new HashMap<>();
        options.put("key","3d1540104e112155653a2f775452a1f");
        //options.put("key","");
        options.put("sign","true");
        options.put("photo-host","public");
        options.put("group_urlname", group_urlname);
        options.put("page", "200");

        Call<MemberResponse> call= ApiClient.getMyApiClient().membersByGroup(options);
        call.enqueue(new Callback<MemberResponse>() {
            @Override
            public void onResponse(Call<MemberResponse> call, Response<MemberResponse> response) {
                if(response.isSuccessful()){
                    MemberResponse memberResponse= response.body();
                    if(memberResponse!=null){
                        Log.v(TAG, "response.body "+response.body());
                        storageCallback.onSuccess(memberMapper.transformList(memberResponse.getResults()));
                    }else {
                        Exception exception= new Exception("Error");
                        storageCallback.onFailure(exception);
                    }

                }else {

                    Exception exception=null;
                    try{
                        JSONObject jsonError = new JSONObject(response.errorBody().string());
                        String code= jsonError.getString("code")+ " "+jsonError.getString("problem");
                        exception= new Exception(code);
                    }catch (Exception e){
                        exception= new Exception(e);
                    }

                    Log.v(TAG, "response.errorBody "+exception.getMessage());
                    storageCallback.onFailure(exception);
                }
            }

            @Override
            public void onFailure(Call<MemberResponse> call, Throwable t) {
                String message="Error ";
                try {
                    message= new StringBuffer().append(t.getMessage()).toString();
                }catch (NullPointerException e) {}
                Log.v(TAG, "onFailure >>>> " + message);
                Exception exception= new Exception(message);
                storageCallback.onFailure(exception);
            }
        });
    }
}
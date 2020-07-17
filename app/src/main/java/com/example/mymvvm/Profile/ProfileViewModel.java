package com.example.mymvvm.Profile;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mymvvm.model.LoginModel;
import com.example.mymvvm.model.ProfileModel;
import com.example.mymvvm.retrofitConfing.HandelCalls;
import com.example.mymvvm.retrofitConfing.HandleRetrofitResp;
import com.example.mymvvm.utility.DataEnum;

import java.util.HashMap;

public class ProfileViewModel extends ViewModel implements HandleRetrofitResp {


    public MutableLiveData<ProfileModel> profileModelMutableLiveData=new MutableLiveData<>();


        public void CallprofilenApi(Context context) {

        HandelCalls.getInstance(context).call(DataEnum.profile.name(), null, true, this);

    }




    @Override
    public void onResponseSuccess(String flag, Object o) {
        if (flag.equals(DataEnum.profile.name())){

            ProfileModel profileModel=(ProfileModel) o;
            profileModelMutableLiveData.setValue(profileModel);
        }
    }

    @Override
    public void onResponseFailure(String flag, String o) {

    }

    @Override
    public void onNoContent(String flag, int code) {

    }
}

package com.example.mymvvm.Profile;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mymvvm.model.LoginModel;
import com.example.mymvvm.model.LogoutModel;
import com.example.mymvvm.model.ProfileModel;
import com.example.mymvvm.retrofitConfing.HandelCalls;
import com.example.mymvvm.retrofitConfing.HandleRetrofitResp;
import com.example.mymvvm.utility.DataEnum;

import java.util.HashMap;

public class ProfileViewModel extends ViewModel implements HandleRetrofitResp {


    public MutableLiveData<ProfileModel> profileModelMutableLiveData=new MutableLiveData<>();
    public MutableLiveData<LogoutModel> logoutModelMutableLiveData=new MutableLiveData<>();
    public MutableLiveData<ProfileModel> mutableLiveData=new MutableLiveData<>();


        public void CallprofilenApi(Context context) {

        HandelCalls.getInstance(context).call(DataEnum.profile.name(), null, true, this);

    }
    public void CallLogOutnApi(Context context,HashMap<String,String>map) {

        HandelCalls.getInstance(context).call(DataEnum.logout.name(), map, true, this);

    }
    public void CallUpdateProdileApi(Context context,HashMap<String,String>map) {

        HandelCalls.getInstance(context).call(DataEnum.updateProfile.name(), map, true, this);

    }




    @Override
    public void onResponseSuccess(String flag, Object o) {
        if (flag.equals(DataEnum.profile.name())){

            ProfileModel profileModel=(ProfileModel) o;
            profileModelMutableLiveData.setValue(profileModel);
        }else if(flag.equals(DataEnum.logout.name())){

            LogoutModel logoutModel=(LogoutModel)o;
            logoutModelMutableLiveData.setValue(logoutModel);
        }else if(flag.equals(DataEnum.updateProfile.name())){
            ProfileModel profileModel=(ProfileModel) o;
            mutableLiveData.setValue(profileModel);
        }
    }

    @Override
    public void onResponseFailure(String flag, String o) {

    }

    @Override
    public void onNoContent(String flag, int code) {

    }
}

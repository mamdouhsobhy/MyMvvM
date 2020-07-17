package com.example.mymvvm.login;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mymvvm.model.LoginModel;
import com.example.mymvvm.retrofitConfing.HandelCalls;
import com.example.mymvvm.retrofitConfing.HandleRetrofitResp;
import com.example.mymvvm.utility.DataEnum;

import java.util.HashMap;

public class LoginViewModel extends ViewModel implements HandleRetrofitResp {

    public MutableLiveData<LoginModel> loginModelMutableLiveData=new MutableLiveData<>();


    public void CallloginApi(Context context, HashMap<String,String> hashMap) {

        HandelCalls.getInstance(context).call(DataEnum.login.name(), hashMap, true, this);

    }





    @Override
    public void onResponseSuccess(String flag, Object o) {

        if (flag.equals(DataEnum.login.name())){

            LoginModel loginModel=(LoginModel)o;
            loginModelMutableLiveData.setValue(loginModel);
        }

    }

    @Override
    public void onResponseFailure(String flag, String o) {

    }

    @Override
    public void onNoContent(String flag, int code) {

    }
}

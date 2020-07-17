package com.example.mymvvm.register;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mymvvm.model.RegisterModel;
import com.example.mymvvm.retrofitConfing.HandelCalls;
import com.example.mymvvm.retrofitConfing.HandleRetrofitResp;
import com.example.mymvvm.utility.DataEnum;

import java.util.HashMap;

public class RegisterViewModel extends ViewModel implements HandleRetrofitResp {


 public  MutableLiveData<RegisterModel>registerModelMutableLiveData=new MutableLiveData<>();

    public void CallregisterApi(Context context, HashMap<String,String> hashMap) {

        HandelCalls.getInstance(context).call(DataEnum.register.name(), hashMap, true, this);



    }


    @Override
    public void onResponseSuccess(String flag, Object o) {

        if (flag.equals(DataEnum.register.name())) {
            RegisterModel registerModel = (RegisterModel) o;
            registerModelMutableLiveData.setValue(registerModel);
        }

    }

    @Override
    public void onResponseFailure(String flag, String o) {

    }

    @Override
    public void onNoContent(String flag, int code) {

    }
}

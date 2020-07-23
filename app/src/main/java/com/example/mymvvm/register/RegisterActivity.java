package com.example.mymvvm.register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mymvvm.R;
import com.example.mymvvm.login.LoginActivity;
import com.example.mymvvm.model.RegisterModel;
import com.example.mymvvm.updateProfile.UpdateProfileActivity;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {


    @BindView(R.id.name_Register)
    EditText nameRegister;
    @BindView(R.id.phone_Register)
    EditText phoneRegister;
    @BindView(R.id.email_Register)
    EditText emailRegister;
    @BindView(R.id.pass_Register)
    EditText passRegister;
    @BindView(R.id.btn_Register)
    Button btnRegister;
    RegisterViewModel registerViewModel;
    String mName, mPhone, mEmail, mPass;
    @BindView(R.id.btnlogin)
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        registerViewModel.registerModelMutableLiveData.observe(this, new Observer<RegisterModel>() {
            @Override
            public void onChanged(RegisterModel registerModel) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("token", registerModel.getData().getToken());
                editor.apply();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    @OnClick(R.id.btn_Register)
    public void onViewClicked() {
        mName = nameRegister.getText().toString();
        mPhone = phoneRegister.getText().toString();
        mEmail = emailRegister.getText().toString();
        mPass = passRegister.getText().toString();
        HashMap<String, String> meMap = new HashMap<>();
        meMap.put("name", mName);
        meMap.put("phone", mPhone);
        meMap.put("email", mEmail);
        meMap.put("password", mPass);
        registerViewModel.CallregisterApi(RegisterActivity.this, meMap);

    }

}

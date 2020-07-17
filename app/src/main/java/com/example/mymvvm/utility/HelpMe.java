package com.example.mymvvm.utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


/**
 * Created by Mina Shaker on 27-Mar-18.
 */

public class HelpMe {

    // static Uri.Builder builder;

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    @SuppressLint("StaticFieldLeak")
    private static HelpMe instance = null;


//  public   Socket mSocket;

    public static HelpMe getInstance(Context context) {


        HelpMe.context = context;

        if (instance == null) {
            instance = new HelpMe();
        }
        return instance;
    }







    //======================================================//






    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void retrofironFailure(Throwable t) {


        if (t instanceof IOException) {

            Toast.makeText(context, "Check your Internet", Toast.LENGTH_SHORT).show();
        } else {
            //   TastyToast.makeText(context, t.getMessage(), TastyToast.LENGTH_LONG, TastyToast.ERROR);
            Log.e("errrr", Objects.requireNonNull(t.getMessage()));
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getDate(String dateValue) {

        String currentDate = dateValue;
        String[] separated = currentDate.split("T");
        currentDate =separated[0];

        return currentDate;

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getTimeStamp(String dateValue) {

        @SuppressLint("SimpleDateFormat") DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = formatter.parse(dateValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Today is " + Objects.requireNonNull(date).getTime());

        return date.getTime()+"";

    }

}

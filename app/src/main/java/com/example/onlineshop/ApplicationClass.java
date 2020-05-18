package com.example.onlineshop;

import android.app.Application;

import com.example.onlineshop.model.User;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationClass extends Application {

    public static final Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl("http://192.168.100.10:8080/")
            .baseUrl("https://online-shop-ec.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static User currentlyLoggedUser;
}

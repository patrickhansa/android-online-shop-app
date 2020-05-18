package com.example.onlineshop.controllers;

import com.example.onlineshop.json.UserJsonApi;
import com.example.onlineshop.model.User;
import com.example.onlineshop.views.LoginActivityViewImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.onlineshop.ApplicationClass.currentlyLoggedUser;
import static com.example.onlineshop.ApplicationClass.retrofit;


public class LoginController {

    private final UserJsonApi userJsonApi;
    private final LoginActivityViewImpl loginActivityView;

    public LoginController(LoginActivityViewImpl loginActivityView) {
        this.userJsonApi = retrofit.create(UserJsonApi.class);
        this.loginActivityView = loginActivityView;
    }

    public void onLoginButtonClicked(String username, String password) {
        Call<User> call = userJsonApi.login(username, password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    loginActivityView.showToast(response.code() + "");
                    return;
                }

                User user = response.body();
                assert user != null;
                if (user.getUsername() != null) {
                    currentlyLoggedUser = user;
                    loginActivityView.navigateToMainActivity();
                } else {
                    loginActivityView.showToast("Incorrect username or password!");
                    loginActivityView.showProgress(false, loginActivityView.mMainFormView,
                            loginActivityView.mProgressView, loginActivityView.tvLoad);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                loginActivityView.showToast(t.getMessage());
                loginActivityView.showProgress(false, loginActivityView.mMainFormView,
                        loginActivityView.mProgressView, loginActivityView.tvLoad);
            }
        });
    }
}

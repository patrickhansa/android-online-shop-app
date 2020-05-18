package com.example.onlineshop.controllers;
import com.example.onlineshop.json.UserJsonApi;
import com.example.onlineshop.model.User;
import com.example.onlineshop.views.RegisterActivityViewImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.onlineshop.ApplicationClass.retrofit;

public class RegisterController {

    private final UserJsonApi userJsonApi;
    private final RegisterActivityViewImpl registerActivityView;

    public RegisterController(RegisterActivityViewImpl registerActivityView) {
        this.userJsonApi = retrofit.create(UserJsonApi.class);
        this.registerActivityView = registerActivityView;
    }

    public void onRegisterButtonClicked(String username, String password) {
        User user = new User(username, password);
        Call<User> call = userJsonApi.createUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    registerActivityView.showToast(response.code() + "");
                    return;
                }

                registerActivityView.navigateToLoginActivity();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                registerActivityView.showToast(t.getMessage());
                registerActivityView.showProgress(false, registerActivityView.mMainFormView,
                        registerActivityView.mProgressView, registerActivityView.tvLoad);
            }
        });
    }
}

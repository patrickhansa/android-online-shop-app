package com.example.onlineshop.controllers;

import android.content.SharedPreferences;

import com.example.onlineshop.json.UserJsonApi;
import com.example.onlineshop.model.User;
import com.example.onlineshop.views.AccountInfoActivityViewImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.example.onlineshop.ApplicationClass.currentlyLoggedUser;
import static com.example.onlineshop.ApplicationClass.retrofit;

public class AccountInfoController implements BaseController {

    private final UserJsonApi userJsonApi;
    private final AccountInfoActivityViewImpl accountInfoActivityView;

    public AccountInfoController(AccountInfoActivityViewImpl accountInfoActivityView) {
        this.userJsonApi = retrofit.create(UserJsonApi.class);
        this.accountInfoActivityView = accountInfoActivityView;
    }

    @Override
    public void onViewLoaded() {
        Call<User> call = userJsonApi.getUser(currentlyLoggedUser.getUsername());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    accountInfoActivityView.showToast(response.code() + "");
                    return;
                }

                assert response.body() != null;
                accountInfoActivityView.updateViewOnLoad(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                accountInfoActivityView.showToast(t.getMessage());
            }
        });
    }


    public void onDeleteButtonClicked() {
        Call<Void> call = userJsonApi.deleteUser(currentlyLoggedUser.getId().toString());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    accountInfoActivityView.showToast(response.code() + "");
                    return;
                }

                SharedPreferences preferences =
                        accountInfoActivityView.getRootView().getContext()
                                .getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();

                accountInfoActivityView.navigateToLoginActivity();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                accountInfoActivityView.showToast(t.getMessage());
            }
        });
    }
}

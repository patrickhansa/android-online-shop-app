package com.example.onlineshop.controllers;

import com.example.onlineshop.ApplicationClass;
import com.example.onlineshop.json.UserJsonApi;
import com.example.onlineshop.model.User;
import com.example.onlineshop.views.EditAccountActivityViewImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.onlineshop.ApplicationClass.currentlyLoggedUser;
import static com.example.onlineshop.ApplicationClass.retrofit;

public class EditAccountController implements BaseController {

    private final UserJsonApi userJsonApi;
    private final EditAccountActivityViewImpl editAccountActivityView;

    public EditAccountController(EditAccountActivityViewImpl editAccountActivityView) {
        this.userJsonApi = retrofit.create(UserJsonApi.class);
        this.editAccountActivityView = editAccountActivityView;
    }

    @Override
    public void onViewLoaded() {
        Call<User> call =
                userJsonApi.getUser(ApplicationClass.currentlyLoggedUser.getUsername());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    editAccountActivityView.showToast(response.code() + "");
                    return;
                }

                assert response.body() != null;
                editAccountActivityView.updateViewOnLoad(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                editAccountActivityView.showToast(t.getMessage());
            }
        });
    }

    public void onSubmitButtonClicked(
            String firstName, String lastName, String email, String phone,
            String password, String birthDate, String gender, String address
    ) {

        User user = new User(
                currentlyLoggedUser.getId(), currentlyLoggedUser.getUsername(), password,
                email, firstName, lastName, phone, gender, birthDate, address
        );

        Call<User> call = userJsonApi.updateUser(currentlyLoggedUser.getId().toString(), user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    editAccountActivityView.showToast(response.code() + "");
                    return;
                }

                editAccountActivityView.navigateToAccountInfoActivity();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                editAccountActivityView.showToast(t.getMessage());
            }
        });
    }
}

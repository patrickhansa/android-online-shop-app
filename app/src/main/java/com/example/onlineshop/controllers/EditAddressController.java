package com.example.onlineshop.controllers;

import com.example.onlineshop.ApplicationClass;
import com.example.onlineshop.json.UserJsonApi;
import com.example.onlineshop.model.User;
import com.example.onlineshop.views.EditAddressActivityViewImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.onlineshop.ApplicationClass.currentlyLoggedUser;
import static com.example.onlineshop.ApplicationClass.retrofit;

public class EditAddressController implements BaseController {

    private final UserJsonApi userJsonApi;
    private final EditAddressActivityViewImpl editAddressActivityViewImpl;

    public EditAddressController(EditAddressActivityViewImpl editAddressActivityViewImpl) {
        this.userJsonApi = retrofit.create(UserJsonApi.class);
        this.editAddressActivityViewImpl = editAddressActivityViewImpl;
    }

    @Override
    public void onViewLoaded() {
        Call<User> call =
                userJsonApi.getUser(ApplicationClass.currentlyLoggedUser.getUsername());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    editAddressActivityViewImpl.showToast(response.code() + "");
                    return;
                }

                assert response.body() != null;
                editAddressActivityViewImpl.updateViewOnLoad(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                editAddressActivityViewImpl.showToast(t.getMessage());
            }
        });
    }

    public void onSubmitButtonClicked(
            String firstName, String lastName, String email, String phone, String address
    ) {

        User user = new User(
                currentlyLoggedUser.getId(), currentlyLoggedUser.getUsername(),
                currentlyLoggedUser.getPassword(), email, firstName, lastName, phone, address
        );

        Call<User> call = userJsonApi.updateUser(currentlyLoggedUser.getId().toString(), user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    editAddressActivityViewImpl.showToast(response.code() + "");
                    return;
                }

                editAddressActivityViewImpl.navigateToCheckoutActivity();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                editAddressActivityViewImpl.showToast(t.getMessage());
            }
        });
    }
}

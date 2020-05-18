package com.example.onlineshop.controllers;

import com.example.onlineshop.json.ProductJsonApi;
import com.example.onlineshop.json.UserJsonApi;
import com.example.onlineshop.model.User;
import com.example.onlineshop.views.CheckoutActivityViewImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.onlineshop.ApplicationClass.currentlyLoggedUser;
import static com.example.onlineshop.ApplicationClass.retrofit;

public class CheckoutController implements BaseController {

    private final ProductJsonApi productJsonApi;
    private final UserJsonApi userJsonApi;
    private final CheckoutActivityViewImpl checkoutActivityViewImpl;

    public CheckoutController(CheckoutActivityViewImpl checkoutActivityViewImpl) {
        this.productJsonApi = retrofit.create(ProductJsonApi.class);
        this.userJsonApi = retrofit.create(UserJsonApi.class);
        this.checkoutActivityViewImpl = checkoutActivityViewImpl;
    }

    @Override
    public void onViewLoaded() {
        Call<User> call = userJsonApi.getUser(currentlyLoggedUser.getUsername());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    checkoutActivityViewImpl.showToast(response.code() + "");
                    return;
                }

                assert response.body() != null;
                User user = response.body();

                Call<Float> priceCall = productJsonApi.getCartTotal(currentlyLoggedUser.getUsername());

                priceCall.enqueue(new Callback<Float>() {
                    @Override
                    public void onResponse(Call<Float> call, Response<Float> response) {
                        if (!response.isSuccessful()) {
                            checkoutActivityViewImpl.showToast(response.code() + "");
                            return;
                        }

                        assert response.body() != null;
                        Float totalPrice = response.body();
                        checkoutActivityViewImpl.updateViewOnLoad(user, totalPrice);
                    }

                    @Override
                    public void onFailure(Call<Float> call, Throwable t) {
                        checkoutActivityViewImpl.showToast(t.getMessage());
                    }
                });
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                checkoutActivityViewImpl.showToast(t.getMessage());
            }
        });
    }
}

package com.example.onlineshop.controllers;

import com.example.onlineshop.json.ProductJsonApi;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.views.ProductInfoActivityViewImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.onlineshop.ApplicationClass.currentlyLoggedUser;
import static com.example.onlineshop.ApplicationClass.retrofit;

public class ProductInfoController implements BaseController {

    private final ProductJsonApi productJsonApi;
    private final ProductInfoActivityViewImpl productInfoActivityView;

    public ProductInfoController(ProductInfoActivityViewImpl productInfoActivityView) {
        this.productJsonApi = retrofit.create(ProductJsonApi.class);
        this.productInfoActivityView = productInfoActivityView;
    }

    @Override
    public void onViewLoaded() {

    }

    public void onViewLoaded(long id) {
        Call<Product> call = productJsonApi.getProduct(Long.toString(id));

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (!response.isSuccessful()) {
                    productInfoActivityView.showToast(response.code() + "");
                    return;
                }

                assert response.body() != null;
                productInfoActivityView.updateViewOnLoad(response.body());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                productInfoActivityView.showToast(t.getMessage());
            }
        });
    }

    public void onAddToCartClicked(long productId) {
        Call<Void> call = productJsonApi.
                addProductToShoppingCart(currentlyLoggedUser.getUsername(), "" + productId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    productInfoActivityView.showToast(response.code() + "");
                    return;
                }

                assert response.body() != null;
                productInfoActivityView.navigateToShoppingCartActivity();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                productInfoActivityView.showToast(t.getMessage());
            }
        });
    }
}

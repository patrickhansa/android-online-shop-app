package com.example.onlineshop.controllers;

import com.example.onlineshop.ApplicationClass;
import com.example.onlineshop.json.ProductJsonApi;
import com.example.onlineshop.model.ProductList;
import com.example.onlineshop.views.ShoppingCartActivityViewImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.onlineshop.ApplicationClass.retrofit;

public class ShoppingCartController implements BaseController {

    ProductJsonApi productJsonApi;
    ShoppingCartActivityViewImpl shoppingCartActivityView;

    public ShoppingCartController(ShoppingCartActivityViewImpl shoppingCartActivityView) {
        productJsonApi = retrofit.create(ProductJsonApi.class);
        this.shoppingCartActivityView = shoppingCartActivityView;
    }

    @Override
    public void onViewLoaded() {
        Call<ProductList> call = productJsonApi.getProductsInShoppingCart(ApplicationClass.currentlyLoggedUser.getUsername());

        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                if (!response.isSuccessful()) {
                    shoppingCartActivityView.showToast(response.code() + "");
                    return;
                }

                ProductList productList = response.body();

                assert productList != null;
                shoppingCartActivityView.showProductsInShoppingCart(productList.getProducts());
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                shoppingCartActivityView.showToast(t.getMessage());
            }
        });
    }

    public void removeProductFromCart(Long productId) {
        Call<Void> call = productJsonApi.removeProductFromCart(
                ApplicationClass.currentlyLoggedUser.getUsername(), Long.toString(productId));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    shoppingCartActivityView.showToast(response.code() + "");
                    return;
                }

                shoppingCartActivityView.navigateToShoppingCartActivity();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                shoppingCartActivityView.showToast(t.getMessage());
            }
        });
    }
}

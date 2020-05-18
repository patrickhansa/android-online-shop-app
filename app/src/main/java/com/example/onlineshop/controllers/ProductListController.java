package com.example.onlineshop.controllers;

import com.example.onlineshop.json.ProductJsonApi;
import com.example.onlineshop.model.ProductList;
import com.example.onlineshop.views.ProductListActivityViewImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.example.onlineshop.ApplicationClass.retrofit;

public class ProductListController implements BaseController {

    private final ProductJsonApi productJsonApi;
    private final ProductListActivityViewImpl productListActivityView;

    public ProductListController(ProductListActivityViewImpl productListActivityView) {
        this.productJsonApi = retrofit.create(ProductJsonApi.class);
        this.productListActivityView = productListActivityView;
    }

    @Override
    public void onViewLoaded() {
        Call<ProductList> call = productJsonApi.getAllProducts();

        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                if (!response.isSuccessful()) {
                    productListActivityView.showToast(response.code() + "");
                    return;
                }

                ProductList productList = response.body();

                assert productList != null;
                productListActivityView.showAllProducts(productList.getProducts());
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                productListActivityView.showToast(t.getMessage());
            }
        });
    }
}

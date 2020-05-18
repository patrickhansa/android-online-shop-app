package com.example.onlineshop.json;


import com.example.onlineshop.model.Product;
import com.example.onlineshop.model.ProductList;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductJsonApi {

    @GET("api/product/showAll")
    Call<ProductList> getAllProducts();

    @GET("api/product/{productId}/show")
    Call<Product> getProduct(@Path(value = "productId", encoded = true) String productId);

    @GET("api/shoppingCart/{username}")
    Call<ProductList> getProductsInShoppingCart(@Path(value = "username", encoded = true) String username);

    @GET("api/shoppingCart/{username}/cartTotal")
    Call<Float> getCartTotal(@Path(value = "username", encoded = true) String username);

    @PUT("api/shoppingCart/{username}/{productId}")
    Call<Void> addProductToShoppingCart(
            @Path(value = "username", encoded = true) String username,
            @Path(value = "productId", encoded = true) String productId
    );

    @DELETE("api/shoppingCart/{username}/{productId}")
    Call<Void> removeProductFromCart(
            @Path(value = "username", encoded = true) String username,
            @Path(value = "productId", encoded = true) String productId
    );
}

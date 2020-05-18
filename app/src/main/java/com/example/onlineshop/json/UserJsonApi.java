package com.example.onlineshop.json;

import com.example.onlineshop.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserJsonApi {

    @GET("api/user/{username}")
    Call<User> getUser(@Path("username") String username);

    @POST("api/user")
    Call<User> createUser(@Body User user);

    @PUT("api/user/{id}")
    Call<User> updateUser(@Path(value = "id", encoded = true) String id, @Body User user);

    @GET("api/user/{id}/delete")
    Call<Void> deleteUser(@Path(value = "id", encoded = true) String id);

    @POST("api/user/login/{username}/{password}")
    Call<User> login(
            @Path(value = "username", encoded = true) String username,
            @Path(value = "password", encoded = true) String password
    );

}

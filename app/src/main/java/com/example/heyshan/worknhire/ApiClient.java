package com.example.heyshan.worknhire;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ApiClient {
    @GET("api/client/{email}")

    Call<UserModel> login(@Path(value = "email", encoded = true) String email);
}

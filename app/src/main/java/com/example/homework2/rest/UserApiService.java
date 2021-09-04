package com.example.homework2.rest;

import com.example.homework2.model.Posts;
import com.example.homework2.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApiService {

    @GET("/users")
    Call<List<User>> getUsers();

    @GET("/posts")
    Call<List<Posts>> getPosts();
}

package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.homework2.model.Posts;
import com.example.homework2.model.User;
import com.example.homework2.rest.UserApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Intent myIntent = getIntent();
    TextView tvList;

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;

    public int id  = LoginActivity.id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectAndGetApiData(id);


    }

    private void connectAndGetApiData(final int id) {

        tvList = findViewById(R.id.tvList);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        UserApiService userApiService = retrofit.create(UserApiService.class);
        Call<List<Posts>> call = userApiService.getPosts();
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                List<Posts> posts = response.body();
                String postList = "";

                for(Posts p: posts){

                    if(p.getUserId() == id){

                        postList = postList + "userId: " + p.getUserId() + "\n";
                        postList = postList + "Title: " + p.getTitle() + "\n";
                        postList = postList + "Body: " + p.getBody() + "\n";
                        postList = postList + "\n";

                    }
                }

                tvList.setText(postList);
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

                Log.e(TAG, t.toString());

            }
        });
    }

}
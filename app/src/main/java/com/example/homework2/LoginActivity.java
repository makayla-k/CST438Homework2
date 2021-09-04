package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homework2.model.User;
import com.example.homework2.rest.UserApiService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button btnLogin;
    TextView tvList;

    private String user;
    private String pass;

    private static final String TAG = LoginActivity.class.getSimpleName();
    public static List<Pair<String, String>> userList;
    private Pair<String,String> pair1 = Pair.create("Bret","password");
    private Pair<String,String> pair2 = Pair.create("Antonette","password");
    private Pair<String,String> pair3 = Pair.create("Samantha","password");
    private Pair<String,String> pair4 = Pair.create("Karianne","password");
    private Pair<String,String> pair5 = Pair.create("Kamren","password");
    private Pair<String,String> pair6 = Pair.create("Leopoldo_Corkery","password");
    private Pair<String,String> pair7 = Pair.create("Elwyn.Skiles","password");
    private Pair<String,String> pair8 = Pair.create("Maxime_Nienow","password");
    private Pair<String,String> pair9 = Pair.create("Delphine","password");
    private Pair<String,String> pair10 = Pair.create("Moriah.Stanton","password");

    public static int id;

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static Retrofit retrofit = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userList = new ArrayList<>(Arrays.asList(pair1, pair2, pair3, pair4, pair5, pair6, pair7, pair8, pair9, pair10));

        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

//        When a user clicks login, check credentials
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Convert edit texts to a string
                user = username.getText().toString();
                pass = password.getText().toString();

                connectAndGetApiData(user);

            }
        });



    };

    private void connectAndGetApiData(final String user) {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }


        UserApiService userApiService = retrofit.create(UserApiService.class);
        Call<List<User>> call = userApiService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();

                boolean username = false;
                boolean userpass = false;
                userList = new ArrayList<>(Arrays.asList(pair1, pair2, pair3, pair4, pair5, pair6, pair7, pair8, pair9, pair10));


                for(User u : users){

                    if(u.getUsername().equals(user)){
                        username = true;
                    }
                }
//                Iterate through userList and compare credentials
                for(Pair i: userList){

//                    If user & pass match start the main activity. Also passed the index which can also be the userId. So we can access the correct users posts.
                    if(i.first.equals(user) && i.second.equals(pass)){
                        userpass = true;
                        id = userList.indexOf(i) + 1;
                    }
                }
//if correct credentials new intent
                if(username && userpass){

                    Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);

                    startActivity(myIntent);

                    Log.d(TAG, "Number of users received: " + users.size());

                }
//if wrong username
                if(!username){
                    Toast toast = Toast.makeText(getApplicationContext(),"Incorrect Username", Toast.LENGTH_SHORT);
                    toast.show();
                }
//if wrong password
                if(!userpass){
                    Toast toast = Toast.makeText(getApplicationContext(),"Incorrect Password", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                Log.e(TAG, t.toString());

            }
        });

    }

}
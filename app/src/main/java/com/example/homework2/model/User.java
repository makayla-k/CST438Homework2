package com.example.homework2.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class User {

    @SerializedName("id")
    private int id;

    @SerializedName("username")
    private String username;

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }

    private static final List<User> users = new ArrayList<>();

    public static List<User> getAllUsernames(){

        users.add(new User(1,"Bret"));
        users.add(new User(2,"Antonette"));
        users.add(new User(3,"Samantha"));
        users.add(new User(4,"Karianne"));
        users.add(new User(5,"Kamren"));
        users.add(new User(6,"Leopoldo_Corkery"));
        users.add(new User(7,"Elwyn.Skiles"));
        users.add(new User(8,"Maxime_Nienow"));
        users.add(new User(9,"Delphine"));
        users.add(new User(10,"Moriah.Stanton"));

        return users;
    }

    public static User compareUsernames(String user, List<User> users){

        for(User u: users){

            if(u.getUsername().equals(user)){
                return u;
            }

        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

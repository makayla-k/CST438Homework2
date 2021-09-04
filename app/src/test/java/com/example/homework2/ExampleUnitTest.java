package com.example.homework2;

import android.util.Pair;

import com.example.homework2.model.User;

import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void checkUsername() {
        Random rand = new Random();
        List<User> users = User.getAllUsernames();
        int index = rand.nextInt(users.size());
        String username = users.get(index).getUsername();

        assertTrue(LoginActivity.verifyUsername(username, users));
        assertFalse(LoginActivity.verifyUsername(username, null));
    }
}
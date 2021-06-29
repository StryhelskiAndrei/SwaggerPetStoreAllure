package com.itspartner.petstore.test;

import com.itspartner.petstore.Order;
import com.itspartner.petstore.User;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.itspartner.petstore.test.Constants.Headers.CONTENT_TYPE_JSON;
import static com.itspartner.petstore.test.Constants.ResponseCodes.*;
import static com.itspartner.petstore.test.Constants.Urls.USER_URL;

public class UserTest extends PetStoreTest {

    @Test
    public void createUserWithList() throws IOException {
        User user = User.createPositiveUser();
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        String jsnObj = gson.toJson(users);
        RequestBody body = RequestBody.create(jsnObj, CONTENT_TYPE_JSON);
        Request request = new Request.Builder()
                .url(USER_URL + "createWithList")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void getUserByUserName() throws IOException {
        User user = User.createPositiveUser();
        sendUser(user);
        Request request = new Request.Builder()
                .url(USER_URL + user.getUsername())
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void getNonExistentUserByUsername() throws IOException {
        String nonExistentUsername = User.getRandomName();
        Request request = new Request.Builder()
                .url(USER_URL + nonExistentUsername)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), FAILURE);
    }

    @Test
    public void updateUser() throws IOException {
        User user = User.createPositiveUser();
        sendUser(user);
        User updatedUser = User.createPositiveUser(user.getUsername());
        String jsnObj = gson.toJson(updatedUser);
        RequestBody body =  RequestBody.create(jsnObj, CONTENT_TYPE_JSON);
        Request request = new Request.Builder()
                .url(USER_URL + user.getUsername())
                .put(body)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void deleteUser() throws IOException {
        User user = User.createPositiveUser();
        sendUser(user);
        Request request = new Request.Builder()
                .url(USER_URL + user.getUsername())
                .delete()
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void deleteNonExistentUser() throws IOException {
        Request request = new Request.Builder()
                .url(USER_URL + null)
                .delete()
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), FAILURE);
    }

    @Test
    public void login() throws IOException {
        User user = User.createPositiveUser();
        String username = user.getUsername();
        String password = user.getPassword();
        String loginUrl = USER_URL + "login?username=" + username + "&password=" + password;
        Request request = new Request.Builder()
                .url(loginUrl)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void loginWithoutPassword() throws IOException {
        String login = Order.getRandomName();
        String loginUrl = USER_URL + "login?username=" + login;
        Request request = new Request.Builder()
                .url(loginUrl)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), ERROR_400);
    }

    @Test
    public void loginWithoutUsername() throws IOException {
        String password = Order.getRandomPassword();
        String loginUrl = USER_URL + "login?&password=" + password;
        Request request = new Request.Builder()
                .url(loginUrl)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), ERROR_400);
    }

    @Test
    public void loginWithoutCredentials() throws IOException {
        String loginUrl = USER_URL + "login?";
        Request request = new Request.Builder()
                .url(loginUrl)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), ERROR_400);
    }

    @Test
    public void logout() throws IOException {
        Request request = new Request.Builder()
                .url(USER_URL + "logout")
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void createUserWithArray() throws IOException {
        User user = User.createPositiveUser();
        User[] users = new User[]{user};
        String jsnObj = gson.toJson(users);
        RequestBody body = RequestBody.create(jsnObj, CONTENT_TYPE_JSON);
        Request request = new Request.Builder()
                .url(USER_URL + "createWithArray")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void createUser() throws IOException {
        User user = User.createPositiveUser();
        Response response = sendUser(user);
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void createEmptyUser() throws IOException {
        User user = new User.Builder()
                .build();
        String jsnObj = gson.toJson(user);
        RequestBody body = RequestBody.create(jsnObj, CONTENT_TYPE_JSON);
        Request request = new Request.Builder()
                .url(USER_URL)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void createNullUser() throws IOException {
        String jsnObj = gson.toJson(null);
        RequestBody body = RequestBody.create(jsnObj, CONTENT_TYPE_JSON);
        Request request = new Request.Builder()
                .url(USER_URL)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), ERROR_405);
    }
}
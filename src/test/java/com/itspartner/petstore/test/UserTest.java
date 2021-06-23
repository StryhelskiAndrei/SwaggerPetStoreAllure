package com.itspartner.petstore.test;

import com.itspartner.petstore.User;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.itspartner.petstore.test.Constants.Headers.CONTENT_TYPE_JSON;
import static com.itspartner.petstore.test.Constants.ResponseCodes.SUCCESS;
import static com.itspartner.petstore.test.Constants.Urls.USER_URL;

public class UserTest extends PetStoreTest {


    @Test
    public void createUserWithList() throws IOException {
        User user = new User.Builder()
                .setId(11)
                .setUsername("user1")
                .build();
        ArrayList<User> users = new ArrayList<User>();
        users.add(user);

        String jsnObj = gson.toJson(users);

        RequestBody body = RequestBody.create(CONTENT_TYPE_JSON, jsnObj);
        Request request = new Request.Builder()
                .url(USER_URL + "createWithList")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void getUserByUserName() throws IOException {
        Request request = new Request.Builder()
                .url(USER_URL + "user1")
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(),SUCCESS);
    }

    @Test
    public void updateUser() throws IOException {
        final int userId = 444;
        final String userName= "user444";
        final String updatedName = "user4";
        createUser(userId, userName, "user", "444", "user@user", "123", "911",1);
        User user = new User.Builder()
                .setId(userId)
                .setUsername(updatedName)
                .setPhone("222")
                .build();

        String jsnObj = gson.toJson(user);
        RequestBody body = RequestBody.create(CONTENT_TYPE_JSON, jsnObj);
        Request request = new Request.Builder()
                .url(USER_URL + userName)
                .put(body)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test()
    public void deleteUser() throws IOException {
        final String userName = "testUser";
        createUser(331, userName, "user", "444", "user@user", "123", "911",1);
        Request request = new Request.Builder()
                .url(USER_URL + userName)
                .delete()
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void login() throws IOException {
        String login = "TestUser";
        String password = "pass123";
        String loginUrl = "https://petstore.swagger.io/v2/user/login?username=" + login + "&password=" + password;
        Request request = new Request.Builder()
                .url(loginUrl)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void loginWithoutPassword() throws IOException {
        String login = "TestUser";
        String loginUrl = "https://petstore.swagger.io/v2/user/login?username=" + login;
        Request request = new Request.Builder()
                .url(loginUrl)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void loginWithoutUsername() throws IOException {
        String password = "pass123";
        String loginUrl = "https://petstore.swagger.io/v2/user/login?&password=" + password;
        Request request = new Request.Builder()
                .url(loginUrl)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void loginWithoutCredentials() throws IOException {
        String loginUrl = "https://petstore.swagger.io/v2/user/login?";
        Request request = new Request.Builder()
                .url(loginUrl)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), 400);
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
        User user = new User.Builder()
                .setId(11)
                .setUsername("user1")
                .build();
        User[] users = new User[] {user};
        String jsnObj = gson.toJson(users);

        RequestBody body = RequestBody.create(CONTENT_TYPE_JSON, jsnObj);
        Request request = new Request.Builder()
                .url(USER_URL + "createWithArray")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void createUser() throws IOException {
        User user = new User.Builder()
                .setId(77)
                .setUsername("user1")
                .build();

        String jsnObj = gson.toJson(user);

        RequestBody body = RequestBody.create(CONTENT_TYPE_JSON, jsnObj);
        Request request = new Request.Builder()
                .url(USER_URL)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void createEmptyUser() throws IOException {
        User user = new User.Builder()
                .build();
        String jsnObj = gson.toJson(user);
        RequestBody body = RequestBody.create(CONTENT_TYPE_JSON, jsnObj);
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
        RequestBody body = RequestBody.create(CONTENT_TYPE_JSON, jsnObj);
        Request request = new Request.Builder()
                .url(USER_URL)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), 405);
    }
}
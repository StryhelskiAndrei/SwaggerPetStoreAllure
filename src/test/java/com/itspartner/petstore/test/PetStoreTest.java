package com.itspartner.petstore.test;

import com.google.gson.Gson;
import com.itspartner.petstore.Order;
import com.itspartner.petstore.Pet;
import com.itspartner.petstore.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

import static com.itspartner.petstore.test.Constants.Headers.CONTENT_TYPE_JSON;
import static com.itspartner.petstore.test.Constants.Urls.*;

public class PetStoreTest {

    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();

    public Response createPet(Pet pet) throws IOException {
        String jsnObj = gson.toJson(pet);
        RequestBody body = RequestBody.create(jsnObj, CONTENT_TYPE_JSON);
        Request request = new Request.Builder()
                .url(PET_URL)
                .post(body)
                .build();

        return client.newCall(request).execute();
    }

    public Response sendOrder(Order order) throws IOException {
        String jsnObj = gson.toJson(order);
        RequestBody body = RequestBody.create(jsnObj, CONTENT_TYPE_JSON);
        Request request = new Request.Builder()
                .url(ORDER_URL)
                .post(body)
                .build();
        return client.newCall(request).execute();
    }

    public Response sendUser(User user) throws IOException {
        String jsnObj = gson.toJson(user);
        RequestBody body = RequestBody.create(jsnObj, CONTENT_TYPE_JSON);
        Request request = new Request.Builder()
                .url(USER_URL)
                .post(body)
                .build();
        return client.newCall(request).execute();
    }
}

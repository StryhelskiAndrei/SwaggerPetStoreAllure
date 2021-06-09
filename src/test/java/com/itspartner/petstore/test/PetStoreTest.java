package com.itspartner.petstore.test;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class PetStoreTest {
    OkHttpClient client = new OkHttpClient();



    public Response getPetById(int testPetId) throws IOException {

        Request request = new Request.Builder()
                .url(Constants.Urls.GetPetById.formatted(testPetId))
                .build();

        return client.newCall(request).execute();
    }
}

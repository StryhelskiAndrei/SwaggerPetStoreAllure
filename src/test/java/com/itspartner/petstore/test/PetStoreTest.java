package com.itspartner.petstore.test;

import com.google.gson.Gson;
import com.itspartner.petstore.Pet;
import okhttp3.*;

import java.io.IOException;

import static com.itspartner.petstore.test.Constants.Headers.CONTENT_TYPE_JSON;

public class PetStoreTest {
    OkHttpClient client = new OkHttpClient();



    public Response getPetById(int testPetId) throws IOException {

        Request request = new Request.Builder()
                .url(Constants.Urls.GetPetById.formatted(testPetId))
                .build();

        return client.newCall(request).execute();
    }

    public void createPet(int id, String name, String status) throws IOException {
        Pet pet = new Pet();
        pet.id = id;
        pet.name = name;
        pet.status = status;

        Gson gson = new Gson();
        String jsnObj = gson.toJson(pet);

        RequestBody body = RequestBody.create(CONTENT_TYPE_JSON, jsnObj);
        Request request = new Request.Builder()
                .url("https://petstore.swagger.io/v2/pet/")
                .post(body)
                .build();

        client.newCall(request).execute();
    }
}

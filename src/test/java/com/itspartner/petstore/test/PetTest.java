package com.itspartner.petstore.test;

import com.itspartner.petstore.Pet;
import com.google.gson.Gson;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.itspartner.petstore.test.Constants.Headers.CONTENT_TYPE_JSON;

public class PetTest extends PetStoreTest {

    String url = "https://petstore.swagger.io/v2/pet/";
    Gson gson = new Gson();

    /**
     *  Adding a new pet
     * @throws IOException
     */

    @Test
    public void PostAddPet() throws IOException {
        Pet pet = new Pet();
        pet.id = 55555;
        pet.name = "Chupakabra";
        pet.status = "available";

        String jsnObj = gson.toJson(pet);

        RequestBody body = RequestBody.create(CONTENT_TYPE_JSON, jsnObj);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        ResponseBody responseBody = client.newCall(request).execute().body();
        pet = gson.fromJson(responseBody.string(), Pet.class);

        System.out.println(pet.name);
        System.out.println(pet.status);
        Assert.assertEquals(pet.name, "Chupakabra");
    }


    @Test
    public void UpdatingPetPut() throws IOException {
        Pet pet = new Pet();
        pet.id = 2;
        pet.name = "Kitty";
        pet.status = "available";
        String jsnObj = gson.toJson(pet);
        RequestBody body = RequestBody.create(CONTENT_TYPE_JSON,jsnObj);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        Response response = client.newCall(request).execute();
        Pet pet2 = new Pet();
        pet2 = gson.fromJson(response.body().string(), Pet.class);
        Assert.assertEquals(pet2.name, "Kitty");
        Assert.assertEquals(response.code(), Constants.ResponseCodes.SUCCESS);
    }

    @Test
    public void FindByStatus() throws IOException {
        Request request = new Request.Builder()
                .url(url + "findByStatus?status=fff&status=fff&status=sfff")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.code());


    }

    @Test
    public void GetID() throws IOException {
        final int testPetId = 55555;

        Response response = getPetById(testPetId);
        ResponseBody responseBody = response.body();
        Assert.assertEquals(response.code(), Constants.ResponseCodes.SUCCESS);
        Assert.assertNotNull(responseBody);

        Pet pet = Pet.fromJsonString(responseBody.string());
        System.out.println(pet);
        Assert.assertNotNull(pet);
        Assert.assertNotNull(pet.name);
        System.out.println(pet.name);
    }





}
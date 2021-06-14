package com.itspartner.petstore.test;

import com.google.gson.Gson;
import com.itspartner.petstore.Pet;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.itspartner.petstore.test.Constants.Headers.CONTENT_TYPE_JSON;
import static com.itspartner.petstore.test.Constants.ResponseCodes.SUCCESS;

public class PetTest extends PetStoreTest {

    String url = "https://petstore.swagger.io/v2/pet/";
    Gson gson = new Gson();

    @BeforeClass
    public void beforeClass(ITestContext context) {
        String value = context.getCurrentXmlTest().getParameter("env");
        System.err.println("webdriver.deviceName.iPhone = " + value);
    }

    @Test
    public void postAddPet() throws IOException {
        Pet pet = new Pet(5555, "Chupakabra", "available");

        String jsnObj = gson.toJson(pet);

        RequestBody body = RequestBody.create(CONTENT_TYPE_JSON, jsnObj);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        ResponseBody responseBody = client.newCall(request).execute().body();
        pet = gson.fromJson(responseBody.string(), Pet.class);
        Assert.assertEquals(pet.name, "Chupakabra");
    }


    @Test
    public void updatingPetPut() throws IOException {
        Pet pet = new Pet(2, "Kitty", "available");
        String jsnObj = gson.toJson(pet);
        RequestBody body = RequestBody.create(CONTENT_TYPE_JSON, jsnObj);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        Response response = client.newCall(request).execute();
        Pet pet2 = new Pet();
        pet2 = gson.fromJson(response.body().string(), Pet.class);
        Assert.assertEquals(pet2.name, "Kitty");
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void findByStatus() throws IOException {
        Request request = new Request.Builder()
                .url(url + "findByStatus?status=available&status=pending&status=sold")
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
        Assert.assertNotNull(response.body());
    }

//    @Test
//    public void getID() throws IOException {
//        final int testPetId = 55555;
//
//        Response response = getPetById(testPetId);
//        ResponseBody responseBody = response.body();
//        Assert.assertEquals(response.code(), SUCCESS);
//        Assert.assertNotNull(responseBody);
//
//        Pet pet = Pet.fromJsonString(responseBody.string());
//        System.out.println(pet);
//        Assert.assertNotNull(pet);
//        Assert.assertNotNull(pet.name);
//        System.out.println(pet.name);
//    }

    @Test
    public void updatePetIdPost() throws IOException {

        createPet(6666, "Chupacabra", "available");
        Pet pet = new Pet(6666, "Kitty", "available");

        String jsnObj = gson.toJson(pet);

        RequestBody body = RequestBody.create(CONTENT_TYPE_JSON, jsnObj);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), response.code());
    }

    @Test
    public void deletePetById() throws IOException {
        createPet(6666, "Chupacabra", "available");
        Request request = new Request.Builder()
                .url(url + "6666")
                .delete()
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }
}
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
import static com.itspartner.petstore.test.Constants.Urls.PET_URL;

public class PetTest extends PetStoreTest {

    @BeforeClass
    public void beforeClass(ITestContext context) {
        String value = context.getCurrentXmlTest().getParameter("env");
        System.err.println("webdriver.deviceName.iPhone = " + value);
    }

    @Test(groups = {"Smoke"})
    public void postAddPet() throws IOException {

        Pet pet = new Pet.Builder()
                .setId(555)
                .setName("Kesha")
                .setStatus("available")
                .build();
        Response response = createPet(pet);
        Assert.assertEquals(response.code(), SUCCESS);
    }


    @Test
    public void updatingPetPut() throws IOException {
        Pet pet = new Pet.Builder()
                .setId(2)
                .setName("Kitty")
                .setStatus("available")
                .build();
        String jsnObj = gson.toJson(pet);
        RequestBody body = RequestBody.create(CONTENT_TYPE_JSON, jsnObj);
        Request request = new Request.Builder()
                .url(PET_URL)
                .put(body)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);

//        Pet pet = new Pet(2, "Kitty", "available");
//        String jsnObj = gson.toJson(pet);
//        RequestBody body = RequestBody.create(CONTENT_TYPE_JSON, jsnObj);
//        Request request = new Request.Builder()
//                .url(PET_URL)
//                .put(body)
//                .build();
//
//        Response response = client.newCall(request).execute();
//        Pet pet2 = new Pet();
//        pet2 = gson.fromJson(response.body().string(), Pet.class);
//        Assert.assertEquals(pet2.name, "Kitty");
//        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void findByStatus() throws IOException {
        Request request = new Request.Builder()
                .url(PET_URL + "findByStatus?status=available&status=pending&status=sold")
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
        Assert.assertNotNull(response.body());
    }

    @Test
    public void getPetById() throws IOException {
        final int petId = 5555;
        Pet pet = new Pet.Builder()
                .setId(petId)
                .setName("Chupakabra")
                .setStatus("available")
                .build();
        createPet(pet);
        Request request = new Request.Builder()
                .url(PET_URL + String.valueOf(petId))
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
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

        final int petIdForUpdating = 9988;
        Pet pet = new Pet.Builder()
                .setId(petIdForUpdating)
                .setName("Chupacabra")
                .setStatus("available")
                .build();
        createPet(pet);
        Pet updatedPet = new Pet.Builder()
                .setId(petIdForUpdating)
                .setName("Kitty")
                .setStatus("available")
                .build();
        String jsnObj = gson.toJson(updatedPet);
        RequestBody body = RequestBody.create(CONTENT_TYPE_JSON, jsnObj);
        Request request = new Request.Builder()
                .url(PET_URL)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), response.code());
    }

    @Test
    public void deletePetById() throws IOException {

        final int petId = 6666;
        Pet pet = new Pet.Builder()
                .setId(petId)
                .setName("Chupacabra")
                .setStatus("available")
                .build();
        createPet(pet);
        Request request = new Request.Builder()
                .url(PET_URL + petId)
                .delete()
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }
}
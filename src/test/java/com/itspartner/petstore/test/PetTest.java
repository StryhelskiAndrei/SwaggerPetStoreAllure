package com.itspartner.petstore.test;

import com.itspartner.petstore.Pet;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.itspartner.petstore.test.Constants.Headers.CONTENT_TYPE_JSON;
import static com.itspartner.petstore.test.Constants.ResponseCodes.SUCCESS;
import static com.itspartner.petstore.test.Constants.ResponseCodes.FAILURE;
import static com.itspartner.petstore.test.Constants.Urls.PET_URL;

public class PetTest extends PetStoreTest {

    @BeforeClass
    public void beforeClass(ITestContext context) {
//        String value = context.getCurrentXmlTest().getParameter("env");
//        System.err.println("webdriver.deviceName.iPhone = " + value);
        System.out.println("Start testing Pet class");
    }

    @Test(groups = {"Smoke"})
    public void postAddPet() throws IOException {
        Response response = createPet(DEFAULT_PET);
        Assert.assertEquals(response.code(), SUCCESS);
        System.out.println(response.body().string());
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
                .url(PET_URL + petId)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void getPetByNull() throws IOException {
        Request request = new Request.Builder()
                .url(PET_URL + null)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), FAILURE);
    }


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
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void updatePetWithoutId() throws IOException {
        Pet updatedPetWithoutId = new Pet.Builder()
                .setName("Kitty")
                .setStatus("available")
                .build();
        String jsnObj = gson.toJson(updatedPetWithoutId);
        RequestBody body = RequestBody.create(CONTENT_TYPE_JSON, jsnObj);
        Request request = new Request.Builder()
                .url(PET_URL)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test()
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

    @Test
    public void deletePetByNull() throws IOException {
        Request request = new Request.Builder()
                .url(PET_URL + null)
                .delete()
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), FAILURE);
    }
}
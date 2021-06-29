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
import static com.itspartner.petstore.test.Constants.PetStatuses;


public class PetTest extends PetStoreTest {

    @BeforeClass
    public void beforeClass(ITestContext context) {
        System.out.println("Start testing Pet class");
    }

    @Test(groups = {"Smoke"})
    public void postAddPet() throws IOException {
        Pet pet =  Pet.createPositivePet();
        Response response = createPet(pet);
        Assert.assertEquals(response.code(), SUCCESS, "Status code isn't expected");
    }

    @Test
    public void updatingPetPut() throws IOException {
        Pet pet = Pet.createPositivePet();
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
                .url(PET_URL + PetStatuses.ALL_STATUSES)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
        Assert.assertNotNull(response.body());
    }

    @Test
    public void getPetById() throws IOException {
        final int petId = Pet.getRandomId();
        Pet pet = new Pet.Builder()
                .setId(petId)
                .setName(Pet.getRandomName())
                .setStatus(PetStatuses.AVAILABLE)
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
        final int petIdForUpdating = Pet.getRandomId();
        Pet pet = new Pet.Builder()
                .setId(petIdForUpdating)
                .setName(Pet.getRandomName())
                .setStatus(PetStatuses.AVAILABLE)
                .build();
        createPet(pet);
        Pet updatedPet = new Pet.Builder()
                .setId(petIdForUpdating)
                .setName(Pet.getRandomName())
                .setStatus(PetStatuses.SOLD)
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
                .setName(Pet.getRandomName())
                .setStatus(PetStatuses.AVAILABLE)
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

    @Test(invocationCount = 5)
    public void deletePetById() throws IOException {
        final int petId = Pet.getRandomId();
        Pet pet = new Pet.Builder()
                .setId(petId)
                .setName(Pet.getRandomName())
                .setStatus(PetStatuses.AVAILABLE)
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
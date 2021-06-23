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
import static com.itspartner.petstore.test.Constants.Urls.ORDER_URL;
import static com.itspartner.petstore.test.Constants.Urls.USER_URL;

public class PetStoreTest {

    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();
//    public Response getPetById(int testPetId) throws IOException {
//
//        Request request = new Request.Builder()
//                .url(Constants.Urls.GetPetById.formatted(testPetId))
//                .build();
//
//        return client.newCall(request).execute();
//    }

    public Response createPet(Pet pet) throws IOException {
        String jsnObj = gson.toJson(pet);
        RequestBody body = RequestBody.create(CONTENT_TYPE_JSON, jsnObj);
        Request request = new Request.Builder()
                .url("https://petstore.swagger.io/v2/pet/")
                .post(body)
                .build();

        return client.newCall(request).execute();
    }

    public Response createOrder(int id, int petId, int quantity, String shipDate, String status, boolean complete) throws IOException {
        Order order = new Order.Builder()
                .setId(id)
                .setPetId(petId)
                .setQuantity(quantity)
                .setShipDate(shipDate)
                .setStatus(status)
                .setComplete(complete)
                .build();
        String jsnObj = gson.toJson(order);
        RequestBody body = RequestBody.create(CONTENT_TYPE_JSON, jsnObj);
        Request request = new Request.Builder()
                .url(ORDER_URL)
                .post(body)
                .build();
        return client.newCall(request).execute();
    }

    public Response createUser(int id, String username, String firstName, String lastName, String email, String password, String phone, int userStatus) throws IOException {
        User user = new User.Builder()
                .setId(id)
                .setUsername(username)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setPhone(phone)
                .setUserStatus(userStatus)
                .build();

        String jsnObj = gson.toJson(user);
        RequestBody body = RequestBody.create(CONTENT_TYPE_JSON, jsnObj);
        Request request = new Request.Builder()
                .url(USER_URL)
                .post(body)
                .build();

        return client.newCall(request).execute();
    }

    final Pet DEFAULT_PET = new Pet.Builder().setId(323).setStatus("available").setName("Kesha").build();
}

package com.itspartner.petstore.test;

import com.itspartner.petstore.Order;
import com.google.gson.Gson;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.itspartner.petstore.test.Constants.Headers.CONTENT_TYPE_JSON;
import static com.itspartner.petstore.test.Constants.ResponseCodes.SUCCESS;

import static org.testng.Assert.*;

public class OrderTest {

    String url = "https://petstore.swagger.io/v2/store/order";
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    @Test
    public void inventoryCheck() throws IOException {
        Request request = new Request.Builder()
                .url("https://petstore.swagger.io/v2/store/inventory")
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);

    }

    @Test
    public void orderForPet() throws IOException {
        //Order order = new Order(5555, 1, 3,"2021-06-14T07:46:14.469Z", "placed", true);
        Order order = new Order.Builder()
                .setId(5555)
                .setPetId(1)
                .setQuantity(3)
                .setShipDate("2021-06-14T07:46:14.469Z")
                .setStatus("placed")
                .setComplete(true)
                .build();

        String jsnObj = gson.toJson(order);
        RequestBody body = RequestBody.create(CONTENT_TYPE_JSON, jsnObj);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void findPurchaseOrderById() throws IOException {
        Request request = new Request.Builder()
                .url("https://petstore.swagger.io/v2/store/order/1")
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void deleteTheOrder() {


    }

}
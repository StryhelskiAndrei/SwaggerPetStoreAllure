package com.itspartner.petstore.test;

import com.google.gson.Gson;
import com.itspartner.petstore.Order;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.itspartner.petstore.test.Constants.Headers.CONTENT_TYPE_JSON;
import static com.itspartner.petstore.test.Constants.ResponseCodes.*;
import static com.itspartner.petstore.test.Constants.Urls.INVENTORY_URL;
import static com.itspartner.petstore.test.Constants.Urls.ORDER_URL;

public class OrderTest extends PetStoreTest {

    @Test
    public void inventoryCheck() throws IOException {
        Request request = new Request.Builder()
                .url(INVENTORY_URL)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void makeAnOrder() throws IOException {
        Order order = Order.createPositiveOrder();
        Response response = sendOrder(order);
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void makeAnOrderWithNull() throws IOException {
        String jsnObj = gson.toJson(null);
        //RequestBody body = RequestBody.create(CONTENT_TYPE_JSON, jsnObj);
        RequestBody body = RequestBody.create(jsnObj, CONTENT_TYPE_JSON);
        Request request = new Request.Builder()
                .url(ORDER_URL)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), ERROR_400);
    }

    @Test
    public void findPurchaseOrderById() throws IOException {
        final int orderId = Order.getRandomId();
        Order order = Order.createPositiveOrder(orderId);
        sendOrder(order);
        Request request = new Request.Builder()
                .url(ORDER_URL + '/' + orderId)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test()
    public void deleteTheOrder() throws IOException {
        final int orderId = Order.getRandomId();
        System.out.println(orderId);
        Order order = Order.createPositiveOrder(orderId);
        sendOrder(order);
        Request request = new Request.Builder()
                .url(ORDER_URL + '/' + orderId)
                .delete()
                .build();
        Response response2 = client.newCall(request).execute();
        Assert.assertEquals(response2.code(), SUCCESS);
    }

    @Test
    public void deleteTheOrderWithNull() throws IOException {
        Request request = new Request.Builder()
                .url(ORDER_URL + '/' + null)
                .delete()
                .build();
        Response response2 = client.newCall(request).execute();
        Assert.assertEquals(response2.code(), FAILURE);
    }
}
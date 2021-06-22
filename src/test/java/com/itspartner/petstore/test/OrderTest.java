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
import static com.itspartner.petstore.test.Constants.ResponseCodes.SUCCESS;
import static com.itspartner.petstore.test.Constants.Urls.ORDER_URL;
import static com.itspartner.petstore.test.Constants.Urls;

public class OrderTest extends PetStoreTest {

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
                .url(ORDER_URL)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void findPurchaseOrderById() throws IOException {
        final int orderId = 1;
        createOrder(orderId,1,3,"2021-06-14T07:46:14.469Z", "placed", true);
        Request request = new Request.Builder()
                .url("https://petstore.swagger.io/v2/store/order/" + orderId)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), SUCCESS);
    }

    @Test
    public void deleteTheOrder() throws IOException {
        final int orderId = 5555;
        createOrder(orderId,1,3,"2021-06-14T07:46:14.469Z", "placed", true);
        Request request = new Request.Builder()
                .url("https://petstore.swagger.io/v2/store/order/" + orderId)
                .delete()
                .build();
        Response response2 = client.newCall(request).execute();
        Assert.assertEquals(response2.code(), SUCCESS);
    }
}
package apiClasses;

import com.google.gson.Gson;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public class PetTest {
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();
    String url = "https://petstore.swagger.io/v2/pet";
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    @Test
    public void GetID() throws IOException {
        Request request = new Request.Builder()
                .url("https://petstore.swagger.io/v2/pet/55555")
                .build();
        Response response = client.newCall(request).execute();

        ResponseBody responseBody = client.newCall(request).execute().body();
        Pet pet = gson.fromJson(responseBody.string(), Pet.class);
        System.out.println(pet.name);
        Assert.assertEquals(response.code(), 200);
    }

    @Test
    public void PostAddPet() throws IOException {
        Pet pet = new Pet();
        pet.id = 55555;
        pet.name = "Chupakabra";
        pet.status = "available";
        Gson gson = new Gson();
        String jsnObj = gson.toJson(pet);

        RequestBody body = RequestBody.create(JSON, jsnObj);
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
}
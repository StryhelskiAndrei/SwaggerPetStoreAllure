package com.itspartner.petstore;

import com.google.gson.Gson;
import org.testng.xml.dom.Tag;

import java.util.List;

public class Pet {

    public Integer id;

    public String name;

    public List<String> photoUrls = null;

    public List<Tag> tags = null;

    public String status;

    public  Pet(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Pet(){}

    public static Pet fromJsonString(String responseBody) {
        Gson gson = new Gson();
        Pet pet = new Pet();
        pet = gson.fromJson(responseBody, Pet.class);
        return pet;
    }
}

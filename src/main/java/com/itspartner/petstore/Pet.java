package com.itspartner.petstore;

import com.google.gson.Gson;
import org.testng.xml.dom.Tag;

import java.util.List;

public class Pet extends PetStore{

    private Integer id;

    private String name;

    private List<String> photoUrls = null;

    private List<Tag> tags = null;

    private String status;


    public static class Builder {

        private Pet mPet = new Pet();
        public Builder setId(int id) {
            this.mPet.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.mPet.name = name;
            return this;
        }

        public Builder setPhotoUrls(List<String> photoUrls) {
            this.mPet.photoUrls = photoUrls;
            return this;
        }

        public Builder setTags(List<Tag> tags) {
            this.mPet.tags = tags;
            return this;
        }

        public Builder setStatus(String status) {
            this.mPet.status = status;
            return this;
        }

        public Pet build() {
            return mPet;
        }
    }

    public static Pet fromJsonString(String responseBody) {
        Gson gson = new Gson();
        Pet pet = new Pet();
        pet = gson.fromJson(responseBody, Pet.class);
        return pet;
    }

    public static Pet createPositivePet() {
        Pet pet = new Pet.Builder()
                .setId(getRandomId())
                .setStatus("available")
                .setName(getRandomName())
                .build();
        return pet;
    }
}

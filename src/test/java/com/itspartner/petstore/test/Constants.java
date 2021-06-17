package com.itspartner.petstore.test;

import okhttp3.MediaType;

public class Constants {
    public static class Headers {
        public static final MediaType CONTENT_TYPE_JSON
                = MediaType.get("application/json; charset=utf-8"); 
    }

    public static class Urls {
        /**
         * Provide pet id as integer value
         */
        public static final String GetPetById = "https://petstore.swagger.io/v2/pet/%d";
        public static final String PET_URL = "https://petstore.swagger.io/v2/pet/";
        public static final String ORDER_URL = "https://petstore.swagger.io/store/order/";
        public static final String USER_URL = "https://petstore.swagger.io/v2/user/";

    }

     public static class ResponseCodes {
        /**
         * Provide pet id as integer value
         */
        public static final int SUCCESS = 200;

    }


}

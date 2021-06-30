package com.itspartner.petstore.test;

import okhttp3.MediaType;

public class Constants {
    public static class Headers {
        public static final MediaType CONTENT_TYPE_JSON
                = MediaType.get("application/json; charset=utf-8");
    }

    public static class Urls {
        public static final String GetPetById = "https://petstore.swagger.io/v2/pet/%d";
        public static final String PET_URL = "https://petstore.swagger.io/v2/pet/";
        public static final String ORDER_URL = "https://petstore.swagger.io/v2/store/order";
        public static final String USER_URL = "https://petstore.swagger.io/v2/user/";
        public static final String INVENTORY_URL = "https://petstore.swagger.io/v2/store/inventory";

    }

    public static class ResponseCodes {
        public static final int SUCCESS = 200;
        public static final int FAILURE = 404;
        public static final int ERROR_400 = 400;
        public static final int ERROR_405 = 405;

    }

    public static class PetStatuses {
        public static final String AVAILABLE = "available";
        public static final String PENDING = "pending";
        public static final String SOLD = "sold";
        public static final String ALL_STATUSES = "findByStatus?status=available&status=pending&status=sold";
    }
}

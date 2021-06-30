package com.itspartner.petstore;

import java.util.Random;

public abstract class PetStore {

    public static int getRandomId() {
        Random random = new Random();
        int min = 1;
        int max = 9999;
        int diff = max - min;
        return random.nextInt(diff);
    }

    public static int getRandomLength() {
        Random random = new Random();
        int min = 1;
        int max = 15;
        int diff = max - min;
        return random.nextInt(diff);
    }

    public static String getRandomName() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int nameLength = getRandomLength();
        for (int i = 0; i < nameLength; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static String getRandomPassword() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789!?@#$%^&*()[] ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int passLength = getRandomLength();
        for (int i = 0; i < passLength; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}

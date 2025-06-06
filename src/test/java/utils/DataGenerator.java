package utils;

import java.util.UUID;

public class DataGenerator {

    public static String generateUsername() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8);
    }

    public static String generatePassword() {
        // Uma senha com letra maiúscula, minúscula e número
        return "Pass@" + (int)(Math.random() * 10000);
    }
}

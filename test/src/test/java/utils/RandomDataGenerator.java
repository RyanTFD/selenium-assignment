package utils;

import java.util.UUID;

public class RandomDataGenerator {

    public static String randomUsername() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8);
    }

    public static String randomEmail() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8) + "@test.com";
    }

    public static String randomPassword() {
        return "Pass_" + UUID.randomUUID().toString().substring(0, 8);
    }
}
package com.sytoss.trainee.utils;

public class Validation {

    public static boolean isValidId(String id) {
        return id.matches("^[0-9]+");
    }
    public static boolean isValidName(String name) {
        return name.matches("^[a-zA-Z]+");
    }
    public static boolean isValidDate(String name) {
        return name.matches("^[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]");
    }

}

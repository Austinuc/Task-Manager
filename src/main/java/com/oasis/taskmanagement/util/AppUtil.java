package com.oasis.taskmanagement.util;

public class AppUtil {
    private AppUtil(){}
    public static boolean validEmail(String email) {
        String regex = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$";
        return email.matches(regex);
    }
}

package com.exception;

import java.util.Random;

public class SeverityClassifier {

    public String assignSeverity(String server) {
        Random random = new Random();
        if (server.equals("A")) {
            int randomNum = random.nextInt(10);
            return randomNum >= 2 ? "High" : "Medium";
        } else {
            int randomNum = random.nextInt(10);
            return randomNum >= 2 ? "Low" : "Medium";
        }
    }
}

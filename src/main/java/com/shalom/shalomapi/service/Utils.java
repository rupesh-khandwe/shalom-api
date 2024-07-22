package com.shalom.shalomapi.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Utils {
     String generatingRandomAlphanumericString(String id, String fileExt) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
        return id+"/"+generatedString+"."+fileExt.trim();
    }
}

package com.example.bebiki_hw2_api.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {
    public static String randomString() {
        return "test_" + RandomStringUtils.randomAlphabetic(10);
    }

    public static String randomPhone() {
        return "+7" + RandomStringUtils.randomNumeric(10);
    }
}

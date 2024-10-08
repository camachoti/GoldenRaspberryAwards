package com.example.goldenraspberryawards.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public static List<String> splitProducer(String producers) {
        return new ArrayList<>(Arrays.asList(producers.split(", | and ")));
    }

}

package com.midas.demo.utils;

import android.os.Build;

public class AppUtils {


    public static String getMobileModel() {
        String model = Build.MODEL;
        return model;

    }
}

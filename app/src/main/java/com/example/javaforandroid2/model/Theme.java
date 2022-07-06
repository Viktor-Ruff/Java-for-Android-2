package com.example.javaforandroid2.model;

import androidx.annotation.StyleRes;

import com.example.javaforandroid2.R;

/**
 * Created by Viktor-Ruff
 * Date: 03.07.2022
 * Time: 18:54
 */

public enum Theme {
    WHITE(R.style.Theme_JavaForAndroid2, R.string.white_theme, "themeWhite"),
    DARK(R.style.Theme_JavaForAndroid2_Dark, R.string.dark_theme, "themeDark");


    @StyleRes
    private int themeRes;
    @StyleRes
    private int title;

    private String key;

    Theme(int themeRes, int title, String key) {
        this.themeRes = themeRes;
        this.title = title;
        this.key = key;
    }

    public int getThemeRes() {
        return themeRes;
    }

    public int getTitle() {
        return title;
    }

    public String getKey() {
        return key;
    }
}

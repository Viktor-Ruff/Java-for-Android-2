package com.example.javaforandroid2.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Viktor-Ruff
 * Date: 03.07.2022
 * Time: 19:11
 */
public class ThemeRepositoryImpl implements ThemeRepository {

    private static ThemeRepository INSTANCE;

    private SharedPreferences preferences;
    private static final String KEY_THEME = "KEY_THEME";

    public ThemeRepositoryImpl(Context context) {
        preferences = context.getSharedPreferences("themes.xml", Context.MODE_PRIVATE);

    }

    public static ThemeRepository getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ThemeRepositoryImpl(context);
        }
        return INSTANCE;
    }

    @Override
    public Theme getSavedTheme() {

        String savedKey = preferences.getString(KEY_THEME, Theme.WHITE.getKey());

        for (Theme them : Theme.values()) {
            if (them.getKey().equals(savedKey)) {
                return them;
            }
        }

        return Theme.WHITE;
    }

    @Override
    public void saveTheme(Theme theme) {

        preferences.edit()
                .putString(KEY_THEME, theme.getKey())
                .apply();

    }

    @Override
    public List<Theme> getAll() {
        return Arrays.asList(Theme.values());
    }

    public static ThemeRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ThemeRepositoryImpl(context);
        }
        return INSTANCE;
    }

}

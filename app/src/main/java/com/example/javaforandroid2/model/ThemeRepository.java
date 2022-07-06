package com.example.javaforandroid2.model;

import java.util.List;

/**
 * Created by Viktor-Ruff
 * Date: 03.07.2022
 * Time: 19:07
 */
public interface ThemeRepository {

    Theme getSavedTheme();

    void saveTheme(Theme theme);

    List<Theme> getAll();
}

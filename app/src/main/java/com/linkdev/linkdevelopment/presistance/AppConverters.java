package com.linkdev.linkdevelopment.presistance;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linkdev.linkdevelopment.model.Article;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

class AppConverters {

    @TypeConverter
    public static List<Article> toList(String value) {
        Type listType = new TypeToken<List<Article>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<Article> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

}

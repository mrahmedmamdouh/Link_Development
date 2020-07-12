package com.linkdev.linkdevelopment.presistance;


import android.content.Context;
import com.linkdev.linkdevelopment.model.Article;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Article.class}, version = 1)
public abstract class ArticleDatabase extends RoomDatabase {

    private static ArticleDatabase instance;

    public static ArticleDatabase getInstance(final Context context) {
        if (instance == null) {
            String DATABASE_NAME = "article_db";
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ArticleDatabase.class,
                    DATABASE_NAME
            ).allowMainThreadQueries().setJournalMode(JournalMode.TRUNCATE).build();
        }
        return instance;
    }

    public abstract ArticleDao getArticleDao();
}

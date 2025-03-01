package com.linkdev.linkdevelopment.presistance;

import com.linkdev.linkdevelopment.model.Article;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ArticleDao {


    @Insert(onConflict = REPLACE)
    void insertArticle(Article article);

    @Query("SELECT * from article_data where id BETWEEN 1 AND 10")
    LiveData<List<Article>> getAllArticlesI();

    @Query("SELECT * from article_data where id BETWEEN 11 AND 20")
    LiveData<List<Article>> getAllArticlesII();

}

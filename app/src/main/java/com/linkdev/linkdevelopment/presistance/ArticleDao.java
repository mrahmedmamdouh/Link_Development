package com.linkdev.linkdevelopment.presistance;

import com.linkdev.linkdevelopment.model.Article;
import com.linkdev.linkdevelopment.model.ArticleResponse;

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

    @Query("SELECT * from article_data")
    LiveData<List<Article>> getAllArticles();

}

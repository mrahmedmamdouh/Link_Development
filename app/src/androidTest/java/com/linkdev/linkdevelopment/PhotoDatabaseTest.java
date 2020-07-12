package com.linkdev.linkdevelopment;

import android.util.Log;

import com.linkdev.linkdevelopment.model.Article;
import com.linkdev.linkdevelopment.util.LiveDataTestUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PhotoDatabaseTest extends DbTest {
    @Mock
    public Observer<List<Article>> observer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void insertAndReadArticlesTest() throws InterruptedException {
        Article article = new Article(1, "ahmed mamdouh", "the test case", "test room database", "room.databse", "roomDB", "atLinkDev");
        database.getArticleDao().insertArticle(article);
        LiveDataTestUtil<List<Article>> liveDataTestUtil = new LiveDataTestUtil<>();
        List<Article> articles = liveDataTestUtil.getValue(database.getArticleDao().getAllArticles());
        assertNotNull(articles);
        assertEquals(article.getAuthor(),articles.get(0).getAuthor());
    }


}

package com.linkdev.linkdevelopment;

import com.linkdev.linkdevelopment.model.Article;
import com.linkdev.linkdevelopment.util.LiveDataTestUtil;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ArticleDatabaseTest extends DbTest {


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void insertAndReadArticlesTest() throws InterruptedException {
        Article article = new Article(1, "ahmed mamdouh", "the test case", "test room database", "room.database", "roomDB", "atLinkDev");
        database.getArticleDao().insertArticle(article);
        LiveDataTestUtil<List<Article>> liveDataTestUtil = new LiveDataTestUtil<>();
        List<Article> articles = liveDataTestUtil.getValue(database.getArticleDao().getAllArticlesI());
        assertNotNull(articles);
        assertEquals(article.getAuthor(),articles.get(0).getAuthor());
    }


}

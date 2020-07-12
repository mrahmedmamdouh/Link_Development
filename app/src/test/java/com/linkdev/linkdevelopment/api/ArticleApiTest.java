package com.linkdev.linkdevelopment.api;

import com.linkdev.linkdevelopment.model.ArticleResponse;
import com.linkdev.linkdevelopment.requests.ArticleApi;
import com.linkdev.linkdevelopment.utils.Constants;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.io.IOException;
import retrofit2.Response;

@RunWith(JUnit4.class)
public class ArticleApiTest extends ApiAbstract<ArticleApi> {

    private ArticleApi trendingApi;

    @Before
    public void initService() {
        this.trendingApi = createService();
    }

    @Test
    public void fetchArticlesTest() throws IOException {
        enqueueResponse();
        Response<ArticleResponse> response = trendingApi.testGetArticles("the-next-web", Constants.API_KEY).execute();
        Assert.assertTrue(response.isSuccessful());

        ArticleResponse apiResponse = response.body();
        assert apiResponse != null;
        Assert.assertNotNull(apiResponse.getArticles());
    }
}

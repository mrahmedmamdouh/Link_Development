package com.linkdev.linkdevelopment.model.ArticleRepository;

import android.content.Context;
import android.util.Log;

import com.linkdev.linkdevelopment.App;
import com.linkdev.linkdevelopment.AppExecutors;
import com.linkdev.linkdevelopment.model.Article;
import com.linkdev.linkdevelopment.model.ArticleResponse;
import com.linkdev.linkdevelopment.presistance.ArticleDao;
import com.linkdev.linkdevelopment.presistance.ArticleDatabase;
import com.linkdev.linkdevelopment.requests.ServiceGenerator;
import com.linkdev.linkdevelopment.requests.responses.ApiResponse;
import com.linkdev.linkdevelopment.utils.Constants;
import com.linkdev.linkdevelopment.utils.NetworkBoundResource;
import com.linkdev.linkdevelopment.utils.Resource;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

public class ArticleRepository {

    private static final String TAG = "TrendRepository";
    private static ArticleRepository instance;
    private final ArticleDao articleDao;

    private ArticleRepository(Context context) {
        articleDao = ArticleDatabase.getInstance(context).getArticleDao();
    }

    public static ArticleRepository getInstance() {
        if (instance == null) {
            instance = new ArticleRepository(App.getmContext());
        }
        return instance;
    }


    public LiveData<Resource<List<Article>>> getArticle_I() {
        return new NetworkBoundResource<List<Article>, ArticleResponse>(AppExecutors.getInstance()) {
            @Override
            protected void saveCallResult(@NonNull ArticleResponse item) {
                Log.d(TAG, "saveCallResult: inserted in database");
                for (Article article : item.getArticles()) {
                    articleDao.insertArticle(article);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Article> data) {
                Log.d(TAG, "saveCallResult: fetched from network");
                return data == null || data.size() == 0;
            }

            @NonNull
            @Override
            protected LiveData<List<Article>> loadFromDb() {
                Log.d(TAG, "saveCallResult: loaded from database");
                return articleDao.getAllArticles();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<ArticleResponse>> createCall() {
                Log.d(TAG, "saveCallResult: network Call");
                return ServiceGenerator.getArticleApi().getArticles("the-next-web", Constants.API_KEY);
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<List<Article>>> getArticle_II() {
        return new NetworkBoundResource<List<Article>, ArticleResponse>(AppExecutors.getInstance()) {
            @Override
            protected void saveCallResult(@NonNull ArticleResponse item) {
                Log.d(TAG, "saveCallResult: inserted in database");
                for (Article article : item.getArticles()) {
                    articleDao.insertArticle(article);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Article> data) {
                Log.d(TAG, "saveCallResult: fetched from network");
                return data == null || data.size() == 0;
            }

            @NonNull
            @Override
            protected LiveData<List<Article>> loadFromDb() {
                Log.d(TAG, "saveCallResult: loaded from database");
                return articleDao.getAllArticles();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<ArticleResponse>> createCall() {
                Log.d(TAG, "saveCallResult: network Call");
                return ServiceGenerator.getArticleApi().getArticles("associated-press", Constants.API_KEY);
            }
        }.getAsLiveData();
    }


}

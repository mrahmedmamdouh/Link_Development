package com.linkdev.linkdevelopment.requests;

import com.linkdev.linkdevelopment.model.ArticleResponse;
import com.linkdev.linkdevelopment.requests.responses.ApiResponse;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleApi {

    @GET("/v1/articles")
    LiveData<ApiResponse<ArticleResponse>> getArticles(@Query("source") String source, @Query("apiKey") String key);

    @GET("/v1/articles")
    Call<ArticleResponse> testGetArticles(@Query("source") String source, @Query("apiKey") String key);

}

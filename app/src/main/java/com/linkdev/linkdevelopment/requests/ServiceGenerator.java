package com.linkdev.linkdevelopment.requests;

import com.linkdev.linkdevelopment.utils.Constants;
import com.linkdev.linkdevelopment.utils.RetrofitCallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
                    .addCallAdapterFactory(new RetrofitCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create());

    private static final Retrofit retrofit = retrofitBuilder.build();

    private static final ArticleApi ARTICLE_API = retrofit.create(ArticleApi.class);

    public static ArticleApi getArticleApi(){
        return ARTICLE_API;
    }
}

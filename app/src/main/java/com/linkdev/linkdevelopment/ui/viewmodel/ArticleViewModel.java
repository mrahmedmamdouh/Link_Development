package com.linkdev.linkdevelopment.ui.viewmodel;

import android.app.Application;
import android.util.Pair;

import com.linkdev.linkdevelopment.model.Article;
import com.linkdev.linkdevelopment.model.ArticleRepository.ArticleRepository;
import com.linkdev.linkdevelopment.utils.AbsentLiveData;
import com.linkdev.linkdevelopment.utils.CombinedLiveData;
import com.linkdev.linkdevelopment.utils.Resource;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public class ArticleViewModel extends AndroidViewModel {

    private MediatorLiveData<Pair<Resource<List<Article>>,Resource<List<Article>>>> getAllArticles;

    public ArticleViewModel(@NonNull Application application) {
        super(application);
        ArticleRepository repository = ArticleRepository.getInstance();
        LiveData<Resource<List<Article>>> getArticle_I = repository.getArticle_I();
        LiveData<Resource<List<Article>>> getArticle_II = repository.getArticle_II();
//        getAllArticles = new MediatorLiveData<>();
//        getAllArticles.addSource(getArticle_I, value -> getAllArticles.setValue(value));
//        getAllArticles.addSource(getArticle_II, value -> getAllArticles.setValue(value));

        getAllArticles = new CombinedLiveData<>(getArticle_I, getArticle_II);
    }

    public LiveData<Pair<Resource<List<Article>>,Resource<List<Article>>>> getArticles() {
        return getAllArticles;
    }

}

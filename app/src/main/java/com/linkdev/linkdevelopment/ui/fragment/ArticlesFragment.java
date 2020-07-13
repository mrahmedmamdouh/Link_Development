package com.linkdev.linkdevelopment.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.linkdev.linkdevelopment.R;
import com.linkdev.linkdevelopment.databinding.FragmentArticlesListBinding;
import com.linkdev.linkdevelopment.model.Article;
import com.linkdev.linkdevelopment.ui.adapter.MyArticlesRecyclerViewAdapter;
import com.linkdev.linkdevelopment.ui.viewmodel.ArticleViewModel;
import com.linkdev.linkdevelopment.utils.Resource;

import java.util.ArrayList;
import java.util.List;

public class ArticlesFragment extends Fragment {

    private ArticleViewModel viewModel;
    private FragmentArticlesListBinding binding;
    private MyArticlesRecyclerViewAdapter adapter;

    public ArticlesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ArticleViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_articles_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        observeData();
    }

    private void init() {
        binding.progressBarShowEnroll.show();
        binding.list.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.list.setHasFixedSize(true);
        binding.list.addItemDecoration(new DividerItemDecoration(requireActivity(),
                DividerItemDecoration.VERTICAL));

    }

    private void observeData() {
        viewModel.getArticles().observe(getViewLifecycleOwner(), resourceResourcePair -> {
            if (resourceResourcePair.first.status.equals(Resource.Status.SUCCESS) && resourceResourcePair.second.status.equals(Resource.Status.SUCCESS)) {
                assert resourceResourcePair.first.data != null;
                assert resourceResourcePair.second.data != null;
                if (resourceResourcePair.first.data.size() != 0 && resourceResourcePair.second.data.size() != 0) {
                    List<Article> articles = new ArrayList<>();
                    articles.addAll(resourceResourcePair.second.data);
                    articles.addAll(resourceResourcePair.first.data);
                    binding.progressBarShowEnroll.hide();
                    adapter = new MyArticlesRecyclerViewAdapter(getActivity(), articles);
                    binding.list.setAdapter(adapter);
                    viewModel.getArticles().removeObservers(getViewLifecycleOwner());
                }
            }
        });
    }
}

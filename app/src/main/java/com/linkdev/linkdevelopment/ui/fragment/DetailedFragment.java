package com.linkdev.linkdevelopment.ui.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.linkdev.linkdevelopment.R;
import com.linkdev.linkdevelopment.databinding.FragmentDetailedBinding;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailedFragment extends Fragment {

    private String url;
    private FragmentDetailedBinding binding;
    public DetailedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detailed,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
        onClickListener();
    }

    private void getData() {
        if (!Objects.requireNonNull(requireArguments().getString("title")).isEmpty()){
            if (Objects.equals(requireArguments().getString("image"), ""))
                binding.image.setImageResource(R.drawable.placeholder);
            else
                Glide.with(requireActivity()).load(requireArguments().getString("image")).into(binding.image);

            binding.author.setText(requireArguments().getString("author"));
            binding.title.setText(requireArguments().getString("title"));
            binding.description.setText(requireArguments().getString("description"));
            url = requireArguments().getString("url");
        }
    }

    private void onClickListener() {
        binding.materialButton.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);

        });
    }
}

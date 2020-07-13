package com.linkdev.linkdevelopment.ui.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.linkdev.linkdevelopment.R;
import com.linkdev.linkdevelopment.model.Article;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MyArticlesRecyclerViewAdapter extends RecyclerView.Adapter<MyArticlesRecyclerViewAdapter.ViewHolder> {

    private final List<Article> mValues;
    private Context context;

    public MyArticlesRecyclerViewAdapter(Context context, List<Article> items) {
        mValues = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_articles, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.title.setText(mValues.get(position).getTitle());
        holder.author.setText(mValues.get(position).getAuthor());
        holder.date.setText(dateFormatter(mValues.get(position).getPublishedAt()));
        if (mValues.get(position).getUrlToImage().equals(""))
            holder.imageView.setImageResource(R.drawable.placeholder);
        else
            Glide.with(context).load(mValues.get(position).getUrlToImage()).into(holder.imageView);
        holder.mView.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView title;
        final TextView author;
        final TextView date;
        final AppCompatImageView imageView;
        Article mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            title = view.findViewById(R.id.title);
            author = view.findViewById(R.id.author);
            date = view.findViewById(R.id.date);
            imageView = view.findViewById(R.id.image);
        }

    }

    private String dateFormatter(String date) {
        String reverse1 = "";
        LocalDateTime datetime = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            datetime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            reverse1 = datetime.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
        }
        return reverse1;
    }
}

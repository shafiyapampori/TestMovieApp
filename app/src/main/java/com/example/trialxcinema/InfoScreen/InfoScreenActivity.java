package com.example.trialxcinema.InfoScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.trialxcinema.DashboardActivity.ResultsItem;
import com.example.trialxcinema.R;
import com.example.trialxcinema.databinding.ActivityInfoScreenBinding;

public class InfoScreenActivity extends AppCompatActivity {
    ResultsItem data;
    ActivityInfoScreenBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfoScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        data = getIntent().getExtras().getParcelable("ResultItem");

        Glide.with(InfoScreenActivity.this)
                .load(data.getBackdropPath())
                .placeholder(R.drawable.placeholder_image)
                .into( binding.imgInnerThumbnail);

        Glide.with(InfoScreenActivity.this)
                .load(data.getBackdropPath())
                .placeholder(R.drawable.placeholder_image)
                .into( binding.circleImageView);

        binding.tvRating.setText(data.getVoteAverage());
        binding.tvDate.setText(data.getReleaseDate());
        binding.tvDescription.setText(data.getOverview());
        binding.tvMovieName.setText(data.getTitle());
        binding.imgBackArrow.setOnClickListener(view1 -> {
            onBackPressed();
        });
    }
}
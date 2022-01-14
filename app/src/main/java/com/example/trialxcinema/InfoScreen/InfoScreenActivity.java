package com.example.trialxcinema.InfoScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;

import com.bumptech.glide.Glide;
import com.example.trialxcinema.DashboardActivity.DashboardActivity;
import com.example.trialxcinema.DashboardActivity.ResultsItem;
import com.example.trialxcinema.R;
import com.example.trialxcinema.VideoActivity.VideoActivity;
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

        binding.btnPlay.setOnClickListener(view1 -> {
            startActivity(new Intent(InfoScreenActivity.this, VideoActivity.class));
        });
    }
}
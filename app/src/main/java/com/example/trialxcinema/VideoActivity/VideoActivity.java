package com.example.trialxcinema.VideoActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;

import com.example.trialxcinema.R;
import com.example.trialxcinema.databinding.ActivityVideoBinding;

public class VideoActivity extends AppCompatActivity {
    ActivityVideoBinding binding;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityVideoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.imgBackArrow.setOnClickListener(view1 -> {
            onBackPressed();
        });

        String videopath = "android.resource://com.example.trialxcinema/"+R.raw.kitten;
        Uri uri = Uri.parse(videopath);
        binding.Videov.setVideoURI(uri);
        mediaController = new MediaController(VideoActivity.this);
        binding.Videov.setMediaController(mediaController);
        mediaController.setAnchorView(binding.Videov);
        binding.Videov.start();
    }
}
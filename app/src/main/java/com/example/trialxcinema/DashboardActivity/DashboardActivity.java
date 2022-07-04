package com.example.trialxcinema.DashboardActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.example.trialxcinema.InfoScreen.InfoScreenActivity;
import com.example.trialxcinema.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Tag;

public class DashboardActivity extends AppCompatActivity {

    public static String API_KEY = "90787843a200cfbfd55b14b39270f6a1";
    List<ResultsItem> listOfMovies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);
        // Obtain the FirebaseAnalytics instance.
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        Toolbar toolbar = findViewById(R.id.toolbar);

        //setSupportActionBar(binding.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(DashboardActivity.this, drawerLayout,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mostPopular:
                        Toast.makeText(DashboardActivity.this, "Most Popular option is open",
                                Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.topRated:
                        Toast.makeText(DashboardActivity.this, "Top Rated option is open",
                                Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.favourites:
                        Toast.makeText(DashboardActivity.this, "Favourites option is open",
                                Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });
        getAllMovieImages();

        GridView gridView = findViewById(R.id.GridView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Bundle params = new Bundle();
                params.putInt("ImageId",view.getId());
                String imageName = "id_"+ String.valueOf(listOfMovies.get(i).getId());

                startActivity(new Intent(DashboardActivity.this, InfoScreenActivity.class)
                        .putExtra("ResultItem",listOfMovies.get(i)));
                Log.e("MOVIE-INDEX::", String.valueOf(i));
               Log.e("IMAGE CLICKED LOGGED::", imageName);
                mFirebaseAnalytics.logEvent(imageName,params);

            }
        });
    }

    public void getAllMovieImages() {
        Call<MovieResponse> movieResponse = ApiClient.getInterface().getAllMovieImages(API_KEY);
        movieResponse.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {

                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    if (movieResponse != null) {
                        listOfMovies = movieResponse.getResults();
                        CustomAdapter customAdapter = new CustomAdapter(listOfMovies, DashboardActivity.this);
                        GridView gridView = findViewById(R.id.GridView);
                        gridView.setAdapter(customAdapter);
                    }

//                    String message = "Request Successful....";
//                    Toast.makeText(DashboardActivity.this, "" + message, Toast.LENGTH_SHORT).show();

                } else {

                    String message = "An error occurred, try again later....";
                    Toast.makeText(DashboardActivity.this, "" + message, Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                String message = t.getLocalizedMessage();
                Log.e("ERROR::", t.getLocalizedMessage());
                Toast.makeText(DashboardActivity.this, "" + message, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public class CustomAdapter extends BaseAdapter {
        private final List<ResultsItem> itemList;
        private final Context context;
        private final LayoutInflater layoutInflater;

        public CustomAdapter(List<ResultsItem> itemList, Context context) {
            this.itemList = itemList;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = layoutInflater.inflate(R.layout.row_grid_item, viewGroup, false);
            }
            ImageView img_moviePoster = view.findViewById(R.id.img_moviePoster);
            Glide.with(DashboardActivity.this)
                        .load(itemList.get(i).getPosterPath())
                    .placeholder(R.drawable.placeholder_image)
                    .into(img_moviePoster);
            return view;
        }
    }
}
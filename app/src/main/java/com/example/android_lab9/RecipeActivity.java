package com.example.android_lab9;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class RecipeActivity extends AppCompatActivity {
    private TextView recipeTV, recipeTitleTV;
    private Button langB, playB, resetB;
    private ImageView recipeIV;
    private VideoView recipeVW;
    MediaPlayer musicMP;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicMP = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recipeIV = findViewById(R.id.recipeIV);
        recipeVW = findViewById(R.id.recipeVW);
        Uri uri= Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.video);
        recipeVW.setVideoURI(uri);
        MediaController videoMC = new MediaController(this);
        recipeVW.setMediaController(videoMC);
        videoMC.setMediaPlayer(recipeVW);
        recipeVW.setVisibility(View.GONE);

        playB = findViewById(R.id.playB);
        resetB = findViewById(R.id.resetB);
        playB.setVisibility(View.GONE);
        resetB.setVisibility(View.GONE);

        playB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicMP != null && musicMP.isPlaying()) {
                    musicMP.pause();
                    playB.setText(R.string.play_b);
                    return;
                }

                Locale locale = getResources().getConfiguration().getLocales().get(0);
                switch (locale.toString().substring(0,2)) {
                    case "en":
                        musicMP = MediaPlayer.create(RecipeActivity.this, R.raw.en);
                        break;
                    case "ru":
                        musicMP = MediaPlayer.create(RecipeActivity.this, R.raw.ru);
                        break;
                    case "pl":
                        musicMP = MediaPlayer.create(RecipeActivity.this, R.raw.pl);
                        break;
                }
                playB.setText(R.string.pause_b);
                musicMP.start();
            }
        });

        resetB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicMP != null) {
                    musicMP.reset();
                    playB.setText(R.string.play_b);
                }
            }
        });


        langB = findViewById(R.id.langB);
        langB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (getString(R.string.lang_b)) {
                    case "EN":
                        setLocale(RecipeActivity.this, "ru");
                        break;
                    case "RU":
                        setLocale(RecipeActivity.this, "pl");
                        break;
                    case "PL":
                        setLocale(RecipeActivity.this, "en");
                        break;
                    default:
                        setLocale(RecipeActivity.this, "en");
                        break;
                }
                if (musicMP != null && musicMP.isPlaying()) {
                    musicMP.stop();
                    playB.setText(R.string.play_b);
                }
                recreate();
            }
        });

        recipeTV = findViewById(R.id.recipeTV);
        recipeTitleTV = findViewById(R.id.recipeTitleTV);

        Intent intent = getIntent();
        switch (intent.getIntExtra("recipe", 0)) {
            case 1:
                recipeIV.setVisibility(View.VISIBLE);
                recipeIV.setImageResource(R.drawable.baseline_egg_alt_24);
                recipeTitleTV.setText(R.string.listview_item1);
                recipeTV.setText(R.string.recipe1);
                break;
            case 2:
                recipeTitleTV.setText(R.string.listview_item2);
                recipeTV.setText(R.string.recipe2);

                recipeVW.setVisibility(View.VISIBLE);
                break;
            case 3:
                recipeTitleTV.setText(R.string.listview_item3);
                recipeTV.setText(R.string.recipe3);

                playB.setVisibility(View.VISIBLE);
                resetB.setVisibility(View.VISIBLE);
                break;
        }
    }
    public static void setLocale(Activity activity, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}
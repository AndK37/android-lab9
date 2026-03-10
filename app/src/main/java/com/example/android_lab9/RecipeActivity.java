package com.example.android_lab9;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class RecipeActivity extends AppCompatActivity {
    private TextView recipeTV, recipeTitleTV;
    private Button langB, playB, resetB;
    private ImageView recipeIV;
    private VideoView recipeVW;
    MediaPlayer musicMP, timerMP;

    private CountDownTimer timer1, timer2, timer3;
    private Boolean timer1IsRunning = false, timer2IsRunning = false, timer3IsRunning = false;
    private LinearLayout timerLL1, timerLL2, timerLL3;
    private TextView timerTV1, timerTV2, timerTV3;
    private Button timerPlayB1, timerPauseB1, timerResetB1, timerPlayB2, timerPauseB2, timerResetB2, timerPlayB3, timerPauseB3, timerResetB3;
    private EditText timer3ET1, timer3ET2;
    private String timer3min, timer3sec;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicMP = null;
        timerMP = null;
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

        timerLL1 = findViewById(R.id.timerLL1);
        timerLL2 = findViewById(R.id.timerLL2);
        timerLL3 = findViewById(R.id.timerLL3);
        timerTV1 = findViewById(R.id.timerTV1);
        timerTV2 = findViewById(R.id.timerTV2);
        timerTV3 = findViewById(R.id.timerTV3);
        timerPlayB1 = findViewById(R.id.timerPlayB1);
        timerPlayB2 = findViewById(R.id.timerPlayB2);
        timerPlayB3 = findViewById(R.id.timerPlayB3);
        timerPauseB1 = findViewById(R.id.timerPauseB1);
        timerPauseB2 = findViewById(R.id.timerPauseB2);
        timerPauseB3 = findViewById(R.id.timerPauseB3);
        timerResetB1 = findViewById(R.id.timerResetB1);
        timerResetB2 = findViewById(R.id.timerResetB2);
        timerResetB3 = findViewById(R.id.timerResetB3);
        timer3ET1 = findViewById(R.id.timer3ET1);
        timer3ET2 = findViewById(R.id.timer3ET2);

        timerLL1.setVisibility(View.GONE);
        timerLL2.setVisibility(View.GONE);
        timerLL3.setVisibility(View.GONE);

        timerPauseB1.setEnabled(false);
        timerPauseB2.setEnabled(false);
        timerPauseB3.setEnabled(false);
        timerResetB1.setEnabled(false);
        timerResetB2.setEnabled(false);
        timerResetB3.setEnabled(false);

        timerPlayB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timer1IsRunning) {
                    int m = Integer.parseInt(timerTV1.getText().toString().split(":")[0]);
                    int s = Integer.parseInt(timerTV1.getText().toString().split(":")[1]);
                    timer1 = new CountDownTimer((m * 60 + s) * 1000, 1000) {
                        @Override
                        public void onFinish() {
                            timerTV1.setText("07:00");
                            timerMP = MediaPlayer.create(RecipeActivity.this, R.raw.timer1);
                            timerMP.start();

                            timerPlayB1.setEnabled(true);
                            timerPauseB1.setEnabled(false);
                            timerResetB1.setEnabled(false);
                        }
                        @Override
                        public void onTick(long millisUntilFinished) {
                            NumberFormat f = new DecimalFormat("00");
                            long min = (millisUntilFinished / 60000) % 60;
                            long sec = (millisUntilFinished / 1000) % 60;
                            timerTV1.setText(f.format(min) + ":" + f.format(sec));
                            if (min == 0) {
                                timerTV1.setTextColor(Color.RED);
                            } else {
                                timerTV1.setTextColor(Color.BLACK);
                            }
                        }
                    };
                    timer1.start();
                    timer1IsRunning = true;

                    timerPlayB1.setEnabled(false);
                    timerPauseB1.setEnabled(true);
                    timerResetB1.setEnabled(true);
                }
            }
        });
        timerPauseB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer1.cancel();
                timer1IsRunning = false;

                timerPauseB1.setEnabled(false);
                timerPlayB1.setEnabled(true);
                timerResetB1.setEnabled(true);
            }
        });
        timerResetB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer1.cancel();
                timerTV1.setText("07:00");
                timer1IsRunning = false;

                timerResetB1.setEnabled(false);
                timerPauseB1.setEnabled(false);
                timerPlayB1.setEnabled(true);

                timerTV1.setTextColor(Color.BLACK);
            }
        });



        timerPlayB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timer2IsRunning) {
                    int m = Integer.parseInt(timerTV2.getText().toString().split(":")[0]);
                    int s = Integer.parseInt(timerTV2.getText().toString().split(":")[1]);
                    timer2 = new CountDownTimer((m * 60 + s) * 1000, 1000) {
                        @Override
                        public void onFinish() {
                            timerTV2.setText("15:00");
                            timerMP = MediaPlayer.create(RecipeActivity.this, R.raw.timer2);
                            timerMP.start();

                            timerPlayB2.setEnabled(true);
                            timerPauseB2.setEnabled(false);
                            timerResetB2.setEnabled(false);
                        }
                        @Override
                        public void onTick(long millisUntilFinished) {
                            NumberFormat f = new DecimalFormat("00");
                            long min = (millisUntilFinished / 60000) % 60;
                            long sec = (millisUntilFinished / 1000) % 60;
                            timerTV2.setText(f.format(min) + ":" + f.format(sec));
                            if (min == 0) {
                                timerTV2.setTextColor(Color.RED);
                            } else {
                                timerTV2.setTextColor(Color.BLACK);
                            }
                        }
                    };
                    timer2.start();
                    timer2IsRunning = true;

                    timerPlayB2.setEnabled(false);
                    timerPauseB2.setEnabled(true);
                    timerResetB2.setEnabled(true);
                }
            }
        });
        timerPauseB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer2.cancel();
                timer2IsRunning = false;

                timerPauseB2.setEnabled(false);
                timerPlayB2.setEnabled(true);
                timerResetB2.setEnabled(true);
            }
        });
        timerResetB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer2.cancel();
                timerTV2.setText("15:00");
                timer2IsRunning = false;

                timerResetB2.setEnabled(false);
                timerPauseB2.setEnabled(false);
                timerPlayB2.setEnabled(true);

                timerTV2.setTextColor(Color.BLACK);
            }
        });



        timerPlayB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer3min = timer3ET1.getText().toString();
                timer3sec = timer3ET2.getText().toString();

                if (!timer3IsRunning) {
                    int m = Integer.parseInt(timer3ET1.getText().toString());
                    int s = Integer.parseInt(timer3ET2.getText().toString());
                    if (s > 59) {
                        s = 59;
                    }
                    timer3 = new CountDownTimer((m * 60 + s) * 1000, 1000) {
                        @Override
                        public void onFinish() {
                            timer3ET1.setText(timer3min);
                            timer3ET2.setText(timer3sec);
                            timerMP = MediaPlayer.create(RecipeActivity.this, R.raw.timer3);
                            timerMP.start();

                            timerPlayB3.setEnabled(true);
                            timerPauseB3.setEnabled(false);
                            timerResetB3.setEnabled(false);
                        }
                        @Override
                        public void onTick(long millisUntilFinished) {
                            NumberFormat f = new DecimalFormat("00");
                            long min = (millisUntilFinished / 60000) % 60;
                            long sec = (millisUntilFinished / 1000) % 60;
                            timer3ET1.setText(f.format(min));
                            timer3ET2.setText(f.format(sec));

                            if (min == 0) {
                                timerTV3.setTextColor(Color.RED);
                                timer3ET1.setTextColor(Color.RED);
                                timer3ET2.setTextColor(Color.RED);
                            } else {
                                timerTV3.setTextColor(Color.BLACK);
                                timer3ET1.setTextColor(Color.BLACK);
                                timer3ET2.setTextColor(Color.BLACK);
                            }
                        }
                    };
                    timer3.start();
                    timer3IsRunning = true;

                    timerPlayB3.setEnabled(false);
                    timerPauseB3.setEnabled(true);
                    timerResetB3.setEnabled(true);

                    timer3ET1.setEnabled(false);
                    timer3ET2.setEnabled(false);
                }
            }
        });
        timerPauseB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer3.cancel();
                timer3IsRunning = false;

                timerPauseB3.setEnabled(false);
                timerPlayB3.setEnabled(true);
                timerResetB3.setEnabled(true);

                timer3ET1.setEnabled(false);
                timer3ET2.setEnabled(false);
            }
        });
        timerResetB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer3.cancel();
                timer3IsRunning = false;

                timerResetB3.setEnabled(false);
                timerPauseB3.setEnabled(false);
                timerPlayB3.setEnabled(true);

                timerTV3.setTextColor(Color.BLACK);
                timer3ET1.setTextColor(Color.BLACK);
                timer3ET2.setTextColor(Color.BLACK);

                timer3ET1.setEnabled(true);
                timer3ET2.setEnabled(true);

                timer3ET1.setText(timer3min);
                timer3ET2.setText(timer3sec);
            }
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

                timerLL1.setVisibility(View.VISIBLE);
                timerLL2.setVisibility(View.VISIBLE);
                timerLL3.setVisibility(View.VISIBLE);
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
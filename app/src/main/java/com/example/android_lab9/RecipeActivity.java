package com.example.android_lab9;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class RecipeActivity extends AppCompatActivity {
    private TextView recipeTV, recipeTitleTV;
    private Button langB;
    private ImageView recipeIV;

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
                break;
            case 3:
                recipeTitleTV.setText(R.string.listview_item3);
                recipeTV.setText(R.string.recipe3);
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
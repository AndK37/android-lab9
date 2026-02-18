package com.example.android_lab9;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Spinner typeS;
    private ListView mainLV;
    private Button langB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ActivityResultLauncher<Intent> startActivity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            recreate();
        });

        langB = findViewById(R.id.langB);
        langB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (getString(R.string.lang_b)) {
                    case "EN":
                        setLocale(MainActivity.this, "ru");
                        break;
                    case "RU":
                        setLocale(MainActivity.this, "pl");
                        break;
                    case "PL":
                        setLocale(MainActivity.this, "en");
                        break;
                    default:
                        setLocale(MainActivity.this, "en");
                        break;
                }
                recreate();
            }
        });

        mainLV = findViewById(R.id.mainLV);

        typeS = findViewById(R.id.typeS);
        typeS.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                new String[]{getString(R.string.filter_item0), getString(R.string.filter_item1), getString(R.string.filter_item2), getString(R.string.filter_item3)}));
        typeS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mainLV.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                                new String[]{getString(R.string.listview_item1), getString(R.string.listview_item2), getString(R.string.listview_item3)}));
                        mainLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                switch (position) {
                                    case 0:
                                        startActivity.launch(new Intent(MainActivity.this, RecipeActivity.class)
                                                .putExtra("recipe", 1)
                                                .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
                                        break;
                                    case 1:
                                        startActivity.launch(new Intent(MainActivity.this, RecipeActivity.class)
                                                .putExtra("recipe", 2)
                                                .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
                                        break;
                                    case 2:
                                        startActivity.launch(new Intent(MainActivity.this, RecipeActivity.class)
                                                .putExtra("recipe", 3)
                                                .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
                                        break;
                                }
                            }
                        });
                        break;
                    case 1:
                        mainLV.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                                new String[]{getString(R.string.listview_item1)}));
                        mainLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                switch (position) {
                                    case 0:
                                        startActivity.launch(new Intent(MainActivity.this, RecipeActivity.class)
                                                .putExtra("recipe", 1)
                                                .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
                                        break;
                                }
                            }
                        });
                        break;
                    case 2:
                        mainLV.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                                new String[]{getString(R.string.listview_item2)}));
                        mainLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                switch (position) {
                                    case 0:
                                        startActivity.launch(new Intent(MainActivity.this, RecipeActivity.class)
                                                .putExtra("recipe", 2)
                                                .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
                                        break;
                                }
                            }
                        });
                        break;
                    case 3:
                        mainLV.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                                new String[]{getString(R.string.listview_item3)}));
                        mainLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                switch (position) {
                                    case 0:
                                        startActivity.launch(new Intent(MainActivity.this, RecipeActivity.class)
                                                .putExtra("recipe", 3)
                                                .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
                                        break;
                                }
                            }
                        });
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
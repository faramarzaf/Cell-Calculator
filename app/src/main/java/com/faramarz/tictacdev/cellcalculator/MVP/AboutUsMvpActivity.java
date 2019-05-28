package com.faramarz.tictacdev.cellcalculator.MVP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;

import com.faramarz.tictacdev.cellcalculator.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class AboutUsMvpActivity extends AppCompatActivity {

    Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_mvp);

        toolbar = findViewById(R.id.toolbar);
        collapsingToolbar = findViewById(R.id.collapsingToolbar);
        collapsingToolbar.setExpandedTitleColor(Color.WHITE);
        collapsingToolbar.setCollapsedTitleTextColor(Color.YELLOW);
        setSupportActionBar(toolbar);


    }
}

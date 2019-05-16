package com.faramarz.tictacdev.cellcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_ic50, btn_anova, btn_doublingTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        bind();

        btn_ic50.setOnClickListener(this);
        btn_anova.setOnClickListener(this);
        btn_doublingTime.setOnClickListener(this);


    }

    void bind() {
        btn_ic50 = findViewById(R.id.btn_ic50);
        btn_anova = findViewById(R.id.btn_anova);
        btn_doublingTime = findViewById(R.id.btn_doublingTime);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btn_ic50.getId()) {
            startActivity(new Intent(this, Ic50Activity.class));
        } else if (v.getId() == btn_anova.getId()) {
            startActivity(new Intent(this, AnovaActivity.class));
        } else if (v.getId() == btn_doublingTime.getId()) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }



}

package com.faramarz.tictacdev.cellcalculator;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class FirstActivity extends AppCompatActivity implements View.OnClickListener {
    Boolean backpressed = false;
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

    @Override
    public void onBackPressed() {
        if (backpressed) {
            finish();
        } else
            Toast.makeText(this, "Press again for exit", Toast.LENGTH_SHORT).show();
        backpressed = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backpressed = false;
            }
        }, 2000);
    }

}

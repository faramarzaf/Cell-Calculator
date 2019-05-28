package com.faramarz.tictacdev.cellcalculator.MVP.View_mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.faramarz.tictacdev.cellcalculator.R;

public class FirstMvpActivity extends AppCompatActivity implements View.OnClickListener {

    Boolean backpressed = false;
    Button btn_ic50, btn_anova, btn_doublingTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_mvp);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.aboutus) {
            startActivity(new Intent(FirstMvpActivity.this, AboutUsMvpActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btn_ic50.getId()) {
            startActivity(new Intent(this, Ic50MvpActivity.class));
        } else if (v.getId() == btn_anova.getId()) {
            startActivity(new Intent(this, AnovaMvpActivity.class));
        } else if (v.getId() == btn_doublingTime.getId()) {
            startActivity(new Intent(this, MainMvpActivity.class));
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

package com.faramarz.tictacdev.cellcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ExtendedEditText txt_initialConcentration, txt_finalConcentration, txt_duration;
    Button btn_compute_doubling_time, btn_clearAll;

    DoublingComputerTime doubligComputerTime = new DoublingComputerTime();
    TextView myresult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();

        setBackBtn();

        btn_compute_doubling_time.setOnClickListener(this);
        btn_clearAll.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    void setBackBtn() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    void bind() {
        txt_initialConcentration = findViewById(R.id.txt_initialConcentration);
        txt_finalConcentration = findViewById(R.id.txt_finalConcentration);
        txt_duration = findViewById(R.id.txt_duration);

        btn_compute_doubling_time = findViewById(R.id.btn_compute_doubling_time);
        btn_clearAll = findViewById(R.id.btn_clearAll);

        myresult = findViewById(R.id.myresult);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btn_compute_doubling_time.getId()) {
            if (txt_initialConcentration.length() == 0) {
                txt_initialConcentration.setError("Enter value");
            }
            if (txt_finalConcentration.length() == 0) {
                txt_finalConcentration.setError("Enter value");
            }
            if (txt_duration.length() == 0) {
                txt_duration.setError("Enter value");

            } else if (txt_initialConcentration.length() != 0 & txt_finalConcentration.length() != 0 & txt_duration.length() != 0) {
                String initial = txt_initialConcentration.getText().toString();
                String finall = txt_finalConcentration.getText().toString();
                String duration = txt_duration.getText().toString();
                final double result = doubligComputerTime.ComputeDoubling(Double.valueOf(initial), Double.valueOf(finall), Double.valueOf(duration));
                String result2 = String.format("%.2f", result);
                myresult.setText((result2));

            }
        } else if (v.getId() == btn_clearAll.getId()) {
            myresult.setText("");
            txt_duration.setText("");
            txt_finalConcentration.setText("");
            txt_initialConcentration.setText("");
        }

    }
}

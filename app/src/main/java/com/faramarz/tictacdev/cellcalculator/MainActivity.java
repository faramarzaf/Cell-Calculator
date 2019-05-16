package com.faramarz.tictacdev.cellcalculator;

import android.content.Intent;
import android.os.Handler;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.faramarz.tictacdev.cellcalculator.DataBase.DBHandler;
import com.faramarz.tictacdev.cellcalculator.Utils.HistoryModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;


public class MainActivity extends AppCompatActivity {

    ExtendedEditText txt_initialConcentration, txt_finalConcentration, txt_duration, txt_cell_description, txt_cell_title;
    Button btn_compute_doubling_time, btn_clearAll, showHistory, saveCell;

    DoublingComputerTime doubligComputerTime = new DoublingComputerTime();
    TextView myresult, dayDevice, day_week;



    String todaysPostDate;
    DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
        showDate();
        setBackBtn();

        dbHandler = new DBHandler(this);

        btn_compute_doubling_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

            }
        });

        saveCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_cell_title.getText().toString().isEmpty() & txt_cell_description.getText().toString().isEmpty()
                        & txt_finalConcentration.getText().toString().isEmpty() & txt_duration.getText().toString().isEmpty()
                        & txt_initialConcentration.getText().toString().isEmpty()) {
                    txt_initialConcentration.setError("Enter value");
                    txt_cell_title.setError("Enter value");
                    txt_duration.setError("Enter value");
                    txt_finalConcentration.setError("Enter value");
                    txt_cell_description.setError("Enter value");
                    return;

                } else if (txt_cell_title.getText().toString().isEmpty() & txt_cell_description.getText().toString().isEmpty()) {
                    return;

                } else if (txt_cell_title.getText().toString().isEmpty()) {
                    return;
                } else if (txt_cell_description.getText().toString().isEmpty()) {
                    return;
                } else if (!txt_cell_title.getText().toString().isEmpty() & !txt_cell_description.getText().toString().isEmpty())
                    dbHandler.open();
                HistoryModel historyModel = new HistoryModel();
                historyModel.setDescription((txt_cell_description.getText().toString()));
                historyModel.setTitle((txt_cell_title.getText()).toString());
                historyModel.setDoublingtime("DT : "+myresult.getText().toString());
                historyModel.setDate(todaysPostDate);
                dbHandler.addHistory(historyModel);
                dbHandler.close();
            }
        });
        showHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
        });

        btn_clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myresult.setText("");
                txt_duration.setText("");
                txt_finalConcentration.setText("");
                txt_initialConcentration.setText("");
            }
        });
    }

    @Override
    protected void onPause() {
        dbHandler.close();
        super.onPause();
    }

    @Override
    protected void onResume() {
        dbHandler.open();
        super.onResume();
    }

    public void showDate() {
        //day of week
        SimpleDateFormat dow = new SimpleDateFormat("EEE");
        Date dayName = new Date();
        String dayOfWeek = dow.format(dayName);
        day_week.setText(dayOfWeek);

        //day and month number
        SimpleDateFormat format = new SimpleDateFormat("dd / MM");
        Date monthAndDay = new Date();
        String monthandday = format.format(monthAndDay);
        dayDevice.setText(monthandday);

        //full date number
        SimpleDateFormat format1 = new SimpleDateFormat("YYYY / dd / MM");
        Date monthAndDay1 = new Date();
        todaysPostDate = format1.format(monthAndDay1);
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
        txt_cell_description = findViewById(R.id.txt_cell_description);
        txt_cell_title = findViewById(R.id.txt_cell_title);

        btn_compute_doubling_time = findViewById(R.id.btn_compute_doubling_time);
        btn_clearAll = findViewById(R.id.btn_clearAll);

        myresult = findViewById(R.id.myresult);

        showHistory = findViewById(R.id.showHistory);
        saveCell = findViewById(R.id.saveCell);

        dayDevice = findViewById(R.id.dayDevice);
        day_week = findViewById(R.id.day_week);
    }




}


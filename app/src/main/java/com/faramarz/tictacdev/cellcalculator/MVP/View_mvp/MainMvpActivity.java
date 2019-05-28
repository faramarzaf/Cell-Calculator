package com.faramarz.tictacdev.cellcalculator.MVP.View_mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.faramarz.tictacdev.cellcalculator.MVP.Contract;
import com.faramarz.tictacdev.cellcalculator.MVP.Model_mvp.Database.DBHandler;
import com.faramarz.tictacdev.cellcalculator.MVP.Model_mvp.HistoryModel;
import com.faramarz.tictacdev.cellcalculator.MVP.Presenter_mvp.Presenter;
import com.faramarz.tictacdev.cellcalculator.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.dmoral.toasty.Toasty;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class MainMvpActivity extends AppCompatActivity implements Contract.View, View.OnClickListener {

    ExtendedEditText txt_initialConcentration, txt_finalConcentration, txt_duration, txt_cell_description, txt_cell_title;
    Button btn_compute_doubling_time_mvp, btn_clearAll_mvp, saveCell_mvp;
    TextView myresult, dayDevice, day_week;
    String todaysPostDate;
    DBHandler dbHandler;

    Contract.Presenter presenter = new Presenter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mvp);
        presenter.attachView(this);
        bind();
        showDate();
        setBackBtn();

        btn_compute_doubling_time_mvp.setOnClickListener(this);
        btn_clearAll_mvp.setOnClickListener(this);
        saveCell_mvp.setOnClickListener(this);

        dbHandler = new DBHandler(this);
    }


    @Override
    public void onDoublingTimeReceived(double result) {
        String result2 = String.format("%.2f", result);
        myresult.setText((result2));


    }

    void bind() {

        txt_initialConcentration = findViewById(R.id.txt_initialConcentration);
        txt_finalConcentration = findViewById(R.id.txt_finalConcentration);
        txt_duration = findViewById(R.id.txt_duration);

        txt_cell_description = findViewById(R.id.txt_cell_description);
        txt_cell_title = findViewById(R.id.txt_cell_title);
        btn_compute_doubling_time_mvp = findViewById(R.id.btn_compute_doubling_time_mvp);

        btn_clearAll_mvp = findViewById(R.id.btn_clearAll_mvp);
        myresult = findViewById(R.id.myresult);
        saveCell_mvp = findViewById(R.id.saveCell_mvp);

        dayDevice = findViewById(R.id.dayDevice);
        day_week = findViewById(R.id.day_week);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_compute_doubling_time_mvp:

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

                    presenter.getDoublingTime(Double.valueOf(initial), Double.valueOf(finall), Double.valueOf(duration));
                    break;
                }

                    case R.id.btn_clearAll_mvp:

                        myresult.setText("");
                        txt_duration.setText("");
                        txt_finalConcentration.setText("");
                        txt_initialConcentration.setText("");
                        break;

                    case R.id.saveCell_mvp:


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
                        } else if (!txt_cell_title.getText().toString().isEmpty()) {

                            Toasty.success(MainMvpActivity.this, "Saved!", Toast.LENGTH_SHORT, true).show();
                            dbHandler.open();
                            HistoryModel historyModel = new HistoryModel();
                            historyModel.setDescription((txt_cell_description.getText().toString()));
                            historyModel.setTitle((txt_cell_title.getText()).toString());
                            historyModel.setDoublingtime("DT : " + myresult.getText().toString());
                            historyModel.setDate(todaysPostDate);
                            dbHandler.addHistory(historyModel);
                            dbHandler.close();

                            break;
                        }

                        default:break;
                }

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

    void setBackBtn() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
            finish();
        else if (item.getItemId() == R.id.history) {
            startActivity(new Intent(MainMvpActivity.this, ListMvpActivity.class));
        }
        return super.onOptionsItemSelected(item);
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

}

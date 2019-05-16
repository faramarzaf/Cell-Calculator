package com.faramarz.tictacdev.cellcalculator;

import android.content.DialogInterface;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;
import com.faramarz.tictacdev.cellcalculator.DataBase.DBHandler;
import com.faramarz.tictacdev.cellcalculator.Utils.HistoryModel;
import com.faramarz.tictacdev.cellcalculator.Utils.ListAdapter;






public class ListActivity extends AppCompatActivity {


    ListView listview;
    DBHandler dbHandler;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listview = findViewById(R.id.list);
        dbHandler = new DBHandler(this);
        generateList();
        setBackBtn();

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                        showAlertDialog("Delete this cell ?", "YES", "CANCEL", adapterView, i);



                return false;
            }
        });

        //findViewById(R.id.settingActivity).setOnClickListener(v -> {startActivity(new Intent(this, SettingActivity.class));});
    }
    public void showAlertDialog(String title, String yes, String cancel, final AdapterView<?> adapterView, final int i){
        new AlertDialog.Builder(ListActivity.this).setTitle(title)
                .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int pos) {
                        HistoryModel historyModel = (HistoryModel) adapterView.getItemAtPosition(i);
                        dbHandler.open();
                        dbHandler.deleteItem(historyModel.getId());
                        dbHandler.close();
                        generateList();
                    }
                }).setNeutralButton(cancel, null).show();
    }


    public void generateList() {
        dbHandler.open();
       List<HistoryModel> historyModels = dbHandler.getAllDiary();
        adapter = new ListAdapter(this, historyModels);
        listview.setAdapter(adapter);
        listview.deferNotifyDataSetChanged();
        dbHandler.close();
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

}

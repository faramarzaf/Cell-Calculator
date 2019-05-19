package com.faramarz.tictacdev.cellcalculator;

import android.content.DialogInterface;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import com.faramarz.tictacdev.cellcalculator.DataBase.DBHandler;
import com.faramarz.tictacdev.cellcalculator.DataBase.DBOpenHelper;
import com.faramarz.tictacdev.cellcalculator.Utils.HistoryModel;
import com.faramarz.tictacdev.cellcalculator.Utils.ListAdapter;

import es.dmoral.toasty.Toasty;

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


    }

    public void showAlertDialog(String title, String yes, String cancel, final AdapterView<?> adapterView, final int i) {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_all_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
            finish();
        else if (id == R.id.deleteAll) {
            showDeleteAllDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDeleteAllDialog() {
        new AlertDialog.Builder(ListActivity.this).setTitle("Delete All History?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearData();
                generateList();
                Toasty.success(getApplicationContext(), "All cells removed!", Toasty.LENGTH_SHORT).show();

            }
        }).setNeutralButton("Cancel", null).show();
    }

    void clearData() {
        DBHandler dbHandler = new DBHandler(getApplicationContext());
        dbHandler.deleteAll();
    }


    void setBackBtn() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

}

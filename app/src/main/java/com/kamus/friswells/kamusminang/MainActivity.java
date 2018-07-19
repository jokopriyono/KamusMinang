package com.kamus.friswells.kamusminang;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kamus.friswells.kamusminang.database.KamusDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog loading;
    private ListView listData;
    private List<String> dataKamus;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listData = findViewById(R.id.listKata);

        munculLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                KamusDB kamusDB = KamusDB.getInstance(MainActivity.this);
                kamusDB.open();
                dataKamus = kamusDB.getQuotes();
                kamusDB.close();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hilangLoading();
                        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dataKamus);
                        listData.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }

    private void munculLoading(){
        if (loading==null){
            loading = new ProgressDialog(this);
            loading.setMessage("Mengambil semua data dari database..");
            loading.setCancelable(false);
            loading.show();
        }
    }

    private void hilangLoading(){
        if (loading!=null){
            loading.dismiss();
            loading = null;
        }
    }
}

package com.kamus.friswells.kamusminang;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.kamus.friswells.kamusminang.database.KamusDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressLint("UseSparseArrays")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog loading;
    private List<String> dataKamus;
    private ArrayAdapter<String> adapter;
    private Spinner spinBahasa;
    private EditText edtCari;
    private HashMap<Integer, String> bahasaMinang = new HashMap<>();
    private HashMap<Integer, String> bahasaInggris = new HashMap<>();
    private HashMap<Integer, String> bahasaIndo = new HashMap<>();
    private Bundle mSavedInstanceState;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mSavedInstanceState = savedInstanceState;

        Button btnCari = findViewById(R.id.btn_cari);
        spinBahasa = findViewById(R.id.spinner);
        edtCari = findViewById(R.id.edt_search);
        recycler = findViewById(R.id.recycler_kamus);
        recycler.hasFixedSize();
        recycler.setLayoutManager(new LinearLayoutManager(this));
        btnCari.setOnClickListener(this);

        munculLoading();
        spinBahasa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int pos, long l) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            KamusDB kamusDB = KamusDB.getInstance(MainActivity.this);
                            kamusDB.open();
                            switch (pos){
                                case 0:
                                    if (mSavedInstanceState==null) {
                                        bahasaIndo = kamusDB.getBahasaIndo();
                                        dataKamus = kamusDB.getQuotes();
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                ListKataAdapter adapterKata = new ListKataAdapter(MainActivity.this,
                                                        dataKamus, ListKataAdapter.B_INDO);
                                                recycler.setAdapter(adapterKata);
                                            }
                                        });
                                        mSavedInstanceState = new Bundle();
                                    } else {
                                        bahasaIndo = kamusDB.getBahasaIndo();
                                    }
                                    break;
                                case 1:
                                    bahasaMinang = kamusDB.getBahasaMinang();
                                    break;
                                default:
                                    bahasaInggris = kamusDB.getBahasaInggris();
                                    break;
                            }
                            kamusDB.close();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    hilangLoading();
                                }
                            });
                        }
                    }).start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void munculLoading(){
        if (loading==null){
            loading = new ProgressDialog(this);
            loading.setMessage("Mengambil data dari database..");
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_cari:
                String cari = edtCari.getText().toString().trim();

                if (spinBahasa.getSelectedItemPosition() == 0){
                    dataKamus = new ArrayList<>();
                    for (int key : bahasaIndo.keySet()){
                        if (bahasaIndo.get(key).contains(cari)){
                            dataKamus.add(bahasaIndo.get(key));
                        }
                    }
                    ListKataAdapter adapterKata = new ListKataAdapter(this, dataKamus, ListKataAdapter.B_INDO);
                    recycler.setAdapter(adapterKata);
//                    adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dataKamus);
//                    listData.setAdapter(adapter);
                } else if (spinBahasa.getSelectedItemPosition() == 1){
                    dataKamus = new ArrayList<>();
                    for (int key : bahasaMinang.keySet()){
                        if (bahasaMinang.get(key).contains(cari)){
                            dataKamus.add(bahasaMinang.get(key));
                        }
                    }
                    ListKataAdapter adapterKata = new ListKataAdapter(this, dataKamus, ListKataAdapter.B_MINANG);
                    recycler.setAdapter(adapterKata);
                } else {
                    dataKamus = new ArrayList<>();
                    for (int key : bahasaInggris.keySet()){
                        if (bahasaInggris.get(key).contains(cari)){
                            dataKamus.add(bahasaInggris.get(key));
                        }
                    }
                    ListKataAdapter adapterKata = new ListKataAdapter(this, dataKamus, ListKataAdapter.B_INGGRIS);
                    recycler.setAdapter(adapterKata);
                }
                break;
        }
    }
}

package com.kamus.friswells.kamusminang;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.kamus.friswells.kamusminang.adapter.ListKataAdapter;
import com.kamus.friswells.kamusminang.database.KamusDB;

import java.util.HashMap;

@SuppressLint("UseSparseArrays")
public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private ProgressDialog loading;
    private Spinner spinBahasa;
    private EditText edtCari;
    private HashMap<Integer, String> bahasaMinang = new HashMap<>();
    private HashMap<Integer, String> bahasaInggris = new HashMap<>();
    private HashMap<Integer, String> bahasaIndo = new HashMap<>();
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCari = findViewById(R.id.btn_cari);
        spinBahasa = findViewById(R.id.spinner);
        edtCari = findViewById(R.id.edt_search);
        recycler = findViewById(R.id.recycler_kamus);

        recycler.hasFixedSize();
        recycler.setLayoutManager(new LinearLayoutManager(this));
        btnCari.setOnClickListener(this);
        spinBahasa.setOnItemSelectedListener(this);
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

                HashMap<Integer, String> bahasaDitemukan = new HashMap<>();
                if (spinBahasa.getSelectedItemPosition() == 0){
                    for (int key : bahasaIndo.keySet()){
                        if (bahasaIndo.get(key).contains(cari)){
                            bahasaDitemukan.put(key, bahasaIndo.get(key));
                        }
                    }
                } else if (spinBahasa.getSelectedItemPosition() == 1){
                    for (int key : bahasaMinang.keySet()){
                        if (bahasaMinang.get(key).contains(cari)){
                            bahasaDitemukan.put(key, bahasaMinang.get(key));
                        }
                    }
                } else {
                    for (int key : bahasaInggris.keySet()){
                        if (bahasaInggris.get(key).contains(cari)){
                            bahasaDitemukan.put(key, bahasaInggris.get(key));
                        }
                    }
                }
                ListKataAdapter adapterKata = new ListKataAdapter(this, bahasaDitemukan);
                recycler.setAdapter(adapterKata);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, final int pos, long l) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        munculLoading();
                    }
                });
                KamusDB kamusDB = KamusDB.getInstance(MainActivity.this);
                kamusDB.open();
                switch (pos){
                    case 0:
                        bahasaIndo = kamusDB.getBahasaIndo();
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
}

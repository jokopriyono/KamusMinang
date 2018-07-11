package com.kamus.friswells.kamusminang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String urladdress = "http://friswells.000webhostapp.com/";
    String[] bhs_minang;
    String[] bhs_indo;
    String[] bhs_ingg;
    ListView listKata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

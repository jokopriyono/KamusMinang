package com.kamus.friswells.kamusminang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kamus.friswells.kamusminang.R;
import com.kamus.friswells.kamusminang.database.KamusDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListKataAdapter extends RecyclerView.Adapter<ListKataAdapter.ViewHolder>{

    private HashMap<Integer, String> data;
    private List<Integer> urutan;
    public final static int B_INDO = 1;
    public final static int B_MINANG = 2;
    public final static int B_INGGRIS = 3;
    private final KamusDB database;

    public ListKataAdapter(Context mContext, HashMap<Integer, String> dataKamus) {
        data = dataKamus;
        urutan = new ArrayList<>();
        urutan.addAll(data.keySet());
        database = KamusDB.getInstance(mContext);
        database.open();
    }

    @Override
    public ListKataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item_kata, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListKataAdapter.ViewHolder holder, int position) {
        String bIndo = database.cariBahasa(urutan.get(position), B_INDO);
        String bMinang = database.cariBahasa(urutan.get(position), B_MINANG);
        String bInggris = database.cariBahasa(urutan.get(position), B_INGGRIS);
        String jenisBahasa = database.cariJenisBahasa(urutan.get(position));

        holder.txtJenisKata.setText(jenisBahasa);
        holder.txtIndo.setText(bIndo);
        holder.txtMinang.setText(bMinang);
        holder.txtInggris.setText(bInggris);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtIndo, txtMinang, txtInggris, txtJenisKata;

        ViewHolder(final View view) {
            super(view);
            txtIndo = view.findViewById(R.id.txt_kata_indo);
            txtMinang = view.findViewById(R.id.txt_kata_minang);
            txtInggris = view.findViewById(R.id.txt_kata_inggris);
            txtJenisKata = view.findViewById(R.id.txt_jenis);
        }
    }
}

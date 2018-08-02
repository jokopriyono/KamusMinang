package com.kamus.friswells.kamusminang;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by friswell on 30/05/2018.
 */

public class ListKataAdapter extends RecyclerView.Adapter<ListKataAdapter.ViewHolder>{

    private Context mContext;
    private List<String> data;
    private int bahasa = 1;
    public final static int B_INDO = 1;
    public final static int B_MINANG = 2;
    public final static int B_INGGRIS = 3;

    public ListKataAdapter(Context mContext, List<String> dataKamus, int jenisKata) {
        this.mContext = mContext;
        data = dataKamus;
        bahasa = jenisKata;
    }

    @Override
    public ListKataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item_kata, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListKataAdapter.ViewHolder holder, int position) {
        holder.txtJenisKata.setText("-");
        switch (bahasa){
            case B_INDO:
                holder.txtMinang.setText("-");
                holder.txtInggris.setText("-");
                holder.txtIndo.setText(data.get(position));
                break;
            case B_MINANG:
                holder.txtIndo.setText("-");
                holder.txtInggris.setText("-");
                holder.txtMinang.setText(data.get(position));
                break;
            case B_INGGRIS:
                holder.txtIndo.setText("-");
                holder.txtMinang.setText("-");
                holder.txtInggris.setText(data.get(position));
                break;
        }
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

package com.kamus.friswells.kamusminang;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by friswell on 30/05/2018.
 */

public class CustomAdapter extends BaseAdapter{

    private Context mContext;
    private List<Data> mData;

    public CustomAdapter(Context mContext, List<Data> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext, R.layout.custom_list, null);
        TextView bhs_satu = (TextView) v.findViewById (R.id.bhs_satu);
        TextView bhs_dua = (TextView) v.findViewById(R.id.bhs_dua);
        TextView bhs_tiga =(TextView) v.findViewById(R.id.bhs_tiga);
        TextView bhs_empat = (TextView) v.findViewById(R.id.bhs_empat);

        TextView txt_satu = (TextView) v.findViewById (R.id.txt_satu);
        TextView txt_dua = (TextView) v.findViewById(R.id.txt_dua);
        TextView txt_tiga =(TextView) v.findViewById(R.id.txt_tiga);
        TextView txt_empat = (TextView) v.findViewById(R.id.txt_empat);

        txt_satu.setText(mData.get(position).getB_indo());
        txt_dua.setText(mData.get(position).getB_minang());
        txt_tiga.setText(mData.get(position).getB_ingg());

        return null;
    }
}

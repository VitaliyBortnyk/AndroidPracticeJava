package com.example.store;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AvailableGoodsAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Goods> list = new ArrayList<>();

    public AvailableGoodsAdapter(Context context, ArrayList<Goods> list) {
        this.context = context;
        this.list = list;

        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount () {
        return list.size();
    }

    @Override
    public Object getItem (int position) {
        return list.get(position);
    }

    @Override
    public long getItemId (int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView (int position, View convertView, ViewGroup parent) {

        View view = convertView;

        Goods g = getProduct(position);

        if (g.isAvailability()) {
            view = layoutInflater.inflate(R.layout.available_item, parent, false);
        } else {
            view = layoutInflater.inflate(R.layout.not_available_item, parent, false);
        }

        ((TextView) view.findViewById(R.id.textView1)).setText(g.getName());
        ((TextView) view.findViewById(R.id.textView2)).setText("Ціна: " + g.getPrice());
        ((TextView) view.findViewById(R.id.textView3)).setText("Кількість наявних: " + g.getNumber());

        return view;
    }

    private Goods getProduct (int position) {
        return (Goods) getItem(position);
    }
}

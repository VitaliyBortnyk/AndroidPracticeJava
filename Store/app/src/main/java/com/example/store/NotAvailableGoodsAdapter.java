package com.example.store;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NotAvailableGoodsAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Goods> list;
    ArrayList<Goods> notAvailable = new ArrayList<>();

    public NotAvailableGoodsAdapter(Context context, ArrayList<Goods> list) {
        this.context = context;
        this.list = list;

        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        filterItems();
    }

    private void filterItems () {
        for (Goods g: list) {
            if (!g.isAvailability()) {
                this.notAvailable.add(g);
            }
        }
    }

    @Override
    public int getCount () {
        return notAvailable.size();
    }

    @Override
    public Object getItem (int position) {
        return notAvailable.get(position);
    }

    @Override
    public long getItemId (int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView (int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.available_item, parent, false);
        }

        Goods g = getProduct(position);

        ((TextView) view.findViewById(R.id.textView1)).setText(g.getName());
        ((TextView) view.findViewById(R.id.textView2)).setText("Ціна: " + g.getPrice());
        ((TextView) view.findViewById(R.id.textView3)).setText("В наявності: " + g.isAvailability());

        return view;
    }

    private Goods getProduct (int position) {
        return (Goods) getItem(position);
    }
}

package com.example.store;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<Goods> goodsArrayList;
    private AvailableGoodsAdapter availableGoods;
    private NotAvailableGoodsAdapter notAvailableGoods;
    private String filename = "data.txt";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goodsArrayList = new ArrayList<>();

        availableGoods = new AvailableGoodsAdapter(this, goodsArrayList);
        ListView availableListView = findViewById(R.id.listView);

        availableListView.setAdapter(availableGoods);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GoodsAdd.class);
            startActivity(intent);
        });

        availableListView.setOnItemClickListener((parent, view, position, id) -> {

            Intent intent = new Intent(MainActivity.this, GoodsAdd.class);
            intent.putExtra("Goods", position);
            startActivity(intent);
        });

        try {
            deserialize();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume () {
        super.onResume();

        updateListView();
    }

    private void updateListView () {
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(availableGoods);
    }

    public static ArrayList<Goods> getGoodsList () {
        return goodsArrayList;
    }
    public static void setGoodsList (ArrayList<Goods> goodsArrayList) {
        MainActivity.goodsArrayList = goodsArrayList;
    }

    public void onSearchButtonClick (View view) {
        EditText searchET = findViewById(R.id.searchET);
        ArrayList<Goods> result;
        ListView listView = findViewById(R.id.listView);

        if (!String.valueOf(searchET.getText()).isEmpty()) {
            result = getSearch(String.valueOf(searchET.getText()));

            availableGoods = new AvailableGoodsAdapter(this, result);
            listView.setAdapter(availableGoods);
            availableGoods.notifyDataSetChanged();
        } else {
            availableGoods = new AvailableGoodsAdapter(this, goodsArrayList);
            listView.setAdapter(availableGoods);
        }
    }

    private ArrayList<Goods> getSearch (String category) {
        ArrayList<Goods> result = new ArrayList<>();

        for (Goods g: goodsArrayList) {
            if (g.getCategory().equals(category)) {
                result.add(g);
            }
        }
        return result;
    }

    public void onSaveButtonClick (View view) throws IOException {
        FileOutputStream fos = getBaseContext().openFileOutput(filename, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(goodsArrayList);
        os.close();
        fos.close();

        availableGoods.notifyDataSetChanged();

        Toast.makeText(getApplicationContext(), "Записи збережено", Toast.LENGTH_SHORT).show();
    }

    public void onLoadButtonClick (View view) throws IOException, ClassNotFoundException {
        deserialize();
    }

    private void deserialize () throws IOException, ClassNotFoundException {
        FileInputStream fis = getBaseContext().openFileInput(filename);
        ObjectInputStream is = new ObjectInputStream(fis);
        goodsArrayList = (ArrayList<Goods>) is.readObject();
        is.close();
        fis.close();

        ListView listView = findViewById(R.id.listView);
        availableGoods = new AvailableGoodsAdapter(this, goodsArrayList);
        listView.setAdapter(availableGoods);
        availableGoods.notifyDataSetChanged();

        Toast.makeText(getApplicationContext(), "Записи зчитано", Toast.LENGTH_SHORT).show();
    }

    public void onCheckBoxClicked (View view) {
        CheckBox availableCheckBox = findViewById(R.id.availableCB);
        CheckBox notAvailableCheckBox = findViewById(R.id.notAvailable);
        EditText category = findViewById(R.id.searchET);

        ListView lv = findViewById(R.id.listView);
        ArrayList<Goods> result = new ArrayList<>();

        if (availableCheckBox.isChecked() && notAvailableCheckBox.isChecked()) {
            result = goodsArrayList;
        } else if (!availableCheckBox.isChecked() && !notAvailableCheckBox.isChecked()) {
            for (Goods g: goodsArrayList){

                if (g.isAvailability()) {
                    result.add(g);
                }
            }
        } else if (!availableCheckBox.isChecked() && notAvailableCheckBox.isChecked()) {
            for (Goods g: goodsArrayList){

                if (!g.isAvailability()) {
                    result.add(g);
                }
            }
        }

        availableGoods = new AvailableGoodsAdapter(this, result);
        lv.setAdapter(availableGoods);
        availableGoods.notifyDataSetChanged();
    }
}
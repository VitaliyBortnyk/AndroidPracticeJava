package com.example.store;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class GoodsAdd extends AppCompatActivity {

    private EditText nameET;
    private EditText categoryET;
    private EditText descriptionET;
    private EditText priceET;
    private CheckBox availability;
    private EditText numberET;
    private EditText deliveryDateET;
    private Button addBTN;

    Button deleteButton;
    int position;

    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

    Bundle arguments;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_add);

        nameET =  findViewById(R.id.nameET);
        categoryET = findViewById(R.id.categoryET);
        descriptionET = findViewById(R.id.descriptionET);
        priceET = findViewById(R.id.priceET);
        availability = findViewById(R.id.checkBox);
        numberET = findViewById(R.id.numberET);
        deliveryDateET = findViewById(R.id.deliveryDateET);

        addBTN = findViewById(R.id.addBTN);

        myCalendar = Calendar.getInstance();

        date = (view, year, monthOfYear, dayOfMonth) -> {

            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        arguments = getIntent().getExtras();

        if (arguments != null) {
            position = (int) arguments.get("Goods");

            Goods goods = MainActivity.getGoodsList().get(position);
            nameET.setText(String.valueOf(goods.getName()));
            categoryET.setText(String.valueOf(goods.getCategory()));
            descriptionET.setText(String.valueOf(goods.getDescription()));
            priceET.setText(String.valueOf(goods.getPrice()));
            availability.setChecked(goods.isAvailability());
            numberET.setText(String.valueOf(goods.getNumber()));
            deliveryDateET.setText(goods.getStringOfDate());

            deleteButton = findViewById(R.id.delBTN);
            deleteButton.setVisibility(View.VISIBLE);

            TextView header = findViewById(R.id.addGoodsTV);
            header.setText("Редагувати запис");

            addBTN.setText("Редагувати");


            if (availability.isChecked()) {
                numberET.setVisibility(View.INVISIBLE);
                deliveryDateET.setVisibility(View.INVISIBLE);
            } else {
                numberET.setVisibility(View.VISIBLE);
                deliveryDateET.setVisibility(View.VISIBLE);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClickListener (View view) {
        DatePickerDialog datePicker = new DatePickerDialog(
                GoodsAdd.this,
                date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
        );

        datePicker.getDatePicker().setMaxDate(new Date().getTime());
        datePicker.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        deliveryDateET.setText(sdf.format(myCalendar.getTime()));
    }

    public void onCheckBoxClicked (View view) {
        if (((CheckBox) view).isChecked()) {
            numberET.setVisibility(View.VISIBLE);
            deliveryDateET.setVisibility(View.VISIBLE);
        } else {
            numberET.setVisibility(View.INVISIBLE);
            deliveryDateET.setVisibility(View.INVISIBLE);
        }
    }

    private boolean validateFields () {
        if (!String.valueOf(nameET.getText()).isEmpty()
                && !String.valueOf(categoryET.getText()).isEmpty()
                && !String.valueOf(descriptionET.getText()).isEmpty()
                && !String.valueOf(priceET.getText()).isEmpty()
                && !String.valueOf(numberET.getText()).isEmpty()) {
            if (availability.isChecked()) {
                return true;
            } else {
                return !String.valueOf(numberET.getText()).isEmpty()
                        && !String.valueOf(deliveryDateET.getText()).isEmpty();
            }
        } else {
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onAddButtonClicked (View view) {

        if (validateFields()) {
            GregorianCalendar calendar = null;

            if (!availability.isChecked()) {
                String[] date = String.valueOf(deliveryDateET.getText()).split("/");
                calendar = new GregorianCalendar();

                calendar.set(Calendar.YEAR, Integer.parseInt(date[2]));
                calendar.set(Calendar.MONTH, Integer.parseInt(date[1]));
                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[0]));
            }

            Goods goods = new Goods(
                    String.valueOf(nameET.getText()),
                    String.valueOf(categoryET.getText()),
                    String.valueOf(descriptionET.getText()),
                    Double.parseDouble(String.valueOf(priceET.getText())),
                    availability.isChecked(),
                    availability.isChecked() ? 0 : Integer.parseInt(String.valueOf(numberET.getText())),
                    availability.isChecked() ? null: calendar);

            if (arguments != null) {
                MainActivity.getGoodsList().get(position).Edit(goods);
                Toast.makeText(getApplicationContext(), "Товар відредаговано!", Toast.LENGTH_SHORT).show();
            } else {
                MainActivity.getGoodsList().add(goods);
                Toast.makeText(getApplicationContext(), "Товар додано!", Toast.LENGTH_SHORT).show();
            }

            nameET.setText("");
            categoryET.setText("");
            descriptionET.setText("");
            priceET.setText("");
            availability.setChecked(true);
            numberET.setText("");
            deliveryDateET.setText("");
            numberET.setVisibility(View.INVISIBLE);
            deliveryDateET.setVisibility(View.INVISIBLE);

            finish();
        }
    }

    public void onDeleteButtonClick (View view) {
        MainActivity.getGoodsList().remove(position);
        Toast.makeText(getApplicationContext(), "Товар видалено", Toast.LENGTH_SHORT).show();

        finish();
    }
}
package com.example.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private boolean operator = true;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
        display.setCursorVisible(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.display).equals(display.getText().toString()))
                {
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightSrt = oldStr.substring(cursorPos);

        if("Помилка".equals(display.getText().toString()))
        {
            display.setText("");
            display.setText(strToAdd);
        }
        else if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }
        else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightSrt));
            display.setSelection(cursorPos + 1);
        }
    }

    public void zeroBTN(View view) { updateText("0"); operator = false;}
    public void oneBTN(View view) { updateText("1"); operator = false;}
    public void twoBTN(View view) { updateText("2"); operator = false;}
    public void threeBTN(View view) { updateText("3"); operator = false;}
    public void fourBTN(View view) { updateText("4"); operator = false;}
    public void fiveBTN(View view) { updateText("5"); operator = false;}
    public void sixBTN(View view) { updateText("6"); operator = false;}
    public void sevenBTN(View view) { updateText("7"); operator = false;}
    public void eightBTN(View view) { updateText("8"); operator = false;}
    public void nineBTN(View view) { updateText("9"); operator = false;}
    public void interestBTN(View view) {
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");
        userExp = userExp.replaceAll("e", "2.71828");

        if(operator) {
            userExp = userExp.substring(0, userExp.length() - 1);
        }
        userExp = userExp + "*0.01";
        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());
        if(result.equals("NaN") || result.equals("Infinity"))
            result = "Помилка";
        display.setText(result);
        display.setSelection(result.length());
        operator = false;
    }
    public void piBTN(View view) {
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");
        userExp = userExp.replaceAll("e", "2.71828");

        if(operator) {
            userExp = userExp.substring(0, userExp.length() - 1);
        }
        userExp = "Pi(" + userExp + ")";
        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());
        if(result.equals("NaN") || result.equals("Infinity"))
            result = "Помилка";
        display.setText(result);
        display.setSelection(result.length());
        operator = false;
    }
    public void sinBTN(View view) {
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");
        userExp = userExp.replaceAll("e", "2.71828");

        if(operator) {
            userExp = userExp.substring(0, userExp.length() - 1);
        }
        userExp = "sin(" + userExp + ")";
        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());
        if(result.equals("NaN") || result.equals("Infinity"))
            result = "Помилка";
        display.setText(result);
        display.setSelection(result.length());
    }
    public void cosBTN(View view) {
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");
        userExp = userExp.replaceAll("e", "2.71828");

        if(operator) {
            userExp = userExp.substring(0, userExp.length() - 1);
        }
        userExp = "cos(" + userExp + ")";
        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());
        if(result.equals("NaN") || result.equals("Infinity"))
            result = "Помилка";
        display.setText(result);
        display.setSelection(result.length());
    }
    public void tanBTN(View view) {
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");
        userExp = userExp.replaceAll("e", "2.71828");

        if(operator) {
            userExp = userExp.substring(0, userExp.length() - 1);
        }
        userExp = "tan(" + userExp + ")";
        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());
        if(result.equals("NaN") || result.equals("Infinity"))
            result = "Помилка";
        display.setText(result);
        display.setSelection(result.length());
    }
    public void lnBTN(View view) {
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");
        userExp = userExp.replaceAll("e", "2.71828");

        if(operator) {
            userExp = userExp.substring(0, userExp.length() - 1);
        }
        userExp = "ln(" + userExp + ")";
        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());
        if(result.equals("NaN") || result.equals("Infinity"))
            result = "Помилка";
        display.setText(result);
        display.setSelection(result.length());
        operator = false;
    }
    public void lgBTN(View view) {
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");
        userExp = userExp.replaceAll("e", "2.71828");

        if(operator) {
            userExp = userExp.substring(0, userExp.length() - 1);
        }
        userExp = "log10(" + userExp + ")";
        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());
        if(result.equals("NaN") || result.equals("Infinity"))
            result = "Помилка";
        display.setText(result);
        display.setSelection(result.length());
        operator = false;
    }
    public void eBTN(View view) { updateText("e"); operator = false;}
    public void sqrtBTN(View view) {  String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");
        userExp = userExp.replaceAll("e", "2.71828");

        if(operator) {
            userExp = userExp.substring(0, userExp.length() - 1);
        }
        userExp = "sqrt(" + userExp + ")";
        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());
        if(result.equals("NaN") || result.equals("Infinity"))
            result = "Помилка";
        display.setText(result);
        display.setSelection(result.length());
        operator = false; }
    public void exponent2BTN(View view) {
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");
        userExp = userExp.replaceAll("e", "2.71828");

        if(operator) {
            userExp = userExp.substring(0, userExp.length() - 1);
        }
        userExp = userExp + "^2";
        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());
        if(result.equals("NaN") || result.equals("Infinity"))
            result = "Помилка";
        display.setText(result);
        display.setSelection(result.length());
        operator = false;
    }
    public void clearBTN(View view) { display.setText("0"); }
    public void exponentBTN(View view) {
        if (display.getText().toString().isEmpty() || operator) {
            return;
        } else {
            updateText("^");
            operator = true;
        }
    }
    public void paranthesesBTN(View view) {
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closePar = 0;
        int textLen  = display.getText().length();

        for(int i = 0; i < cursorPos; i++){
            if (display.getText().toString().substring(i, i + 1).equals("("))
                openPar += 1;
            if (display.getText().toString().substring(i, i + 1).equals(")"))
                closePar += 1;
        }
        
        if (openPar == closePar || display.getText().toString().substring(textLen - 1, textLen).equals("(")) {
            updateText("(");
            display.setSelection(cursorPos + 1);
        }
        else if (closePar < openPar && !display.getText().toString().substring(textLen - 1, textLen).equals("(")) {
            updateText(")");
        }
        display.setSelection(cursorPos + 1);
    }
    public void divideBTN(View view) {
        if (display.getText().toString().isEmpty() || operator) {
            return;
        } else {
            updateText("÷");
            operator = true;
        }
    }
    public void multiplyBTN(View view) {
        if (display.getText().toString().isEmpty() || operator) {
            return;
        } else {
            updateText("×");
            operator = true;
        }
    }
    public void addBTN(View view) {
        if (display.getText().toString().isEmpty() || operator) {
            return;
        } else {
            updateText("+");
            operator = true;

        }
    }
    public void subtractBTN(View view) {
        if (display.getText().toString().isEmpty() || operator) {
            return;
        } else {
            updateText("-");
            operator = true;
        }
    }
    public void pointBTN(View view) {

        updateText(".");
    }
    public void equalsBTN(View view) {
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");
        userExp = userExp.replaceAll("e", "2.71828");

        if(operator) {
            userExp = userExp.substring(0, userExp.length() - 1);
        }
            Expression exp = new Expression(userExp);

            String result = String.valueOf(exp.calculate());
                if(result.equals("NaN") || result.equals("Infinity"))
                    result = "Помилка";
            display.setText(result);
            display.setSelection(result.length());
            operator = false;

    }
    public void backspaceBTN(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos !=0 && textLen != 0)
        {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
        operator = false;
    }
}
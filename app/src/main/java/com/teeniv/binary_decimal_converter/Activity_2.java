package com.teeniv.binary_decimal_converter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class Activity_2 extends AppCompatActivity {
    EditText txt3;
    TextView txt4;
    Button btn5, btn6, btn7, btn8;
    Toolbar toolbar2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        toolbar2 = findViewById(R.id.toolbar2);

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String decimal = txt3.getText().toString();
                if(decimal.isEmpty())
                {
                    txt3.setText("");
                }
                int decimalNumber = Integer.parseInt(decimal);
                String binary = DecToBin(decimalNumber);
                txt4.setText(binary);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String decimalString = txt3.getText().toString();
                if (!decimalString.isEmpty()) {
                    int decimalNumber = Integer.parseInt(decimalString);
                    String octalNumber = Integer.toOctalString(decimalNumber);
                    txt4.setText(octalNumber);
                } else {
                    txt4.setText("Please enter a decimal number.");
                }
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String decimalString = txt3.getText().toString();
                if (!decimalString.isEmpty()) {
                    int decimalNumber = Integer.parseInt(decimalString);
                    String hexNumber = Integer.toHexString(decimalNumber);
                    txt4.setText(hexNumber.toUpperCase());
                } else {
                    txt4.setText("Please enter a decimal number.");
                }
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_2.this,MainActivity.class);
                startActivity(i);
            }
        });

        //Step 1.
        setSupportActionBar(toolbar2);

        //Step2.
        if(getSupportActionBar()!= null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Decimal Converter");
        }

        //Step 3.
        toolbar2.setSubtitle("38");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemid = item.getItemId();

        if(itemid==R.id.copy)
        {
            TextView a = (TextView) findViewById(R.id.txt4);
            ClipboardManager clipboard = (ClipboardManager)
                    getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label",a.getText().toString());
            clipboard.setPrimaryClip(clip);

            Toast.makeText(this, "Your Text has been copied successfully", Toast.LENGTH_SHORT).show();
        }

        // For taking a screenshot
        else if (itemid==R.id.save)
        {
            takess();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void takess()
    {
        View rootView = getWindow().getDecorView().findViewById(R.id.save);
        Bitmap bitmap = getss(rootView);
    }

    private Bitmap getss(View view)
    {
        if(view == null)
        {
            return null;
        }
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmap;

    }

    private String DecToBin(int decimal)
    {
        return Integer.toBinaryString(decimal);
    }


}

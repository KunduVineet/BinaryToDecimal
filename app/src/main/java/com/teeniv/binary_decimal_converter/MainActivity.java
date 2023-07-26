package com.teeniv.binary_decimal_converter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
EditText txt1;
View txt2;
Button btn1,btn2,btn3,btn4;
Toolbar toolbar;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt1 = findViewById(R.id.txt1);
        TextView txt2 = findViewById(R.id.txt2);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        toolbar = findViewById(R.id.toolbar);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String binaryString = txt1.getText().toString();
                // Input validation - Check if the input string matches the binary pattern
                if (!binaryString.matches("[01]+")) {
                    Toast.makeText(MainActivity.this, "Invalid binary input", Toast.LENGTH_SHORT).show();
                    return;
                }
                int DecimalInt = Integer.parseInt(binaryString, 2);
                String decimlString = String.valueOf(DecimalInt);

                 txt2.setText(decimlString);

                return ;
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Binary = txt1.getText().toString();
                // Input validation - Check if the input string matches the binary pattern
                if (!Binary.matches("[01]+")) {
                    Toast.makeText(MainActivity.this, "Invalid binary input", Toast.LENGTH_SHORT).show();
                    return ;
                }

                String octalString = BinToOct(Binary);

                txt2.setText(octalString);
                return ;
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Binary = txt1.getText().toString();
                // Input validation - Check if the input string matches the binary pattern
                if (!Binary.matches("[01]+")) {
                    Toast.makeText(MainActivity.this, "Invalid binary input", Toast.LENGTH_SHORT).show();
                    return ;
                }

                String hexa = binToHex(Binary);
                txt2.setText(hexa.toUpperCase());
                return ;

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Activity_2.class);
                startActivity(i);
            }
        });




        //Step 1.
        setSupportActionBar(toolbar);

        //Step2.
        if(getSupportActionBar()!= null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Binary Converter");
        }

        //Step 3.
        toolbar.setSubtitle("0100110");
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
           TextView a = (TextView) findViewById(R.id.txt2);
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

    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;


    private void takess() {
        View rootView = getWindow().getDecorView().findViewById(R.id.save);
        Bitmap bitmap = getss(rootView);

        if (bitmap != null) {
            String fileName = "screenshot.png";
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");

            ContentResolver resolver = getContentResolver();
            Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            try {
                OutputStream outputStream = resolver.openOutputStream(imageUri);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                if (outputStream != null) {
                    outputStream.close();
                    Toast.makeText(this, "Screenshot saved successfully", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to save screenshot", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Bitmap getss(View view) {
        if (view == null) {
            return null;
        }
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, capture the screenshot
                takess();
            } else {
                Toast.makeText(this, "Permission denied, unable to capture screenshot", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String BinToOct(String Binary)
    {
        String Octal = Integer.toOctalString(Integer.parseInt(Binary,2));
        return Octal;
    }

    private String binToHex(String Binary)
    {
        int decimal = Integer.parseInt(Binary,2);
        String hexa = Integer.toHexString(decimal);
        return hexa;
    }
}
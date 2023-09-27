package com.example.usingpreferences.KonfirmMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.usingpreferences.Activity.LoginActivity;
import com.example.usingpreferences.R;

public class SelamatDatang extends AppCompatActivity {
Button Mulai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selamat_datang);


        Mulai = findViewById(R.id.mulai);
        Mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelamatDatang.this, LoginActivity.class));

                finish();


            }
        });


    } public void onBackPressed() {
        // Tidak melakukan apa-apa ketika tombol kembali ditekan
    }
}
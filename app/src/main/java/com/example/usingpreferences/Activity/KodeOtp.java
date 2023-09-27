package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.usingpreferences.R;

public class KodeOtp extends AppCompatActivity {
private View view;
private Button konfir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kode_otp);


        konfir = findViewById(R.id.button_konfirmasiotpp);
//        LANGSUNG KE MENU ATUR ULANG KATA SANDI
        konfir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(KodeOtp.this, AturUlangKataSandi.class);
                startActivity(pindah);
                finish();
            }
        });





    }
}
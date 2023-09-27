package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.usingpreferences.R;

public class LupaKatasandi extends AppCompatActivity {
private Button lanjutkeotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_katasandi);

        lanjutkeotp = findViewById(R.id.button_lupakatasandi);
        lanjutkeotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(LupaKatasandi.this, KodeOtp.class);
                startActivity(pindah);
                finish();
            }
        });
    }
}
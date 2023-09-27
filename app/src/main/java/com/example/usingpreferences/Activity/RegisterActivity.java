package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usingpreferences.KonfirmMenu.BerhasilDaftar;
import com.example.usingpreferences.Database.Preferences;
import com.example.usingpreferences.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText mViewNama,mViewNotlp,mViewEmail, mViewPassword, mViewPassword2;
    private Button mBtnRegister,mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);








        mViewNama = findViewById(R.id.et_namaSignup);
        mViewNotlp = findViewById(R.id.et_nohpSignup);
        mViewEmail = findViewById(R.id.et_emailSignup);
        mViewPassword = findViewById(R.id.et_passwordSignup);
        mViewPassword2 = findViewById(R.id.et_passwordSignup2);

        mBtnRegister = findViewById(R.id.button_signupSignup);
        mBtnLogin = findViewById(R.id.button_signupSignin);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                finish();
            }
        });
    }

    private void registerUser() {
        mViewNama.setError(null);
        mViewNotlp.setError(null);
        mViewEmail.setError(null);
        mViewPassword.setError(null);
        mViewPassword2.setError(null);
        View focus = null;
        boolean cancel = false;

        String user = mViewNama.getText().toString();
        String notlp = mViewNotlp.getText().toString().trim();
        String email = mViewEmail.getText().toString();
        String password = mViewPassword.getText().toString();
        String confirmPassword = mViewPassword2.getText().toString();

        if (TextUtils.isEmpty(user)) {
            mViewNama.setError("Nama harus diisi");
            focus = mViewNama;
            cancel = true;
        }else if (TextUtils.isEmpty(notlp)) {
            mViewNotlp.setError("No Telpon harus diisi");
            focus = mViewNotlp;
            cancel = true;
        }else if (notlp.length()<=10) {
            mViewNotlp.setError("No Telpon minimal 11 angka");
            focus = mViewNotlp;
            cancel = true;
        }else if (TextUtils.isEmpty(email)) {
            mViewEmail.setError("Email harus diisi");
            focus = mViewEmail;
            cancel = true;
        }else if (TextUtils.isEmpty(password)) {
            mViewPassword.setError("Password harus diisi");
            focus = mViewPassword;
            cancel = true;
        }else if (!TextUtils.equals(password, confirmPassword)) {
            mViewPassword2.setError("Password tidak cocok");
            focus = mViewPassword2;
            cancel = true;
        }else if (cancel) {
            focus.requestFocus();
        } else {
            Preferences.setRegisteredUser(getBaseContext(), user);
            Preferences.setRegisteredUser(getBaseContext(), notlp);
            Preferences.setRegisteredUser(getBaseContext(), email);
            Preferences.setRegisteredPass(getBaseContext(), password);
            Preferences.setLoggedInStatus(getBaseContext(), false);
            Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getBaseContext(), BerhasilDaftar.class));
            finish();
        }
    } @Override
    public void onBackPressed() {
        // Tidak melakukan apa-apa ketika tombol kembali ditekan
    }
}

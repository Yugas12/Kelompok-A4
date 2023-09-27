package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usingpreferences.MenuFragment.HomeFragment;
import com.example.usingpreferences.Database.Preferences;
import com.example.usingpreferences.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginActivity extends AppCompatActivity {

    private EditText mViewUser, mViewPassword;
    private SignInButton googleSignInButton;
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    Button lupapw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lupapw = findViewById(R.id.btn_lupapassword);


//      Mengarah langsung ke kode otp tanpa ada kondisi aliasb hanya frontendnya saja
        lupapw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(LoginActivity.this, LupaKatasandi.class);
                startActivity(pindah);
            
            }
        });
        mViewUser = findViewById(R.id.et_emailSignin);
        mViewPassword = findViewById(R.id.et_passwordSignin);

        mViewPassword.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                razia();
                return true;
            } else {
                return false;
            }
        });

        Button signInButton = findViewById(R.id.button_siginSignin);
        signInButton.setOnClickListener((v) -> {
            razia();
        });

        Button signUpButton = findViewById(R.id.button_signupSignin);
        signUpButton.setOnClickListener((v) -> {
            startActivity(new Intent(getBaseContext(), RegisterActivity.class));
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        });



        googleSignInButton = findViewById(R.id.googleSignInButton);
        googleSignInButton.setSize(SignInButton.SIZE_WIDE);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, connectionResult -> {
                    // Penanganan jika terjadi kesalahan saat menghubungkan dengan layanan Google
                    Toast.makeText(LoginActivity.this, "Kesalahan saat menghubungkan dengan Google", Toast.LENGTH_SHORT).show();
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        googleSignInButton.setOnClickListener(v -> {
            signInWithGoogle();
        });
    }

    private void signInWithGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInAccount account = Auth.GoogleSignInApi.getSignInResultFromIntent(data).getSignInAccount();
            if (account != null) {
                String displayName = account.getDisplayName();
                String email = account.getEmail();
            } else {
                Toast.makeText(this, "Login Google gagal", Toast.LENGTH_SHORT).show();
            }
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (Preferences.getLoggedInStatus(getBaseContext())) {
//            startActivity(new Intent(getBaseContext(), MainActivity.class));
//
//            finish();
//        }
//    }

    private void razia() {
        mViewUser.setError(null);
        mViewPassword.setError(null);
        View focus = null;
        boolean cancel = false;

        String user = mViewUser.getText().toString();
        String password = mViewPassword.getText().toString();

        if (TextUtils.isEmpty(user)) {
            mViewUser.setError("Username harus diisi");
            focus = mViewUser;
            cancel = true;

        }else if (TextUtils.isEmpty(password)) {
            mViewPassword.setError("Password harus diisi");
            focus = mViewPassword;
            cancel = true;
        } else if (!cekUser(user)) {
            mViewUser.setError("Username Belum Terdaftar");

            focus = mViewUser;
            cancel = true;
        } else if (!cekPassword(password)) {
            mViewPassword.setError("Password salah");
            focus = mViewPassword;
            cancel = true;
        }

        if (cancel) {
            focus.requestFocus();
        } else {
            masuk();
        }
    }

    private void masuk() {
        Preferences.setLoggedInStatus(getBaseContext(), true);
        Preferences.setLoggedUser(getBaseContext(), mViewUser.getText().toString());
        Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getBaseContext(), MainActivity.class));
        Fragment fragment = new HomeFragment();
        finish();
    }

    private boolean cekPassword(String password) {
        return password.equals(Preferences.getRegisteredPass(getBaseContext()));
    }

    private boolean cekUser(String user) {
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }

    @Override
    public void onBackPressed() {
        // Tidak melakukan apa-apa ketika tombol kembali ditekan
    }
}

package com.gokhanbayram.whattheplant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.FillResponse;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private Toolbar actionbarRegister;
    private EditText txtUsername, txtEmail, txtPassword;
    private Button btnRegister;

    private FirebaseAuth auth;

    public void init() {

        actionbarRegister = (Toolbar) findViewById(R.id.actionbarRegister);
        setSupportActionBar(actionbarRegister);
        getSupportActionBar().setTitle("Hesap Oluştur");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();

        txtUsername = (EditText) findViewById(R.id.txtusernameRegister);
        txtEmail = (EditText) findViewById(R.id.txtemailRegister);
        txtPassword = (EditText) findViewById(R.id.txtPasswordRegister);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });
    }

    private void createNewAccount() {

        String username = txtUsername.getText().toString();
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email Alanı Boş Olamaz !", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Şifre Alanı Boş Olamaz !", Toast.LENGTH_LONG).show();
        } else {


            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

                        Toast.makeText(RegisterActivity.this, "Hesabınız Başarılı Bir Şekilde Oluşturuldu !", Toast.LENGTH_LONG).show();
                        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
                        finish();
                    } else {

                        Toast.makeText(RegisterActivity.this, "Bir Hata Oluştu !", Toast.LENGTH_LONG).show();
                    }


                }
            });

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

    }
}
package com.testapplication.project.kucherenko.dnu.testsloyev;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText e_mail;
    private ProgressDialog progressDialog;
    private EditText password;
    private Button loginButton;
    private Button signupButton;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        e_mail = (EditText) findViewById(R.id.e_mail_edit);
        password = (EditText) findViewById(R.id.password_edit);
        loginButton = (Button) findViewById(R.id.btn_log);
        signupButton = (Button) findViewById(R.id.btn_sign);
        loginButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_log: {
                signInUser();
                break;
            }
            case R.id.btn_sign: {
                signUpUser();
                break;
            }
        }
    }

    private void signUpUser() {

        if (firebaseAuth.getCurrentUser() != null)
            firebaseAuth.signOut();

        String mail = e_mail.getText().toString().trim();
        final String pass = password.getText().toString().trim();

        if (TextUtils.isEmpty(mail)) {
            Toast.makeText(this, "Please, enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Please, enter password", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Please, wait for completing registering...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            Toast.makeText(RegistrationActivity.this, "Registering completed", Toast.LENGTH_SHORT).show();
                        } else {
                            if (pass.length() <= 4)
                                Toast.makeText(RegistrationActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(RegistrationActivity.this,
                                    "User " + e_mail.getText().toString() + " already exist",
                                    Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    private void signInUser() {
        String mail = e_mail.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (TextUtils.isEmpty(mail)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Sign in completed", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Password isn`t correct \nor user does not exist",
                                    Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }
}

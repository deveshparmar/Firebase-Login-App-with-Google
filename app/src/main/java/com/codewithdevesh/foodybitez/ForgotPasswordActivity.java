package com.codewithdevesh.foodybitez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class ForgotPasswordActivity extends AppCompatActivity {
private CircularProgressButton forgetbtn;
private TextInputLayout txtEmail;
private String email;
private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        auth = FirebaseAuth.getInstance();
        txtEmail = findViewById(R.id.txtEmail);
        forgetbtn = findViewById(R.id.forgetBtn);

        forgetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }

    private void validateData() {
        email = txtEmail.getEditText().getText().toString();
        if(email.isEmpty()){
            txtEmail.setError("Enter the required details!");
        }
        else{
            txtEmail.setError(null);
            txtEmail.setErrorEnabled(false);
            forgetPass();
        }
    }

    private void forgetPass() {
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ForgotPasswordActivity.this, "Link is sent to your email id", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgotPasswordActivity.this,ForgetPasswordSuccessMsg.class));
                            overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
                            finish();
                        }
                        else{
                            Toast.makeText(ForgotPasswordActivity.this, "task:"+task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
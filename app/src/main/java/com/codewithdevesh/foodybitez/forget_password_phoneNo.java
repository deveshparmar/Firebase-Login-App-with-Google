package com.codewithdevesh.foodybitez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class forget_password_phoneNo extends AppCompatActivity {
CircularProgressButton button;
TextInputLayout nextNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_phone_no);
        button = findViewById(R.id.nextbtn);
        nextNo = findViewById(R.id.nextPhNo);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
package com.codewithdevesh.foodybitez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class PhoneActivity extends AppCompatActivity {
TextInputLayout phoneNo;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
CircularProgressButton getOtp;
CountryCodePicker countryCodePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        phoneNo = findViewById(R.id.signup_phoneNo);
        getOtp = findViewById(R.id.getOtp);
        countryCodePicker = findViewById(R.id.countryCodePicker);

        getOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validatePhoneNo()){
                    return;
                }
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");
                String name = (String) getIntent().getExtras().get("Name");
                String usrname = (String) getIntent().getExtras().get("Username");
                String psswd = (String) getIntent().getExtras().get("Password");
                String mail = (String) getIntent().getExtras().get("Email");
                String UserEnteredNo = phoneNo.getEditText().getText().toString();
                String getPhoneNo = "+"+countryCodePicker.getFullNumber()+UserEnteredNo;
                UserHelperClass helperClass = new UserHelperClass(name,usrname,mail,psswd,getPhoneNo);
                reference.child(getPhoneNo).setValue(helperClass);
                Intent intent = new Intent(getApplicationContext(),VerifyOTP.class);
                intent.putExtra("phoneNo",getPhoneNo);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
//                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
//                overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

            }
        });
    }

    private boolean validatePhoneNo(){
        String x = phoneNo.getEditText().getText().toString();
        if(x.isEmpty()){
            phoneNo.setError("Field can not be empty");
            return false;
        }
        else{
            phoneNo.setError(null);
            phoneNo.setErrorEnabled(false);
            return true;
        }
    }
}
package com.codewithdevesh.foodybitez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class SignUpActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 1;
    FirebaseAuth mAuth;
    TextView tv;
    TextInputLayout fullName,username,email,password;
    CircularProgressButton signup;
    float v =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tv = findViewById(R.id.call_lg);
        signup = findViewById(R.id.signup_next_btn);
        fullName = findViewById(R.id.signup_fullname);
        username = findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        mAuth = FirebaseAuth.getInstance();

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateEmail()| !validatePassword()| !validateFullName()| !validateUserName()){
                    return;
                }
                signup.startAnimation();
                createUser();
//                rootNode = FirebaseDatabase.getInstance();
////                reference = rootNode.getReference("Users");
//                String name = fullName.getEditText().getText().toString();
//                String usrname = username.getEditText().getText().toString();
//                String psswd = password.getEditText().getText().toString();
//                String mail = email.getEditText().getText().toString();
//
////
////                UserHelperClass helperClass = new UserHelperClass(name,usrname,mail,psswd);
////                reference.child(mail).setValue(helperClass);
//                Intent intent = new Intent(SignUpActivity.this,PhoneActivity.class);
//                intent.putExtra("Name",name);
//                intent.putExtra("Username",usrname);
//                intent.putExtra("Password",psswd);
//                intent.putExtra("Email",mail);
////                startActivity(new Intent(SignUpActivity.this,PhoneActivity.class));
//                overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
//                startActivity(intent);

            }
        });

    }

    private void createUser() {
        String name = fullName.getEditText().getText().toString();
        String usrname = username.getEditText().getText().toString();
        String psswd = password.getEditText().getText().toString();
        String mail = email.getEditText().getText().toString();
        mAuth.createUserWithEmailAndPassword(mail,psswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    signup.revertAnimation();
                    Toast.makeText(SignUpActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this,PhoneActivity.class);
                    intent.putExtra("Name",name);
                    intent.putExtra("Username",usrname);
                    intent.putExtra("Password",psswd);
                    intent.putExtra("Email",mail);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
                }
                else{
                    signup.revertAnimation();
                    Toast.makeText(SignUpActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateFullName(){
        String x = fullName.getEditText().getText().toString().trim();
        if(x.isEmpty()){
            fullName.setError("Field can not be empty");
            return false;
        }
        else{
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUserName(){
        String x = username.getEditText().getText().toString().trim();
        String space = "\\A\\w{1,20}\\z";
        if(x.isEmpty()){
            username.setError("Field can not be empty");
            return false;
        }
        else if(x.length()>20){
            username.setError("Username too large!");
            return false;
        }
        else if(!x.matches(space)){
            username.setError("White spaces are not allowed!");
            return false;
        }
        else{
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail(){
        String x = email.getEditText().getText().toString().trim();
        String emailMsg = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(x.isEmpty()){
            email.setError("Field can not be empty");
            return false;
        }
        else if(!x.matches(emailMsg)){
            email.setError("White spaces are not allowed!");
            return false;
        }
        else{
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword(){
        String x = password.getEditText().getText().toString().trim();
//        String psswd = "^" +
//                //"(?=.*[0-9])" +         //at least 1 digit
//                //"(?=.*[a-z])" +         //at least 1 lower case letter
//                //"(?=.*[A-Z])" +         //at least 1 upper case letter
//                "(?=.*[a-zA-Z])" +      //any letter
//                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
//                "(?=S+$)" +           //no white spaces
//                ".{4,}" +               //at least 4 characters
//                "$";
        if(x.isEmpty()){
            password.setError("Field can not be empty");
            return false;
        }

//        else if(!x.matches(psswd)){
//            password.setError("Password should contain at least 4 characters!");
//            return false;
//        }
        else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }





 }
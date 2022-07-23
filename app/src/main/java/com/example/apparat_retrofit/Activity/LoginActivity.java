package com.example.apparat_retrofit.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.apparat_retrofit.R;
import com.example.apparat_retrofit.RegisterDatabaseHelper;
import com.example.apparat_retrofit.UserRegister;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity{
    Toolbar logintoolbar;
    AppCompatImageView forward;
    TextView tx1,tx2,tx3;
    Button create,login;
    View viewlogin;
    TextInputLayout textInputLayoutemail,textInputLayoutpassword;
    TextInputEditText textInputEditTextemail,textInputEditTextpassword;
    ConstraintLayout constraintLayout;
    RegisterDatabaseHelper registerDatabaseHelper;
    UserRegister userRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerDatabaseHelper=new RegisterDatabaseHelper(this);


        logintoolbar=findViewById(R.id.toolbar_login);
        forward=findViewById(R.id.img_login_forward);
        tx1=findViewById(R.id.tv_login);
        tx2=findViewById(R.id.tv_login_two);
        tx3=findViewById(R.id.tv_login_tree);
        create=findViewById(R.id.btn_login_creat);

        viewlogin=findViewById(R.id.view_login);
        textInputLayoutemail=findViewById(R.id.textinputlayout_email);
        textInputLayoutpassword=findViewById(R.id.textinputlayout_password);
        textInputEditTextemail=findViewById(R.id.et_dialogEdit_email);
        textInputEditTextpassword=findViewById(R.id.et_dialogEdit_password);
        login=findViewById(R.id.btn_login_login);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r=new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(r);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Validate(textInputEditTextemail) && Validate(textInputEditTextpassword)){
                    loginn();
                    //registerDatabaseHelper.ADDUser(userRegister);
                    registerDatabaseHelper.GETUser();

                }

                
            }
        });


    }
    public boolean Validate(TextInputEditText editText){
        if(editText.getText().toString().trim().length()>0){
            return true;

        }
        editText.setError("Please Fill email and password");
        editText.requestFocus();
        return false;


    }
    public void loginn(){
        userRegister.setEmail(textInputEditTextemail.getText().toString().trim());
        userRegister.setPassword(textInputEditTextpassword.getText().toString().trim());

    }








}

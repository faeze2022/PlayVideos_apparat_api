package com.example.apparat_retrofit.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apparat_retrofit.R;
import com.example.apparat_retrofit.RegisterDatabaseHelper;
import com.example.apparat_retrofit.UserRegister;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CreateAccountActivity extends AppCompatActivity  {
    TextView back,createaccount,enteremail,enterpassword,entername;
    AppCompatImageView backicon;
    TextInputLayout textInputLayoutemail,textInputLayoutpassword,textInputLayoutname;
    TextInputEditText textInputEditTextemail,textInputEditTextpassword,textInputEditTextname;
    Button register;
    ConstraintLayout constraintLayout;
    private static final String TAG ="CreateAccountActivity";
    RegisterDatabaseHelper registerDatabaseHelper;
    UserRegister userRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        initViews();
        registerDatabaseHelper=new RegisterDatabaseHelper(this);
        userRegister=new UserRegister();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Validate(textInputEditTextname) && Validate(textInputEditTextpassword) && Validate(textInputEditTextemail)){
                    registerMe();
                }

            }
        });



    }
    public void initViews(){

        textInputLayoutname=findViewById(R.id.textinputlayout_create_name);
        textInputLayoutpassword=findViewById(R.id.textinputlayout_create_password);
        textInputLayoutemail=findViewById(R.id.textinputlayout_create_email);

        textInputEditTextname=findViewById(R.id.et_dialogEdit_name);
        textInputEditTextpassword=findViewById(R.id.et_dialogEdit_password);
        textInputEditTextemail=findViewById(R.id.et_dialogEdit_email);

        register=findViewById(R.id.btn_create_continiue);
        constraintLayout=findViewById(R.id.constraintlayout);
        back=findViewById(R.id.tv_create);
        createaccount=findViewById(R.id.tv_create_title);
        entername=findViewById(R.id.tv_create_subtitle_name);
        enteremail=findViewById(R.id.tv_create_subtitle);
        enterpassword=findViewById(R.id.tv_create_subtitle_password);
        backicon=findViewById(R.id.img_back_create);

    }


    private void registerMe(){
        userRegister.setName(textInputEditTextname.getText().toString().trim());
        userRegister.setPassword(textInputEditTextpassword.getText().toString().trim());
        userRegister.setEmail(textInputEditTextemail.getText().toString().trim());

        registerDatabaseHelper.ADDUser(userRegister);
        Toast.makeText(CreateAccountActivity.this, "register done successfully", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(CreateAccountActivity.this, LoginActivity.class);
        startActivity(intent);
    }


    public boolean Validate(TextInputEditText editText){
        if(editText.getText().toString().trim().length()>0){
            return true;
        }
        editText.setError("Please Fill This");
        editText.requestFocus();
        return false;

    }

}

package com.example.zhao_.mrcheck;

import android.support.v4.media.MediaMetadataCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editUserName;
    private EditText editPassWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        Button button1 = (Button) findViewById(R.id.loginButton);
        editUserName=(EditText)findViewById(R.id.edit_username);
        editPassWord=(EditText)findViewById(R.id.edit_password);
        button1.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.loginButton:
                String input_username=editUserName.getText().toString();
                String input_password=editPassWord.getText().toString();
                break;
            default:
                break;
        }
    }
    }

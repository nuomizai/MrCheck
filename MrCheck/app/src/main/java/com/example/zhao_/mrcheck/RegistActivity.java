package com.example.zhao_.mrcheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import android.os.Message;

import okhttp3.*;
import java.io.IOException;

public class RegistActivity extends AppCompatActivity {
    private Button reg;
    private EditText uname,pwd;
    private String name1,pwd1;
    private TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        uname=(EditText)findViewById(R.id.username);
        pwd=(EditText)findViewById(R.id.password);
        reg=(Button)findViewById(R.id.registButton);
        reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                name1=uname.getText().toString().trim();
                pwd1=pwd.getText().toString().trim();
                postRequest(name1,pwd1);

            }
        });
    }

    private void postRequest(String name,String pwd){
        FormBody formBody= new FormBody.Builder()
                .add("uname",name)
                .add("pwd",pwd)
                .add("method0","okhttpreg")
                .build();
        final Request request=new Request.Builder()
                .url("http://111.230.237.110/Signin.php")
                .post(formBody)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response=null;
                try{
                    OkHttpClient client= new OkHttpClient();
                    if(response.isSuccessful()){

                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();


    }
}

package com.example.zhao_.mrcheck;

import android.app.DownloadManager;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editUserName;
    private EditText editPassWord;
    private Button LButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        LButton = (Button) findViewById(R.id.loginButton);
        editUserName=(EditText)findViewById(R.id.edit_username);
        editPassWord=(EditText)findViewById(R.id.edit_password);
        LButton.setOnClickListener(this);
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
        if(v.getId() == R.id.loginButton){
            sendRequestWithOkHttp();
        }
    }
    private void sendRequestWithOkHttp(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request=new Request.Builder()
                            .url("http://www.baidu.com")
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

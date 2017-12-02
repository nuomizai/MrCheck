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
import org.json.*;

import okhttp3.*;
import java.io.IOException;

public class RegistActivity extends AppCompatActivity {
    private Button reg;
    private EditText uname,pwd,intro;
    private String name1,pwd1,intro1;
    private TextView text1;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what==1){
                String qq=(String)msg.obj;
                Log.i("RegistActivity",qq);
                text1.setText(qq);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        uname=(EditText)findViewById(R.id.username);
        pwd=(EditText)findViewById(R.id.password);
        intro=(EditText)findViewById(R.id.introduction);
        text1=(TextView)findViewById(R.id.response);
        reg=(Button)findViewById(R.id.registButton);

        reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                    name1 = uname.getText().toString().trim();
                    pwd1 = pwd.getText().toString().trim();
                    intro1 = intro.getText().toString().trim();
                    Log.d("RegistActivity", "name" + name1);
                    Log.d("RegistActivity", "password" + pwd1);
                    Log.d("RegistActivity", "introduction" + intro1);
                    postRequest();
            }
        });
    }

    private void postRequest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody formBody= new FormBody.Builder()
                        .add("uname",name1)
                        .add("pwd",pwd1)
                        .add("introduction",intro1)
                        .build();
                final Request request=new Request.Builder()
                        .url("http://111.230.237.110/Signin.php")
                        .post(formBody)
                        .build();
                try{
                    OkHttpClient client= new OkHttpClient();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    parseJSONWithJSONObject(responseData);

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void parseJSONWithJSONObject(String jsonData){
        try{
            JSONArray jsonArray=new JSONArray(jsonData);
            JSONObject jsonObject=jsonArray.getJSONObject(0);
            String msg=jsonObject.getString("msg");
            Log.d("RegistActivity","msg is"+msg);
            
            }catch(Exception e){
            e.printStackTrace();
        }
    }
}

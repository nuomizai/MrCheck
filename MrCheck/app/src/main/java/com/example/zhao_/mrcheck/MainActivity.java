package com.example.zhao_.mrcheck;

/**
 * Created by 万珂嘉 on 2017/12/3.
 */
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.os.Message;
import android.widget.AdapterView;
import org.json.*;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import okhttp3.*;
import java.io.IOException;


public class MainActivity  extends  AppCompatActivity {
    private Button add;
    private ArrayAdapter<String> adapter;
    private EditText money,ps;
    private String money1,ps1,spstr;
    private TextView text1;
    private Spinner spin=null;

    final OkHttpClient client = new OkHttpClient();

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what==1){
                String qq = (String) msg.obj;
                Log.i("haha", qq);
                text1.setText(qq);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
/**
 * 初始化数据
 */
        money= (EditText) findViewById(R.id.editmoney);
        ps= (EditText) findViewById(R.id.editps);
        add= (Button) findViewById(R.id.add);
        text1= (TextView) findViewById(R.id.return0);
        spin= (Spinner) findViewById(R.id.classifyspin);
        spstr = (String) spin.getSelectedItem();
/**
 * 注册按钮监听
 */spin.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener() {

                                      public void onItemSelected(AdapterView<?> parent, View view,
                                                                 int position, long id) {

                                          //拿到被选择项的值
                                          spstr = (String) spin.getSelectedItem();

                                      }
                                      public void onNothingSelected(AdapterView<?> parent) {

                                      }
                                  } );

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取相关参数
                money1=money.getText().toString().trim();
                ps1=ps.getText().toString().trim();
                //通过okhttp发起post请求
                postRequest();

            }
        });

    }

    /**
     * post请求后台
     * @param
     * @param
     */
    private void postRequest()  {
        //建立请求表单，添加上传服务器的参数
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody formBody = new FormBody.Builder()
                        .add("Type", spstr)
                        .add("Money", money1)
                        .add("Remark", ps1)
                        .build();
                //发起请求
                final Request request = new Request.Builder()
                        .url("http://111.230.237.110/Addbill.php")
                        .post(formBody)
                        .build();

                try {
                    OkHttpClient client = new OkHttpClient();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithJSONObject(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }}).start();}

private void parseJSONWithJSONObject(String jsonData){
        try{
        JSONArray jsonArray=new JSONArray(jsonData);
        JSONObject jsonObject=jsonArray.getJSONObject(0);
        String msg=jsonObject.getString("msg");
        Log.d("mainActivity","msg is"+msg);
        }catch(Exception e){
        e.printStackTrace();
        }

    }

}

package com.example.zhao_.mrcheck;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class searchActivity extends AppCompatActivity {
    private Button sch;
    private EditText syear,smonth,sdate,dyear,dmonth,ddate;
    private String year1,month1,date1,year2,month2,date2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        sch=(Button)findViewById(R.id.search_button);
        syear=(EditText)findViewById(R.id.year1);
        smonth=(EditText)findViewById(R.id.month1);
        sdate=(EditText)findViewById(R.id.date1);
        dyear=(EditText)findViewById(R.id.year2);
        dmonth=(EditText)findViewById(R.id.month2);
        ddate=(EditText)findViewById(R.id.date2);

        sch.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v){
             year1 = syear.getText().toString().trim();
             month1 =smonth.getText().toString().trim();
             date1 = sdate.getText().toString().trim();
             year2 =dyear.getText().toString().trim();
             month2 = dmonth.getText().toString().trim();
             date2 = ddate.getText().toString().trim();
             Log.d("searchActivity", "startyear" + year1);
             Log.d("searchActivity", "startmonth" + month1);
             Log.d("searchActivity", "startdate" + date1);
             Log.d("searchActivity", "endyear" + year2);
             Log.d("searchActivity", "endmonth" + month2);
             Log.d("searchActivity", "enddate" + date2);
             postRequest();
         }
        });
    }

    private void postRequest(){
            new Thread(new Runnable(){
           @Override
                public void run(){
               RequestBody formBody=new FormBody.Builder()
                       .add("Uname","11")
                       .add("Type","")
                       .add("Year1",year1)
                       .add("Month1",month1)
                       .add("Day1",date1)
                       .add("Year2",year2)
                       .add("Month2",month2)
                       .add("Day2",date2)
                       .build();
               final Request request=new Request.Builder()
                       .url("http://111.230.237.110/Searchbill.php")
                       .post(formBody)
                       .build();
               try {
                   OkHttpClient client = new OkHttpClient();
                   Response response=client.newCall(request).execute();
                   String responseData=response.body().string();
                   Log.d("searchActivity", responseData);
                   //showResponse(responseData);
               } catch (Exception e)
               { e.printStackTrace();}
           }
            }).start();
    }
}

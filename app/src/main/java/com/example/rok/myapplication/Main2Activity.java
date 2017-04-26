package com.example.rok.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6;
    RadioButton r1,r2,r3;
    Button b1;
    int count =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();



    }

    public void onClick(View view) {

        switch(view.getId()){
            case R.id.btnAdd:
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                String formatDate = sdfNow.format(date);
                String name = e1.getText().toString();
                String tel = e2.getText().toString();
                String address = e3.getText().toString();
                String category;
                String menu1 = e4.getText().toString();
                String menu2 = e5.getText().toString();
                String menu3 = e6.getText().toString();
                if(e1.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(),"적어도 이름만은 채워주세요",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (r1.isChecked()) {
                        category = "chicken";
                    } else if (r2.isChecked()) {
                        category = "pizza";
                    } else {
                        category = "hamburger";
                    }
                    Log.d("Park1", category);
                    Data data = new Data(name, tel, address, formatDate, category, menu1, menu2, menu3, count);
                    Log.d("Park2", data.category);
                    Intent intent = getIntent();
                    intent.putExtra("user", data);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            case R.id.btnCancel:
                onBackPressed();
                break;

        }
    }
    void init(){
        e1 = (EditText)findViewById(R.id.etname);
        e2 = (EditText)findViewById(R.id.ettel);
        e3 = (EditText)findViewById(R.id.etaddr);
        e4 = (EditText)findViewById(R.id.etmenu1);
        e5 = (EditText)findViewById(R.id.etmenu2);
        e6 = (EditText)findViewById(R.id.etmenu3);

        r1 = (RadioButton)findViewById(R.id.radio1);
        r2 = (RadioButton)findViewById(R.id.radio2);
        r3 = (RadioButton)findViewById(R.id.radio3);






    }
}

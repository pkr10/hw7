package com.example.rok.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6,t7;
    Button b1;
    ImageView i1,i2,i3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        init();
        Intent data = getIntent();
        final Data user = data.getParcelableExtra("user1");
        t1.setText(user.name);
        t2.setText(user.menu1);
        t3.setText(user.menu2);
        t4.setText(user.menu3);
        t5.setText(user.number);
        t6.setText(user.adrress);
        t7.setText(user.date);
        Log.d("Park4",user.category);
        if(user.category.matches("pizza")){
            i1.setImageResource(R.drawable.pizza);
        }
        else if(user.category.matches("hamburger")){
            i1.setImageResource(R.drawable.hamburger);

        }
        else if(user.category.matches("chicken")){
            i1.setImageResource(R.drawable.chicken);

        }
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:/"+user.number));
                startActivity(intent);
            }
        });
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://"+user.adrress));
                startActivity(intent);
            }
        });
    }



    void init(){
        t1 = (TextView)findViewById(R.id.txtname);
        t2 = (TextView)findViewById(R.id.etmenu1);
        t3 = (TextView)findViewById(R.id.etmenu2);
        t4 = (TextView)findViewById(R.id.etmenu3);
        t5 = (TextView)findViewById(R.id.tvTel);
        t6 = (TextView)findViewById(R.id.tvURL);
        t7 = (TextView)findViewById(R.id.tvRegdate);
        b1 = (Button)findViewById(R.id.btnback);
        i1 = (ImageView)findViewById(R.id.imgno);
        i2 = (ImageView)findViewById(R.id.imageView2);
        i3 = (ImageView)findViewById(R.id.imageView3);
    }

    public void onClick(View view) {
        onBackPressed();

    }

}

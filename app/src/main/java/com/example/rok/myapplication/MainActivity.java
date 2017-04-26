package com.example.rok.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    ListView l1;
    ArrayList<Data> data1 = new ArrayList<Data>();
    DataAdapter adapter;
    Button button4,button1;
    EditText e1;
    ArrayList<Data> data2 = new ArrayList<Data>();
    CheckBox c1;
    View v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        l1 = (ListView) findViewById(R.id.listview);
        adapter = new DataAdapter(data1,this,data2);
        l1.setAdapter(adapter);
        Log.d("어댑터",adapter.toString());
        Log.d("리스트",l1.toString());
        button4 = (Button)findViewById(R.id.b4);
        button1 = (Button)findViewById(R.id.b1);


        e1 = (EditText)findViewById(R.id.editText);

        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text =e1.getText().toString();

                adapter.filter(text);

            }
        });



    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.b1:
                Intent intent = new Intent(this, Main2Activity.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.b2:
                Collections.sort(data1, nameasc);
                adapter.notifyDataSetChanged();
                break;
            case R.id.b3:
                Collections.sort(data1,categoryasc);
                adapter.notifyDataSetChanged();
                break;
            case R.id.b4:
                if(button4.getText().toString().equals("선택")){
                    for(int i=0; i< data1.size(); i++){
                            data1.get(i).setCount(1);
                    }
                    adapter.notifyDataSetChanged();
                    button4.setText("삭제");
                    button1.setEnabled(false);
                }
                else{
                    AlertDialog.Builder dig = new AlertDialog.Builder(MainActivity.this);
                    dig.setTitle("정말 삭제하실거에요?")
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    for(int i = 0;i<data1.size();i++){
                                        data1.get(i).setCount(0);
                                    }
                                    button4.setText("선택");
                                    button1.setEnabled(true);
                                    adapter.notifyDataSetChanged();
                                }

                            })
                            .setPositiveButton("삭제", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    for(int i=0; i< data1.size(); i++){
                                        v = data1.get(i).getV();
                                        c1 = (CheckBox)v.findViewById(R.id.checkBox1);
                                        data1.get(i).setCount(0);

                                        if(c1.isChecked()){
                                            data1.remove(i);
                                            i--;

                                        }
                                    }

                                    adapter.notifyDataSetChanged();
                                    button4.setText("선택");
                                    button1.setEnabled(true);
                                }
                            })
                            .show();
                }

                break;



        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                final Data user = data.getParcelableExtra("user");
                Log.d("Park3", user.category);
                data1.add(user);
                data2.add(user);
                adapter.notifyDataSetChanged();
                l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent1 = new Intent(MainActivity.this, Main3Activity.class);
                        intent1.putExtra("user1", data1.get(position));
                        startActivity(intent1);
                    }
                });

            }
        }
    }
  Comparator<Data> nameasc = new Comparator<Data>() {
      @Override
      public int compare(Data o1, Data o2) {
          return o1.name.compareTo(o2.name);
      }
  };
    Comparator<Data> categoryasc = new Comparator<Data>() {
        @Override
        public int compare(Data o1, Data o2) {
            return o1.category.compareTo(o2.category);
        }
    };

}

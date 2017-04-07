package com.example.rok.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView l1;
    ArrayList<Data> data1 = new ArrayList<Data>();
    ArrayAdapter<Data> adapter;
    TextView t1;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListview();
        init();



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void setListview() {
        l1 = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<Data>(this, android.R.layout.simple_list_item_1, data1);

        l1.setAdapter(adapter);


    }


    public void onClick(View view) {

        Intent intent = new Intent(this, Main2Activity.class);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                final Data user = data.getParcelableExtra("user");

                Log.d("Park3", user.category);

                data1.add(user);

                adapter.notifyDataSetChanged();
                l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent1 = new Intent(MainActivity.this, Main3Activity.class);
                        intent1.putExtra("user1", data1.get(position));
                        startActivity(intent1);



                    }
                });
                l1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                        dlg.setTitle("삭제확인");
                        dlg.setMessage("정말 삭제하시겠어요?");
                        dlg.setNegativeButton("닫기",null);
                        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                data1.remove(position);
                                adapter.notifyDataSetChanged();
                                Snackbar.make(view,"삭제되었습니다",Snackbar.LENGTH_SHORT).show();
                                t1.setText("맛집 리스트("+data1.size()+"개)");

                            }
                        });
                        dlg.show();



                        return false;
                    }
                });
                t1.setText("맛집 리스트("+data1.size()+"개)");




            }
        }


    }
    void init(){
        t1 = (TextView)findViewById(R.id.tv);
    }



}

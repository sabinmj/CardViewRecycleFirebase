package com.example.sabinmj.cardviewrecyclefirebase;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.firebase.client.Firebase;


import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.sabinmj.cardviewrecyclefirebase.m_FireBase.FirebaseHelper;
import com.example.sabinmj.cardviewrecyclefirebase.m_Model.Spacecraft;
import com.example.sabinmj.cardviewrecyclefirebase.m_UI.MyAdapter;

public class MainActivity extends AppCompatActivity {

    DatabaseReference db;
    FirebaseHelper helper;
    RecyclerView rv;
    EditText nameEditTxt;
    MyAdapter adapter;
   // public String NEWS_TYPE =null;

    RecyclerView recyclerView;
    Context context;
    RecyclerView.Adapter recyclerView_Adapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // Firebase.setAndroidContext(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = getApplicationContext();
        recyclerView = (RecyclerView) findViewById(R.id.rv);


        //SETUP RecyclerView
        rv= (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        //SETUP FireBase
        db=FirebaseDatabase.getInstance().getReference();
        helper=new FirebaseHelper(db);

        //ADAPTER

        //Change 2 to your choice because here 2 is the number of Grid layout Columns in each row.
        recyclerViewLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView_Adapter = new MyAdapter(context,helper.retrieve());
        recyclerView.setAdapter(recyclerView_Adapter);


        /*adapter=new MyAdapter(this,helper.retrieve());
        rv.setAdapter(adapter);*/

        adapter=new MyAdapter(MainActivity.this,helper.retrieve());
        rv.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayInputDialog();
            }
        });
    }

    private void displayInputDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("Save To Firebase");
        d.setContentView(R.layout.input_dialog);

        nameEditTxt= (EditText) d.findViewById(R.id.nameEditText);
        Button saveBtn= (Button) d.findViewById(R.id.saveBtn);

        //SAVE
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //GET DATA
               // String name=nameEditTxt.getText().toString();
         String name= "https://firebasestorage.googleapis.com/v0/b/cardrecyclefrebase.appspot.com/o/download%20(1).jpg?alt=media&token=1546b3a1-a9ca-4bae-8bc4-5b10af5dd10d";

                //SET DATA
                Spacecraft s=new Spacecraft();
                s.setName(name);

                //VALIDATE
                if(name.length()>0 && name != null)
                {
                    if(helper.save(s))
                    {
                        nameEditTxt.setText("");
                        adapter=new MyAdapter(MainActivity.this,helper.retrieve());
                        rv.setAdapter(adapter);
                    }
                }else
                {
                    Toast.makeText(MainActivity.this, "Name Cannot Be Empty", Toast.LENGTH_SHORT).show();


                }
            }
        });

        d.show();
    }

}

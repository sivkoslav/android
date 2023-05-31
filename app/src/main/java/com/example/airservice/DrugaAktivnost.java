package com.example.airservice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
//import android.widget.Toolbar;
import androidx.appcompat.widget.*;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class DrugaAktivnost extends AppCompatActivity {

    private GradViewModel gradViewModel;
    public final String TOKEN="/?token=d23947c322d25a6842649b96cd015ce2106c9dab";
    public String city;
    public static String BASE_URL="https://api.waqi.info/feed/";
    public String ceoLink=BASE_URL+city+TOKEN;
    RequestQueue queue;
    static final int PERMISSIONS_REQUEST_INTERNET = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_druga_aktivnost);


        //update();



        Toolbar toolbar= findViewById(R.id.toolbarId);
        setSupportActionBar(toolbar);


        FloatingActionButton floatingActionButton=findViewById(R.id.idFloating);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DrugaAktivnost.this,DodajGrad.class);
                startActivityForResult(intent,1);
            }
        });

        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        GradAdapter adapter=new GradAdapter();
        recyclerView.setAdapter(adapter);



        gradViewModel= new ViewModelProvider(this).get(GradViewModel.class);
        gradViewModel.getGradovi().observe(this, new Observer<List<Grad>>() {
            @Override
            public void onChanged(List<Grad> grads) {
                adapter.setLista(grads);
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                gradViewModel.delete(adapter.getGradNa(viewHolder.getAdapterPosition()));
                Toast.makeText(DrugaAktivnost.this,"Grad je obrisan" ,Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);



        //checkPermissions();
        //queue= Volley.newRequestQueue(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meni, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.stavka:
                Toast.makeText(this,"Otvaranje Mape",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(DrugaAktivnost.this,MapsActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK){
            String imeGrada=data.getStringExtra(DodajGrad.IMEGRADA);

           // Grad grad=new Grad()
        }
    }

    void update(){



        gradViewModel=new GradViewModel(this.getApplication());
        gradViewModel.getGradovi().observe(this, new Observer<List<Grad>>() {
           @Override
           public void onChanged(List<Grad> grads) {




           }
        });






    }
    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    PERMISSIONS_REQUEST_INTERNET);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_INTERNET: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }




}
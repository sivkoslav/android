package com.example.airservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class DodajGrad extends AppCompatActivity {


    //dodao sam id na cardview ako nesto ne radi samo undo
    //CardView card=findViewById(R.id.idCard);
    public static final String IMEGRADA="IMEGRADA";
    private EditText grad;
    Button btnSacuvaj;
    static final int PERMISSIONS_REQUEST_INTERNET = 1;
    String link="https://api.waqi.info/feed/";
    String apikey="/?token=d23947c322d25a6842649b96cd015ce2106c9dab";
    RequestQueue queue;
    GradViewModel gradViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_grad);


        grad=findViewById(R.id.editTextIme);
        btnSacuvaj=findViewById(R.id.idSacuvaj);
        btnSacuvaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText=grad.getText().toString();
                if(!searchText.equals("")){
                   createRequest(searchText);
                }
            }
        });
        checkPermissions();
        queue= Volley.newRequestQueue(this);
    }

    private void createRequest(String searchText) {
        String requestUrl = link + searchText + apikey;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, requestUrl, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                parseJson(response);
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        queue.add(jsonObjectRequest);
    }

    private void parseJson(JSONObject response) {
        try {


            int aqi;
            String grd=grad.getText().toString();

            JSONObject jsonData=response.getJSONObject("data");
            System.out.println("aqi:" + jsonData.get("aqi"));
            String aqiv=""+jsonData.get("aqi");
            aqi=Integer.parseInt(aqiv);
            //System.out.println(jsonData);
            JSONObject grad=jsonData.getJSONObject("city");
            JSONObject vreme=jsonData.getJSONObject("time");
            String imeStanice= (String) grad.get("name");
            String vremeDatum= (String) vreme.get("s");
            System.out.println("Datum"+ vremeDatum);
            //System.out.println("Grad: " + grad);
            System.out.println("Ime Stanice: " + imeStanice);
            System.out.println(aqi);
            //gradViewModel.insert(grd);
            System.out.println(grd);
            Grad g;
            g=new Grad(imeStanice,grd,aqi);
            System.out.println(g.toString());
            gradViewModel=new GradViewModel(this.getApplication());
//            if(aqi<50){
//                this.card.setCardBackgroundColor(Color.GREEN);
//            }else if(aqi<  100 && aqi >50){
//                this.card.setCardBackgroundColor(Color.MAGENTA);
//            }else{
//                this.card.setCardBackgroundColor(Color.RED);
//            }
            gradViewModel.insert(g);

            finish();

            //treba skontati kako uaciti u bazu
            //izlazi mi error pokusavam pozvati metod na null object





        } catch (JSONException e) {

            e.printStackTrace();
        }
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

//    public void saveGrad(String ime,int aqi,String opis){
//
//
//        Grad grad=new Grad(opis,ime,aqi);
//        gradViewModel.insert(grad);
//
//
//        //ovde treba dobaviti vrednosti sa sajta i sacuvati u bazu
//    }
}
package com.rallendet.miapellido_s0x;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapa_e extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    Button b1;
    int position;
    Double Lat, Lon;
    EditText txtLatitud, txtLongitud;

    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mapa_e);

        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);



        Intent i = getIntent();
        position=i.getExtras().getInt("position");
        //Para cargar las coordenadas en el EditText
        txtLatitud.setText(listar.listaUsuarios.get(position).getLatitud());
        txtLongitud.setText(listar.listaUsuarios.get(position).getLongitud());
        //Convertimos en Double para que el mapa se precargue
        Lat=Double.parseDouble(listar.listaUsuarios.get(position).getLatitud());
        Lon=Double.parseDouble(listar.listaUsuarios.get(position).getLongitud());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        b1 = (Button) findViewById(R.id.btna);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Mapa_e.class);
                i.putExtra("p_latitud", ed6.getText().toString());
                i.putExtra("p_longitud", ed7.getText().toString());
                i.putExtra("position",position);
                startActivity(i);



                Intent i = getIntent();
                position=i.getExtras().getInt("position");
//Para cargar las coordenadas en el EditText
                txtLatitud.setText(listar.listaUsuarios.get(position).getLatitud());
                txtLongitud.setText(listar.listaUsuarios.get(position).getLongitud());
//Convertimos en Double para que el mapa se precargue
                Lat=Double.parseDouble(listar.listaUsuarios.get(position).getLatitud());
                Lon=Double.parseDouble(listar.listaUsuarios.get(position).getLongitud());

                //****************************
/*
                Intent intent = new Intent(getApplicationContext(), editar.class);

                listar.listaUsuarios.get(position).setLatitud(txtLatitud.getText().toString());
                listar.listaUsuarios.get(position).setLongitud(txtLongitud.getText().toString());
                intent.putExtra("position",position);
                startActivity(intent);*/
            }
        });

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        LatLng upn = new LatLng(Lat,Lon);
        mMap.addMarker(new MarkerOptions().position(upn).title(listar.listaUsuarios.get(position).getNombre()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(upn));
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        txtLatitud.setText(String.valueOf(latLng.latitude));
        txtLongitud.setText(String.valueOf(latLng.longitude));

        mMap.clear();
        LatLng UPN = new LatLng(latLng.latitude,latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(UPN).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(UPN));
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        txtLatitud.setText(String.valueOf(latLng.latitude));
        txtLongitud.setText(String.valueOf(latLng.longitude));

        mMap.clear();
        LatLng UPN = new LatLng(latLng.latitude,latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(UPN).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(UPN));
    }
}
package com.example.borja.actividad_views;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener, RatingBar.OnRatingBarChangeListener{
    private Button btnFondo, btnLetras;
    private CheckBox mostrar;
    private TextView msjOculto, msjClick, valoracion;
    private LinearLayout zonaInferior;
    private RatingBar estrellas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos los elementos necesarios de la vista
        getViews();
        // Añadimos listeners a cada elemento que lo necesite
        setController();
    }

    private void getViews() {
        btnFondo = (Button)findViewById(R.id.btnFondoRojo);
        btnLetras = (Button)findViewById(R.id.btnLetrasNegras);
        mostrar = (CheckBox) findViewById(R.id.checkBox);
        msjOculto = (TextView) findViewById(R.id.txtOculto);
        msjClick = (TextView) findViewById(R.id.txtClickLargo);
        estrellas = (RatingBar) findViewById(R.id.ratingBar);
        valoracion = (TextView) findViewById(R.id.txtRating);
        zonaInferior = (LinearLayout)findViewById(R.id.zonaInferior);
    }

    private void setController() {
        btnFondo.setOnClickListener(this);
        btnLetras.setOnClickListener(this);
        mostrar.setOnClickListener(this);
        msjClick.setOnLongClickListener(this);
        estrellas.setOnRatingBarChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        int sourceId = v.getId();

        // Comprobamos que elemento ha producido el evento
        if (seleccionaBtnFondoRojo(sourceId)) {
            cambiaZonaInferior();
        } else if (seleccionaBtnLetrasNegras(sourceId)) {
            cambiarColorTextoBoton();
        } else if (seleccionaMostrar(sourceId)) {
            muestraTextoOculto();
        }
    }

    private boolean seleccionaBtnFondoRojo(int id) {
        return id == btnFondo.getId();
    }

    private boolean seleccionaBtnLetrasNegras(int id) {
        return id == btnLetras.getId();
    }

    private boolean seleccionaMostrar(int id) {
        return id == mostrar.getId();
    }

    private void cambiaZonaInferior() {
        // Intercambia el color del fondo de la zona inferior y el texto del boton
        if (btnFondo.getText().equals("FONDO ROJO")) {
            btnFondo.setText("FONDO BLANCO");
            zonaInferior.setBackgroundColor(Color.parseColor("red"));
        } else if (btnFondo.getText().equals("FONDO BLANCO")){
            btnFondo.setText("FONDO ROJO");
            zonaInferior.setBackgroundColor(Color.parseColor("#FAFAFA"));
        }
    }

    private void cambiarColorTextoBoton() {
        // Cambia el texto y el color de las letras del boton
        if (btnLetras.getText().equals("LETRAS NEGRAS")) {
            btnLetras.setText("LETRAS ROJAS");
            btnLetras.setTextColor(Color.parseColor("red"));
        } else if (btnLetras.getText().equals("LETRAS ROJAS")) {
            btnLetras.setText("LETRAS NEGRAS");
            btnLetras.setTextColor(Color.parseColor("black"));
        }
    }

    private void muestraTextoOculto() {
        // Muestra u oculta el texto
        if (mostrar.isChecked()) {
            msjOculto.setVisibility(View.VISIBLE);
        } else {
            msjOculto.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(MainActivity.this, "!Muchas gracias!",Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        float rate = estrellas.getRating();
        valoracion.setText("["+rate+"/5]");
    }
}
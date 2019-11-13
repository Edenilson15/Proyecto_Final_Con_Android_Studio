package com.eg.himnario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class Inicio extends AppCompatActivity {
private  Button btn_consultaNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_consultaNumero = findViewById(R.id.btn_consultaNumero);


        }

    public void numerohimno(View view){
        Intent i  = new Intent(this, NumeroHimno.class);
        startActivity(i);




    }



}

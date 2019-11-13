package com.eg.himnario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NumeroHimno extends AppCompatActivity {
    private Button btn_consultaNumero;
    private EditText et_codigo;
    boolean inputEt=false;

    MantenimientoMySQL manto = new MantenimientoMySQL();
    Dto datos = new Dto();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numero_himno);
        btn_consultaNumero = findViewById(R.id.btn_consultaNumero);
        et_codigo = findViewById(R.id.et_codigo);




        btn_consultaNumero.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                if(et_codigo.getText().toString().length()==0){
                    et_codigo.setError("Campo Obligatorio");
                    inputEt = false;
                }else {
                    inputEt=true;
                }
                if (inputEt){
                    String codigo = et_codigo.getText().toString();
                    manto.consultarNumero(NumeroHimno.this, codigo);
                    et_codigo.requestFocus();
                     mostrar();
                }
            }
        });


    }

    private void mostrar() {
        Intent i  = new Intent(this, mostrarNumero.class);
        startActivity(i);
    }
    }





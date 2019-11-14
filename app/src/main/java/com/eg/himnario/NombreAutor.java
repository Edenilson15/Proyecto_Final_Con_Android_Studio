package com.eg.himnario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NombreAutor extends AppCompatActivity {
    private Button btn_consultaporNombre;
    private EditText et_autor;
    boolean inputEd=false;

    MantenimientoMySQL manto = new MantenimientoMySQL();
    Dto datos = new Dto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombre_autor);
        btn_consultaporNombre = findViewById(R.id.btn_consultaporNombre);
        et_autor = findViewById(R.id.et_autor);

        btn_consultaporNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_autor.getText().toString().length()==1){
                    et_autor.setError("Campo Obligatorio");
                    inputEd=false;
                }else {
                    inputEd=true;
                }if (inputEd){
                    String autor = et_autor.getText().toString();
                    manto.consultarAutor(NombreAutor.this, autor);
                    et_autor.requestFocus();
                    mostrar();
                }

            }
        });

    }
    private void mostrar(){
        Intent i = new Intent(this,MostrarAutor.class);
        startActivity(i);

    }
}

package com.eg.himnario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class mostrarNumero extends AppCompatActivity {

    private EditText et_codigo, et_letra, et_genero,et_autor, et_nombre;
    String senal = "";
    String codigo = "";
    String letra = "";
    String nombre = "";
    String autor = "";
    String genero = "";

    MantenimientoMySQL manto = new MantenimientoMySQL();
    Dto datos = new Dto();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_numero);


        et_codigo = findViewById(R.id.et_codigo);
        et_letra = findViewById(R.id.et_letra);
        et_autor = findViewById(R.id.et_autor);
        et_nombre = findViewById(R.id.et_nombre);
        et_genero =  findViewById(R.id.et_genero);

        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if (bundle != null) {

                senal = bundle.getString("senal");
                codigo = bundle.getString("codigo");
                letra = bundle.getString("letra");
                nombre = bundle.getString("nombre");
                genero = bundle.getString("genero");
                autor = bundle.getString("autor");

                if (senal.equals("1")) {
                    et_codigo.setText(codigo);
                    et_letra.setText(letra);
                    et_nombre.setText(nombre);
                    et_autor.setText(autor);
                    et_genero.setText(genero);
                    //finish();
                }else if(senal.equals("2")){

                }
            }
        }catch (Exception e){

        }

    }
}

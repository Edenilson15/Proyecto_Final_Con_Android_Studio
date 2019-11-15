package com.eg.himnario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class mostrarNumero extends AppCompatActivity {
private TextView tvtitulo,tvletra,tvnumero;
    private EditText et_codigo;
    String senal = "";
    String codigo = "";


    MantenimientoMySQL manto = new MantenimientoMySQL();
    Dto datos = new Dto();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_numero);


        et_codigo = findViewById(R.id.et_codigo);
        tvtitulo = findViewById(R.id.tvtitulo);
        tvletra = findViewById(R.id.tvletra);
        tvnumero = findViewById(R.id.tvnumero);


        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if (bundle != null) {

                senal = bundle.getString("senal");
                codigo = bundle.getString("codigo");


                if (senal.equals("1")) {
                    et_codigo.setText(codigo);

                    //finish();
                }else if(senal.equals("2")){

                }
            }
        }catch (Exception e){

        }

    }
}

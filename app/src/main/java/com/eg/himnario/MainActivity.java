package com.eg.himnario;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {
    private EditText et_codigo, et_letra, et_genero,et_autor, et_nombre;
    private Button btn_guardar, btn_consultaCodigo, btn_consultaDescripcion, btn_eliminar, btn_actualizar;

    boolean inputEt=false;
    boolean inputEd=false;
    boolean input1=false;
    boolean input2=false;
    boolean input3=false;
    int resultadoInsert=0;

    String senal = "";
    String codigo = "";
    String letra = "";
    String nombre = "";
    String autor = "";
    String genero = "";

    MantenimientoMySQL manto = new MantenimientoMySQL();
    Dto datos = new Dto();

    //Banderas para saber estados de métodos del CRUD.
    boolean estadoGuarda = false;
    boolean estadoEliminar = false;

    AlertDialog.Builder dialogo;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new android.app.AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_close)
                    .setTitle("Advertencia")
                    .setMessage("¿Realmente desea salir?")
                    .setNegativeButton(android.R.string.cancel, null)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.finishAffinity();
                        }
                    })
                    .show();
            return true;
        }
        //para las demas cosas, se reenvia el evento al listener habitual
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setTitleTextColor(getResources().getColor(R.color.mycolor1));
        toolbar.setTitleMargin(0, 0, 0, 0);
        toolbar.setSubtitle("Himnario");
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.mycolor));
        toolbar.setTitle("Encuentra Todas las Alavanzas aqui");
        setSupportActionBar(toolbar);

        ///y esto para pantalla completa (oculta incluso la barra de estado)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        et_codigo = findViewById(R.id.et_codigo);
        et_letra = findViewById(R.id.et_letra);
        et_autor = findViewById(R.id.et_autor);
        et_nombre = findViewById(R.id.et_nombre);
        et_genero =  findViewById(R.id.et_genero);
        btn_guardar =  findViewById(R.id.btn_guardar);
       // btn_consultaCodigo = (Button) findViewById(R.id.btn_consultaCodigo);
        btn_consultaDescripcion = findViewById(R.id.btn_consultaDescripcion);
        btn_eliminar = findViewById(R.id.btn_eliminar);
        btn_actualizar = findViewById(R.id.btn_actualizar);
        //tv_resultado = (TextView) findViewById(R.id.tv_resultado);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogConfirmacion();
            }
        });


        /******************************************************************/
        //BLOQUE DE CÓDIGO PARA MOSTRAR DATOS DE LA BUSQUEDA//
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
        /******************************************************************/



        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(et_codigo.getText().toString().length()==0){
                    et_codigo.setError("Campo obligatorio");
                    inputEt = false;
                }else {
                    inputEt=true;
                }
                if(et_letra.getText().toString().length()==0){
                    et_letra.setError("Campo obligatorio");
                    inputEd = false;
                }else {
                    inputEd=true;
                }
                if(et_autor.getText().toString().length()==0){
                    et_autor.setError("Campo obligatorio");
                    input1 = false;
                }else {
                    input1=true;
                }
                if(et_nombre.getText().toString().length()==0){
                    et_nombre.setError("Campo obligatorio");
                    input3 = false;
                }else {
                    input3=true;
                }
                if(et_genero.getText().toString().length()==0){
                    et_genero.setError("Campo obligatorio");
                    input2 = false;
                }else {
                    input2=true;
                }

                if (inputEt && inputEd && input1 && input2&& input3){
                    String codigo = et_codigo.getText().toString();
                    String letra = et_letra.getText().toString();
                    String autor = et_autor.getText().toString();
                    String nombre = et_nombre.getText().toString();
                    String genero = et_genero.getText().toString();
                    manto.guardar(MainActivity.this, codigo, letra, autor,nombre, genero);

                    limpiarDatos();
                    et_codigo.requestFocus();

                    /*
                    estadoGuarda = manto.guardar1(MainActivity.this, codigo, descripcion, precio);
                    if(estadoGuarda){
                        Toast.makeText(MainActivity.this, "Registro Almacenado Correctamente.", Toast.LENGTH_SHORT).show();
                        limpiarDatos();
                        et_codigo.requestFocus();
                    }*/

                }


            }
        });


        //Evento clic del botón eliminar.
        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_codigo.getText().toString().length()==0){
                    et_codigo.setError("campo obligatorio");
                    inputEt = false;
                }else {
                    inputEt=true;
                }

                if(inputEt){
                    String codigo = et_codigo.getText().toString();
                    manto.eliminar(MainActivity.this, codigo);

                    limpiarDatos();
                    et_codigo.requestFocus();
                    /*
                    if(estadoEliminar){
                        Toast.makeText(MainActivity.this, "Registro Eliminado correctamente.", Toast.LENGTH_SHORT).show();
                        limpiarDatos();
                    }else{
                         Toast toast = Toast.makeText(getApplicationContext(), "--> Nothing." +
                                        "\nNo hay información que eliminar.", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                        limpiarDatos();
                    }*/
                }
            }
        });


      /*  btn_consultaCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Begin...
                if(et_codigo.getText().toString().length()==0){
                    et_codigo.setError("campo obligatorio");
                    inputEt = false;
                }else {
                    inputEt=true;
                }

                if(inputEt) {
                    String codigo = et_codigo.getText().toString();
                    manto.consultarCodigo(MainActivity.this, codigo);
                    et_codigo.requestFocus();
                }
                //End

            }
        });

*/

        btn_consultaDescripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(et_letra.getText().toString().length()==0){
                    et_letra.setError("Campo obligatorio");
                    inputEd = false;
                }else {
                    inputEd=true;
                }
                if(inputEd){
                    String descripcion = et_letra.getText().toString();
                    //datos.setDescripcion(descripcion);
                    manto.consultarDescripcion(MainActivity.this, descripcion);
                    et_letra.requestFocus();
                    //Hilo();

                }

            }
        });


        btn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(et_codigo.getText().toString().length()==0){
                    et_codigo.setError("campo obligatorio");
                    inputEt = false;
                }else {
                    inputEt=true;
                }

                if(inputEt) {

                    String cod = et_codigo.getText().toString();
                    String letra = et_letra.getText().toString();
                    String autor = et_autor.getText().toString();
                    String nombre = et_nombre.getText().toString();
                    String genero = et_genero.getText().toString();

                    datos.setCodigo(Integer.parseInt(cod));
                    datos.setLetra(letra);
                    datos.setAutor(autor);
                    datos.setNombre(nombre);
                    datos.setGenero(genero);

                    manto.modificar(MainActivity.this, datos);
                    limpiarDatos();
                    et_codigo.requestFocus();
                }

            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Que Buscas", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    public void limpiarDatos(){
        et_codigo.setText(null);
        et_letra.setText(null);
        et_autor.setText(null);
        et_nombre.setText(null);
        et_genero.setText(null);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_limpiar) {
            et_codigo.setText(null);
            et_letra.setText(null);
            et_autor.setText(null);
            et_nombre.setText(null);
            et_genero.setText(null);
            return true;
        }else if(id == R.id.action_listaArticulos){
            Intent spinnerActivity = new Intent(MainActivity.this, Consulta_RecyclerView.class);
            startActivity(spinnerActivity);
        return true;
        }else if(id == R.id.action_salir){
            DialogConfirmacion();
            return true;
        }else if(id == R.id.action_guardar){
            Intent spinnerActivity = new Intent(MainActivity.this, MainActivity.class);
            startActivity(spinnerActivity);
            return true;
        }else if(id == R.id.action_Inicio){
            Intent spinnerActivity = new Intent(MainActivity.this, Inicio.class);
            startActivity(spinnerActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    @Override
    public void onSetDatosInput(Dto datos) {
        //Toast.makeText(this, "Código: "+datos.getCodigo(), Toast.LENGTH_SHORT).show();
    }*/


    private void DialogConfirmacion(){
        //startActivity(new Intent(getApplicationContext(),MainActivity.class));
        String mensaje = "¿Realmente desea salir?";
        dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setIcon(R.drawable.ic_close);
        dialogo.setTitle("Advertencia");
        dialogo.setMessage(mensaje);
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                /*Intent intent = new Intent(DashboardLuces.this, luces_control_sms.class);
                startActivity(intent);*/
                MainActivity.this.finishAffinity();
                //MainActivity.this.finish();
            }
        });
        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                Toast.makeText(getApplicationContext(), "Operación Cancelada.", Toast.LENGTH_LONG).show();
            }
        });
        dialogo.show();
    }


    //Creación de HILOS
    void Hilo(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<=1; i++){
                    demora();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String cod = getSharedCodigo(MainActivity.this);
                        String le = getSharedLetra(MainActivity.this);
                        String au = getSharedAutor(MainActivity.this);
                        String nom = getSharedNombre(MainActivity.this);
                        String gene = getSharedGenero(MainActivity.this);

                        et_codigo.setText(cod);
                        et_letra.setText(le);
                        et_autor.setText(au);
                        et_genero.setText(gene);

                        //Toast.makeText(MainActivity.this, "Código: "+cod + "\nPrecio: "+pre + "\nDescripción: "+des, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }


    private void demora(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){}
    }


    public String getSharedCodigo(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Himnario", MODE_PRIVATE);
        String codigo = preferences.getString("codigo","0");
        return codigo;   //return preferences.getString("tiempo", "Sin configurar.");
    }

    public String getSharedLetra(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Himnario", MODE_PRIVATE);
        String letra = preferences.getString("letra","Sin letra");
        return letra;   //return preferences.getString("tiempo", "Sin configurar.");
    }

    public String getSharedAutor(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Himnario", MODE_PRIVATE);
        String autor = preferences.getString("autor","Sin letra");
        return autor;   //return preferences.getString("tiempo", "Sin configurar.");
    }
    public String getSharedGenero(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Himnario", MODE_PRIVATE);
        String genero = preferences.getString("genero","Sin letra");
        return genero;   //return preferences.getString("tiempo", "Sin configurar.");
    }
    public String getSharedNombre(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Himnario", MODE_PRIVATE);
        String nombre = preferences.getString("nombre","Sin letra");
        return nombre;   //return preferences.getString("tiempo", "Sin configurar.");
    }


}

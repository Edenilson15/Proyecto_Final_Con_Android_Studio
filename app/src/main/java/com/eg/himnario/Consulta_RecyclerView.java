package com.eg.himnario;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Consulta_RecyclerView extends AppCompatActivity {

    //private static final String URL = "http://mjgl.com.sv/mysqlcrud/Api.php";
    private static final String URL = Config.urlConsultaApiMySQLi;

    List<Productos> productosList;
    RecyclerView recyclerView;

    ProductsAdapter adapter;

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
                            finish();
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
        setContentView(R.layout.activity_consulta__recycler_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setTitleTextColor(getResources().getColor(R.color.mycolor1));
        toolbar.setTitleMargin(0, 0, 0, 0);
        toolbar.setSubtitle("LIsta de Alabanzas");
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.mycolor));
        toolbar.setTitle("Himnario");
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogConfirmacion();
            }
        });

        ///y esto para pantalla completa (oculta incluso la barra de estado)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productosList = new ArrayList<>();



        //Toast.makeText(this, "Si", Toast.LENGTH_SHORT).show();

        loadProductos();

    }


    private void loadProductos() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //Toast.makeText(Consulta_RecyclerView.this, ""+response, Toast.LENGTH_SHORT).show();

                        try {
                            JSONArray array = new JSONArray(response);
                            int totalEncontrados = array.length();
                            Toast.makeText(Consulta_RecyclerView.this, "Total: "+totalEncontrados, Toast.LENGTH_SHORT).show();

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject articulosObject = array.getJSONObject(i);

                                //String img = articulosObject.getString("imagen");
                                //Toast.makeText(Consulta_RecyclerView.this, ""+img, Toast.LENGTH_SHORT).show();

                                /*int codigo = articulosObject.getInt("codigo");
                                String descripcion = articulosObject.getString("descripcion");
                                double precio = articulosObject.getDouble("precio");
                                String img = articulosObject.getString("imagen");
                                Productos objeto = new Productos(codigo, descripcion, precio, img);
                                productosList.add(objeto);*/

                                productosList.add(new Productos(
                                        articulosObject.getInt("codigo"),
                                        articulosObject.getString("letra"),
                                        articulosObject.getString("autor"),
                                        articulosObject.getString("nombre"),
                                        articulosObject.getString("genero"),
                                        articulosObject.getString("imagen")
                                ));
                            }

                            adapter = new ProductsAdapter(Consulta_RecyclerView.this, productosList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Consulta_RecyclerView.this, "Error. Compruebe su acceso a Internet.", Toast.LENGTH_SHORT).show();
            }
        });

        //Volley.newRequestQueue(this).add(stringRequest);
       // MySingleton.getInstance(this).addToRequestQueue(stringRequest);
        MySingleton.getInstance(Consulta_RecyclerView.this).addToRequestQueue(stringRequest);
    }


    private void DialogConfirmacion(){
        //startActivity(new Intent(getApplicationContext(),MainActivity.class));
        String mensaje = "¿Realmente desea salir?";
        dialogo = new AlertDialog.Builder(Consulta_RecyclerView.this);
        dialogo.setIcon(R.drawable.ic_close);
        dialogo.setTitle("Advertencia");
        dialogo.setMessage(mensaje);
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                /*Intent intent = new Intent(DashboardLuces.this, luces_control_sms.class);
                startActivity(intent);*/
                //MainActivity.this.finishAffinity();
                finish();
            }
        });
        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                Toast.makeText(getApplicationContext(), "Operación Cancelada.", Toast.LENGTH_LONG).show();
            }
        });
        dialogo.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
       if(id == R.id.action_salir){
            DialogConfirmacion();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




}

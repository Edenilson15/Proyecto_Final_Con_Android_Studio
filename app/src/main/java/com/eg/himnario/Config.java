package com.eg.himnario;

public class Config {

    /*
    //URL PARA ACCESO REMOTA A BASE DE DATOS MYSQL DEL SERVIDOR DEL PROF. GÁMEZ EN LA NUBE.
    public static final String urlGuardar = "http://mjgl.com.sv/mysqlcrud/guardar.php";                               //Guardar datos DB.
    public static final String urlEliminar = "http://mjgl.com.sv/mysqlcrud/eliminar.php";                             //Eliminar datos DB.
    public static final String urlActualizar = "http://mjgl.com.sv/mysqlcrud/actualizar.php";                          //Actualizar datos DB.

    public static final String urlConsultaApiMySQLi = "http://mjgl.com.sv/mysqlcrud/Api.php";                          //Ver todos los registros DB.
    public static final String urlConsultaApiPDO = "http://mjgl.com.sv/mysqlcrud/buscarAll.php";                       //Ver todos los registros DB.

    public static final String urlConsultaCodigo = "http://mjgl.com.sv/mysqlcrud/buscarArticulosCodigo.php";            //Busquedas por código.
    public static final String urlConsultaDescripcion = "http://mjgl.com.sv/mysqlcrud/buscarArticulosDescripcion.php";  //Busquedas por descripción.
    public static final String urlConsultaAllArticulos = "http://mjgl.com.sv/mysqlcrud/buscarArticulos.php";            //Devuelve todos los registros de la tabla MySQL.
    */

    //URL PARA ACCESO A BASE DE DATOS LOCAL MYSQL.
    //public static final String urlGuardar = "http://localhost/conexion/guardar.php";                               //Guardar datos DB.
    public static final String urlGuardar = "http://192.168.43.135/conexion/guardar.php";                               //Guardar datos DB.

    public static final String urlEliminar = "http://192.168.43.135/conexion/eliminar.php";                             //Eliminar datos DB.
    public static final String urlActualizar = "http://192.168.43.135/conexion/actualizar.php";                          //Actualizar datos DB.

    public static final String urlConsultaApiMySQLi = "http://192.168.43.135/conexion/Api.php";                          //Ver todos los registros DB.
    public static final String urlConsultaApiPDO = "http://192.168.43.135/conexion/buscarAll.php";                       //Ver todos los registros DB.

    public static final String urlConsultaCodigo = "http://192.168.43.135/conexion/buscarCodigo.php";            //Busquedas por código.

   public static final String urlbuscarhimnario = "http://192.168.43.135/conexion/buscarhimnario.php";//Busquedas por el nombre de la Cancion.
    public static final String urlConsultaAllArticulos = "http://192.168.43.135/conexion/buscarArticulos.php";            //Devuelve todos los registros de la tabla MySQL.


}

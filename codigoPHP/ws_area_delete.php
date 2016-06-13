<?php
error_reporting(E_ALL ^ E_DEPRECATED);
$IDAREA=$_REQUEST['idArea'];

$servidor="localhost";
$usuario="pdm115";
$password="pdm115";
$respuesta=array('resultado'=>0);
json_encode($respuesta);
$conexion=mysql_connect($servidor,$usuario,$password) or
die ("Problemas en la conexion");
$baseDatos="poliues24";
mysql_select_db($baseDatos,$conexion)
  or  die("Problemas en la seleccion de la base de datos");
$query = "DELETE FROM AREA WHERE IDAREA = ".$IDAREA.";";

$resultado = mysql_query($query) or die(mysql_error());
//Si la respuesta es correcta enviamos 1 y sino enviamos 0
if(mysql_affected_rows() == 1)
   $respuesta=array('resultado'=>1);
echo json_encode($respuesta);
mysql_close($conexion);
?>

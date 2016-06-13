<?php
error_reporting(E_ALL ^ E_DEPRECATED);
$servidor="localhost";
$usuario="pdm115";
$password="pdm115";
$conexion=mysql_connect($servidor,$usuario,$password) or
die ("Problemas en la conexion");
$baseDatos="poliues24";
mysql_select_db($baseDatos,$conexion) 
  or  die("Problemas en la selecciÃ³n de la base de datos");
 $registros=mysql_query("SELECT MAX(IDRESERVA) AS id FROM reserva;",$conexion) or
  die("Problemas en el select:".mysql_error());
 $filas=array();
while ($reg=mysql_fetch_assoc($registros))
{
 $filas[]=$reg;
}
echo json_encode($filas);
mysql_close($conexion);
?> 
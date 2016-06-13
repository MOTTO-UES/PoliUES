<?php
error_reporting(E_ALL ^ E_DEPRECATED);
$idreserva=$_REQUEST['idreserva'];
$idfacultad=$_REQUEST['idfacultad'];
$fechaingreso=$_REQUEST['fechaingreso'];
$numeropersonas=$_REQUEST['numeropersonas'];
$motivo=$_REQUEST['motivo'];
$descripcionreserva=$_REQUEST['descripcionreserva'];
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
$query = "UPDATE RESERVA SET IDFACULTAD='".$idfacultad."',FECHAINGRESO='".$fechaingreso."',NUMEROPERSONAS='".$numeropersonas."',MOTIVO='".$motivo."',DESCRIPCIONRESERVA='".$descripcionreserva."' WHERE IDRESERVA='".$idreserva."';";
//echo($query);
$resultado = mysql_query($query) or die(mysql_error());
//Si la respuesta es correcta enviamos 1 y sino enviamos 0
if(mysql_affected_rows() == 1)
   $respuesta=array('resultado'=>1);
echo json_encode($respuesta);
mysql_close($conexion);
?> 
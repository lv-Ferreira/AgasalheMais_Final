<?php
header('Content-Type: text/html; charset=utf-8');


$response = array();
$response["erro"] = true;

if ($_SERVER['REQUEST_METHOD'] == 'POST'){
	$response["erro"] = false;

	include 'dbOpenCon.php';

	$conn = new mysqli($HostName, $HostUser, $HostPass, $DatabaseName);
	mysqli_set_charset($conn, "utf8");

	if($conn->connect_error){
		die("Ops, falhou...:".$conn->connect_error);
	}

	$result = "conectado com sucesso!";
	
	
$conn->close();

}
echo json_encode($result);


?>
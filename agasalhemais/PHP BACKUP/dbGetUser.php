<?php
header('Content-Type: text/html; charset=utf-8');


$response = array();
$response["cadastrado"] = true;

if ($_SERVER['REQUEST_METHOD'] == 'POST'){
	$response["cadastrado"] = false;

	include 'dbOpenCon.php';

	$conn = new mysqli($HostName, $HostUser, $HostPass, $DatabaseName);
	mysqli_set_charset($conn, "utf8");

	if($conn->connect_error){
		die("Ops, falhou...:".$conn->connect_error);
	}

	$login = "'".$_POST['login']."'";

	$sql = "SELECT usuario FROM usuarios WHERE usuario = $login";

	$result = $conn->query($sql);

	//print_r($result);

	if($result->num_rows > 0){

		$registro = mysqli_fetch_array($result);
		$response["mensagem"] = "Usuario Cadastrado";
		$response["cadastrado"] = true;
	} else {
		$response["mensagem"] = "Usuario nao existe";
		$response["cadastrado"] = false;
	}

$conn->close();

}
echo json_encode($response);


?>
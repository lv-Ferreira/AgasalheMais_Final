<?php
header('Content-Type: text/html; charset=utf-8');


$response = array();
$response["email"] = true;

if ($_SERVER['REQUEST_METHOD'] == 'POST'){
	$response["email"] = false;

	include 'dbOpenCon.php';

	$conn = new mysqli($HostName, $HostUser, $HostPass, $DatabaseName);
	mysqli_set_charset($conn, "utf8");

	if($conn->connect_error){
		die("Ops, falhou...:".$conn->connect_error);
	}

	$Email = "'".$_POST['Email']."'";

	$sql = "SELECT email FROM usuarios WHERE email = $Email";

	$result = $conn->query($sql);

	//print_r($result);

	if($result->num_rows > 0){

		$registro = mysqli_fetch_array($result);
		$response["mensagem"] = "Email Cadastrado";
		$response["email"] = true;
	} else {
		$response["mensagem"] = "Email nao existe";
		$response["email"] = false;
	}

$conn->close();

}
echo json_encode($response);


?>
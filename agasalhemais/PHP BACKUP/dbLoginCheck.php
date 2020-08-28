<?php
header('Content-Type: text/html; charset=utf-8');


$response = array();
$response["login"] = true;

if ($_SERVER['REQUEST_METHOD'] == 'POST'){
	$response["login"] = false;

	include 'dbOpenCon.php';

	$conn = new mysqli($HostName, $HostUser, $HostPass, $DatabaseName);
	mysqli_set_charset($conn, "utf8");

	if($conn->connect_error){
		die("Ops, falhou...:".$conn->connect_error);
	}

	$email = "'".$_POST['email']."'";
	$senha = "'".$_POST['senha']."'";

	$sql = "SELECT * FROM usuarios WHERE email = $email
					 AND senha = $senha";

	$result = $conn->query($sql);

	//print_r($result);


	if($result->num_rows > 0){
		$registro = mysqli_fetch_array($result);
		$response["login"] = true;
		
	} else {
		$response["mensagem"] = "Usuario nao existe";
	}

$conn->close();

}
echo json_encode($response);


?>
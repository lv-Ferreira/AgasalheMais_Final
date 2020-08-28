<?php
header('Content-Type: text/html; charset=utf-8');


$response = array();
$response["status"] = true;

if ($_SERVER['REQUEST_METHOD'] == 'POST'){
	$response["status"] = false;

	include 'dbOpenCon.php';

	$conn = new mysqli($HostName, $HostUser, $HostPass, $DatabaseName);
	mysqli_set_charset($conn, "utf8");

	if($conn->connect_error){
		die("Ops, falhou...:".$conn->connect_error);
	}

	$Login = "'".$_POST['Login']."'";
	$Email = "'".$_POST['Email']."'";
	$Senha = "'".$_POST['Senha']."'";

	$sql = "INSERT INTO usuarios(usuario, email, senha) VALUES ($Login, $Email, $Senha)";

	//$result = $conn->query($sql);
	
	if ($conn->query($sql) === TRUE) {
		echo "Nova entrada com sucesso!";
		$response["status"] = true;
	} else {
		echo "Erro: " . $sql . "<br>" . $conn->error;
		$response["status"] = false;
	}
	

	//print_r($result);

$conn->close();

}
echo json_encode($response);


?>
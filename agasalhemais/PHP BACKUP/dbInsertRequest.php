<?php
header('Content-Type: text/html; charset=utf-8');



if ($_SERVER['REQUEST_METHOD'] == 'POST'){

	include 'dbOpenCon.php';

	$conn = new mysqli($HostName, $HostUser, $HostPass, $DatabaseName);
	mysqli_set_charset($conn, "utf8");

	if($conn->connect_error){
		die("Ops, falhou...:".$conn->connect_error);
	}

	$Usuario = "'".$_POST['Usuario']."'";
	$Produto = "'".$_POST['Produto']."'";
	$Variacao = "'".$_POST['Variacao']."'";
	$Data_pedido = "'".$_POST['Data_pedido']."'";
	$Id_pedido = "'".$_POST['Id_pedido']."'";
	$Doador = "'".$_POST['Doador']."'";

	$sql = "INSERT INTO pedidospendentes (ticket, usuario, produto, variacao, data_pedido, id_pedido, doador) VALUES (NULL, $Usuario, $Produto, $Variacao, $Data_pedido, $Id_pedido, $Doador)";

	//$result = $conn->query($sql);
	//$response = mysqli_fetch_all ($result, MYSQLI_ASSOC);
	
	if ($conn->query($sql) === TRUE) {
		echo "New record created successfully";
		$response["record"] = true;
	} else {
		$response["record"] = false;
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
	

$conn->close();

}
echo json_encode($response);


?>
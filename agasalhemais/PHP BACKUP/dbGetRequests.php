<?php
header('Content-Type: text/html; charset=utf-8');


$pedidos = array();
if ($_SERVER['REQUEST_METHOD'] == 'POST'){
	

	include 'dbOpenCon.php';

	$conn = new mysqli($HostName, $HostUser, $HostPass, $DatabaseName);
	mysqli_set_charset($conn, "utf8");

	if($conn->connect_error){
		die("Ops, falhou...:".$conn->connect_error);
	}

	$sql = "SELECT * FROM pedidosabertos ORDER BY id";

	$result = $conn->query($sql);
	
	$response = mysqli_fetch_all ($result, MYSQLI_ASSOC);
	//echo json_encode($response);
	
	if($result->num_rows > 0){
		$response["rows"] = $result->num_rows;
	}

$conn->close();

}
echo json_encode($response);


?>
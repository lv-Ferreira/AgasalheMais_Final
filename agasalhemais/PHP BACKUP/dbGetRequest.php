<?php
header('Content-Type: text/html; charset=utf-8');



if ($_SERVER['REQUEST_METHOD'] == 'POST'){

	include 'dbOpenCon.php';

	$conn = new mysqli($HostName, $HostUser, $HostPass, $DatabaseName);
	mysqli_set_charset($conn, "utf8");

	if($conn->connect_error){
		die("Ops, falhou...:".$conn->connect_error);
	}

	$ID = "'".$_POST['ID']."'";

	$sql = "SELECT * FROM pedidosabertos WHERE id = $ID";

	$result = $conn->query($sql);
	
	$response = mysqli_fetch_all ($result, MYSQLI_ASSOC);
	//$response = mysqli_fetch_array($result);
	//echo json_encode($response);
	

$conn->close();

}
echo json_encode($response);


?>
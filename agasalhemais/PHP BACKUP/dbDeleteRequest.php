<?php
header('Content-Type: text/html; charset=utf-8');



if ($_SERVER['REQUEST_METHOD'] == 'POST'){

	include 'dbOpenCon.php';

	$conn = new mysqli($HostName, $HostUser, $HostPass, $DatabaseName);
	mysqli_set_charset($conn, "utf8");

	if($conn->connect_error){
		die("Ops, falhou...:".$conn->connect_error);
	}

	$Id = "'".$_POST['Id']."'";

	$sql = "DELETE FROM pedidosabertos WHERE id = $Id;";

	//$result = $conn->query($sql);
	//$response = mysqli_fetch_all ($result, MYSQLI_ASSOC);
	
	if ($conn->query($sql) === TRUE) {
		echo "Delet successfully";
		$response["delete"] = true;
	} else {
		$response["delete"] = false;
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
	

$conn->close();

}
echo json_encode($response);


?>
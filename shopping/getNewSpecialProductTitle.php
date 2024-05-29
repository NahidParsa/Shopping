<?php

include 'connection.php';

$query = "SELECT * FROM title WHERE place = 'home_fragment_special_product_new' LIMIT 1 ";
$stmt = $conn->prepare($query);
$stmt->execute();

$titleNew = array();


while($row = $stmt -> fetch(PDO::FETCH_ASSOC)){
	
	$title_new_special = array();
	$title_new_special['id']= $row['id'];
	$title_new_special['title_text']= $row['title_text'];
	$titleNew[]=  $title_new_special;
	
}

echo json_encode($titleNew);


?>
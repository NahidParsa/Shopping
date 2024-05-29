<?php

// khandan tamam etelaat az jadval wall ba limit 
include 'connection.php';

$query = "SELECT * FROM wall WHERE type = 'amazingOfferWall' LIMIT 1 ";
$stmt = $conn->prepare($query);
$stmt->execute();

$wall = array();

while($row = $stmt -> fetch(PDO::FETCH_ASSOC)){
	
	$wall_list = array();
	$wall_list['id']= $row['id'];
	$wall_list['title']= $row['title'];
	$wall_list['link_img']= $row['link_img'];
	$wall[]=$wall_list;
	
	
}
echo json_encode($wall);


?>
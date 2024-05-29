<?php
include 'connection.php';

$query = "SELECT * FROM pagermodel WHERE pager_place = 'middle' LIMIT 4 ";
$stmt = $conn->prepare($query);
$stmt->execute();

$pager = array();

while($row = $stmt -> fetch(PDO::FETCH_ASSOC)){
	
	$slider = array();
	$slider['id']= $row['id'];
	$slider['link_img']= $row['link_img'];
	$pager[]=$slider;
	
}
echo json_encode($pager);



?>
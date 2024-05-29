<?php

// khandan tamam etelaat az jadval caegory ba limit 
include 'connection.php';

$query = "SELECT * FROM category";
$stmt = $conn->prepare($query);
$stmt->execute();


$category = array();

while($row = $stmt -> fetch(PDO::FETCH_ASSOC)){
	
	$caegory_list = array();
	$caegory_list['id']= $row['id'];
	$caegory_list['title']= $row['title'];
	$caegory_list['link_img']= $row['link_img'];
	$category[]=$caegory_list;
	
	
}
echo json_encode($category);


?>
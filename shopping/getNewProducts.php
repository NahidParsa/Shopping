<?php

// khandan  etelaat amazing offer az jadval product ba limit 
include 'connection.php';

$query = "SELECT * FROM product WHERE new = '1' LIMIT 6";
$stmt = $conn->prepare($query);
$stmt->execute();


$product = array();

while($row = $stmt-> fetch(PDO::FETCH_ASSOC)){
	
	$product_list = array();
	

	
	$product_list['id']= $row['id'];
	$product_list['name']= $row['name'];
	$product_list['link_img']= $row['link_img'];
	$product_list['price']= $row['price'];

	
	$product[]=$product_list;
	
	
}
echo json_encode($product);


?>
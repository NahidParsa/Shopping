<?php

include 'connection.php';

$id = @$_GET["id"];

$query = "SELECT * FROM category WHERE id = '$id' ";

$stmt = $conn->prepare($query);
$stmt ->bindParam('$id', $id);
$stmt->execute();

while($row = $stmt -> fetch(PDO::FETCH_ASSOC)){		
	$rec["id"] = $row["id"];	
}

$query_a = "SELECT * FROM product WHERE catogory_id = '$id'  AND  popular = '1'";

$stmt_a = $conn->prepare($query_a);
$stmt_a ->bindParam('$id', $rec["id"]);
$stmt_a->execute();

$category = array();

while($row_a = $stmt_a -> fetch(PDO::FETCH_ASSOC)){
	

	$product_list['id']= $row_a['id'];
	$product_list['catogory_id']= $row_a['catogory_id'];
	$product_list['name']= $row_a['name'];
	$product_list['link_img']= $row_a['link_img'];
	$product_list['price']= $row_a['price'];

	$category[]=$product_list;
}

echo json_encode($category);
?>
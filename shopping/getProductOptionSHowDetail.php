<?php
include 'connection.php';

$id = @$_GET["id"];

$query = "SELECT * FROM product WHERE id = '$id' ";

$stmt = $conn->prepare($query);
$stmt ->bindParam('$id', $id);
$stmt->execute();

while($row = $stmt -> fetch(PDO::FETCH_ASSOC)){		
	$rec["id"] = $row["id"];	
}

$query_a = "SELECT * FROM product_option WHERE product_id = '$id' ";

$stmt_a = $conn->prepare($query_a);
$stmt_a ->bindParam('$id', $rec["id"]);
$stmt_a->execute();

$category = array();

while($row_a = $stmt_a -> fetch(PDO::FETCH_ASSOC)){
		$product_list = array();
	
	$product_list['id']= $row_a['id'];
	$product_list['product_id']= $row_a['product_id'];
	$product_list['description']= $row_a['description'];
	
	$category[]=$product_list;

}

echo json_encode($category);
?>
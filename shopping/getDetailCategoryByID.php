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

$query_a = "SELECT * FROM category_details WHERE catogory_id = '$id' ";

$stmt_a = $conn->prepare($query_a);
$stmt_a ->bindParam('$id', $rec["id"]);
$stmt_a->execute();


$category = array();

while($row_a = $stmt_a -> fetch(PDO::FETCH_ASSOC)){
		$caegory_list = array();

	$caegory_list['id']= $row_a['id'];
	$caegory_list['catogory_id']= $row_a['catogory_id'];
	$caegory_list['title']= $row_a['title'];
	$caegory_list['link_img']= $row_a['link_img'];
	$category[]=$caegory_list;
		
}

echo json_encode($category);


?>
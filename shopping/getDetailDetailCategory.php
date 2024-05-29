<?php

include 'connection.php';

$id = @$_GET["id"];

$query = "SELECT * FROM category_details WHERE id = '$id' ";

$stmt = $conn->prepare($query);
$stmt ->bindParam('$id', $id);
$stmt->execute();

while($row = $stmt -> fetch(PDO::FETCH_ASSOC)){		
	$rec["id"] = $row["id"];	
}

$query_a = "SELECT * FROM product WHERE detail_category_id = '$id' ";

$stmt_a = $conn->prepare($query_a);
$stmt_a ->bindParam('$id', $rec["id"]);
$stmt_a->execute();

$category = array();

while($row_a = $stmt_a -> fetch(PDO::FETCH_ASSOC)){
		$product_list = array();
	// bedast avardan mablaghe takhfif
	//offpercentage ke yek adad bein 0 ta 100 ast
	$offpercentage = $row_a['off_percentage']; 
	// gheymat asli bedon takhfif
	$rawprice = $row_a['price'];
	// meghdar takhfif ke kam khahad shod
	$minusprice = ($offpercentage * $rawprice) / 100 ;
	
	if($offpercentage == 0 ){
		$minusprice = 0;
	}
		// mablagh bad az kasr tkhfif shegeft angiz
	$pricewithoff = $rawprice - $minusprice;
	$product_list['discount_price']= $pricewithoff;
	
	$product_list['id']= $row_a['id'];
	$product_list['catogory_id']= $row_a['catogory_id'];
	$product_list['detail_category_id']= $row_a['detail_category_id'];
	$product_list['name']= $row_a['name'];
	$product_list['link_img']= $row_a['link_img'];
	$product_list['price']= $row_a['price'];
	$product_list['off_percentage']= $row_a['off_percentage'];
	$product_list['brand']= $row_a['brand'];
	$product_list['stock']= $row_a['stock'];

	
	$category[]=$product_list;

}

echo json_encode($category);
?>
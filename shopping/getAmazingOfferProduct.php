<?php

// khandan  etelaat amazing offer az jadval product ba limit 
include 'connection.php';

$query = "SELECT * FROM product WHERE product_off = '1' LIMIT 5";
$stmt = $conn->prepare($query);
$stmt->execute();


$product = array();

while($row = $stmt-> fetch(PDO::FETCH_ASSOC)){
	
	$product_list = array();

	// bedast avardan mablaghe takhfif
	//offpercentage ke yek adad bein 0 ta 100 ast
	$offpercentage = $row['off_percentage']; 
	// gheymat asli bedon takhfif
	$rawprice = $row['price'];
	// meghdar takhfif ke kam khahad shod
	$minusprice = ($offpercentage * $rawprice) / 100 ;

	if($offpercentage == 0 ){
		$minusprice = 0;
	}
		// mablagh bad az kasr tkhfif shegeft angiz
	$pricewithoff = $rawprice - $minusprice;
	
	$product_list['discount_price']= $pricewithoff;
	
	$product_list['id']= $row['id'];
	$product_list['catogory_id']= $row['catogory_id'];
	$product_list['name']= $row['name'];
	$product_list['link_img']= $row['link_img'];
	$product_list['price']= $row['price'];
	$product_list['off_percentage']= $row['off_percentage'];
	$product_list['brand']= $row['brand'];
	$product_list['stock']= $row['stock'];
	
	$product[]=$product_list;
	
	
}
echo json_encode($product);


?>
<?php
$servername = "localhost";
$username = "root";
$password = "";

try {
  $conn = new PDO("mysql:host=$servername;dbname=shopping", $username, $password);
  // set the PDO error mode to exception
  
  // because we use persian
  $conn-> exec("SET character_set_connection 'utf8' "); 
  $conn-> exec("SET NAMES 'utf8' ");
  
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  //echo "Connected successfully";
} catch(PDOException $e) {
  //echo "Connection failed: " . $e->getMessage();
}
?>
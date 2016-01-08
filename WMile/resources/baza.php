<?php

$servername = "sql205.byethost3.com";
$username = "b3_17082020";
$password = "glupipass";

if(isset($_POST['user']))  {
    $u=$_POST['user'];
    echo("imamo u!");
  }
else {
    echo("nemamo u!");
$u="\"sukurac\"";
}

if(isset($_POST['pass']))  {
    $p= $_POST['pass'];
    echo("imamo p!");
  }
else {
    echo("nemamo p!");
$p="\"sukurac\"";
}
if(isset($_POST['bodovi']))  {
$b=$_POST['bodovi'];
    echo("imamo b! ");
  }
else {
    echo("nemamo b!");
$b=1;
}
if(isset($_POST['level']))  {
    $l=$_POST['level'];
    echo("imamo lu!");
  }
else {
    echo("nemamo l!");
$l=0;
}



try {
    $conn = new PDO("mysql:host=$servername;dbname=b3_17082020_rezultati", $username, $password);
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    echo "Connected successfully";

$stmt = $conn->prepare("INSERT INTO rezultat (user ,pass ,score, level)    VALUES (:user, :pass, :score, :level)");
    $stmt->bindParam(':user', $u);
    $stmt->bindParam(':pass', $p);
    $stmt->bindParam(':score', $b);
    $stmt->bindParam(':level', $l);
    $stmt->execute();

    echo "New records created successfully";
    }
catch(PDOException $e)
    {
    echo "Error: " . $e->getMessage();
    }
$conn = null;
?> 

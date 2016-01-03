<?php
echo"<html><body><p>dfkjhgskjdf</p></body></html>";
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
$b=8;
}




try {
    $conn = new PDO("mysql:host=$servername;dbname=b3_17082020_rezultati", $username, $password);
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    echo "Connected successfully";
 $sql = "INSERT INTO rezultat (user ,pass ,score)VALUES ($u,$p,$b )";

    $conn->exec($sql);
    }

catch(PDOException $e)
    {
    echo "Connection failed: " . $e->getMessage();
    }
$conn = null;
?> 


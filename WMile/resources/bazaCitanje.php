            <?php

                $servername = "sql205.byethost3.com";
                $username = "b3_17082020";
                $password = "glupipass";
$output="";
                try {
                    $conn = new PDO("mysql:host=$servername;dbname=b3_17082020_rezultati", $username, $password);
                    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
                    $stmt = $conn->prepare("SELECT user,  score FROM rezultat ORDER BY score DESC");
                    $stmt->execute();
                    $result = $stmt->fetchAll(PDO::FETCH_BOTH); 
                    $c=0; $output;
                    foreach($result as $r)
                        if($c<50) {
                            $output.="<tr><td>$r[0]</td><td class=zadnjiStupac>$r[1]</td></tr>";
                            $c++;
                        }
                        else break;
                    echo $output;
                    }

                catch(PDOException $e)
                    {
                    echo "Error: " . $e->getMessage();
                    }
                $conn = null;
            ?> 
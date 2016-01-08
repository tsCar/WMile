<HTML>
  <HEAD>
    <TITLE>Whack-a-Mile</TITLE>
    <link rel="icon" type="image/ico" href="mile.jpg" />
    <link rel="stylesheet" href="../resources/milestil.css" type="text/css" />
    <link href='https://fonts.googleapis.com/css?family=Nothing+You+Could+Do' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Press+Start+2P' rel='stylesheet' type='text/css'>
  </HEAD>

<BODY>
       <script>
           function update(){
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                        document.getElementById("prom").innerHTML = xmlhttp.responseText;
                    }
                };
                xmlhttp.open("GET", "bazaCitanje.php" , true);
                xmlhttp.send();
         }
      </script>
  <div id=container > 
    <p id=txt>Whack-a-Mile</p><br />
    <div align=center>    <applet  codebase="classes/" code="Mile.class" width="640" height="480"  >      not supported    </applet> </div>

        <br /><br />
        <table onmouseover="update()">
        <tr ><th colspan="2">HIGH SCORES</th></tr>
        <tr ><th colspan="2" id=majimaji>mouseover to update</th></tr>
           
            
        </table>
<table  id=prom></table>
<br /><br /><br /><br /><br />


  </div>
</BODY>

</HTML>

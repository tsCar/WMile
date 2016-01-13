<HTML>
  <HEAD>
    <TITLE>Whack-a-Mile</TITLE>
    <link rel="icon" type="image/ico" href="./resources/mile.jpg" />
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
<div align=center>   
<script language="Javascript">
  var _app = navigator.appName;
  if (_app == 'Mozilla') {
    document.write('<embed code="/classes/Mile.class"',
                   'width="640"',
                   'height="480"',
                   'type="application/x-java-applet;version=1.5.0">');
    }
  else if (_app == 'Microsoft Internet Explorer') {
    document.write('<OBJECT ',
                   'classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93"',
                   'width="640"',
                   'height="480">',
                   '<PARAM name="code" value="/classes/Mile.class">',
                   '</OBJECT>');
    }
  else {
    document.write(' <applet  codebase="classes/" code="Mile.class" width="640" height="480" border= 2>      not supported    </applet> ');
    }
</script>
</div>

        <br /><br />
        <table onmouseover="update()">
        <tr ><th colspan="3">HIGH SCORES</th></tr>
        <tr ><th colspan="3" id=majimaji>mouseover to update</th></tr>

           
            
        </table>
<table  id=prom></table>
<br /><br /><br /><br /><br />


  </div>
</BODY>

</HTML>

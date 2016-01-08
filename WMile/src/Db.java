import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


//Preuzeto sa http://stackoverflow.com/questions/17510884/passing-value-from-applet-to-php
public class Db{
	
	
 public static String upis(String link, String user, String pass, int bodovi) throws Exception{

    URL                 url=null;
    HttpURLConnection   urlConn=null;
    DataOutputStream    printout=null;
    url = new URL (link+"../baza.php");
     //java.net.URL url = new URL(null, "http://whackamile.byethost3.com/baza.php",new sun.net.www.protocol.https.Handler());
    urlConn = (HttpURLConnection) url.openConnection();  
    urlConn.setDoOutput (true);
    urlConn.setDoInput(true);
    urlConn.setUseCaches (false);
    urlConn.setRequestMethod("POST");
    urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    
    printout = new DataOutputStream (urlConn.getOutputStream ());
    printout.writeBytes ("user="+user+"&pass="+pass+"&bodovi="+bodovi);
  
    printout.flush ();
    printout.close ();
    
    
    BufferedReader in = new BufferedReader(new InputStreamReader(
            urlConn.getInputStream()));
    String inputLine="!nista",m = "m";
    while ((inputLine = in.readLine()) != null) 
        m=m+inputLine;
    in.close();
    return m;

    
 }

}


import java.applet.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister.Pack;

public class Mile extends Applet implements ActionListener {
    private static final long serialVersionUID = 1;
   
    Image backGround;
    Krtica b;
    TextField text;
    Boolean kliknuto;
    ImageIcon mile;
    
    @Override
    public void init() {
        setLayout(null);
        backGround = getImage(getCodeBase(), "slika.jpg");
        mile=new ImageIcon(getImage(getCodeBase(), "mile.jpg").getScaledInstance( 100, 100,  java.awt.Image.SCALE_SMOOTH ) );
        b = new Krtica(mile);
        add(b);
        b.addActionListener(this);
        kliknuto = false;
        text = new TextField("tu \u0107e do\u0107 broj\u010di\u0107i");
        add(text);
      
    }

    @Override
    public void start() {
    }
    @Override
    public void paint(Graphics g) {
    	
   
    	text.setLocation(200, 50);
    	b.setLocation(100, 100);
    	text.setSize(80, 20);
    	b.setSize(111, 111);  
    	g.drawImage(backGround, 0, 0, this);
    }

    @Override
     public void paintComponents(Graphics g) {   	
        
    }

    @Override
    public void stop() {
    	Krtica.brojPogodaka=0;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Krtica.brojPogodaka ++;
        kliknuto = !kliknuto;
        backGround = kliknuto ? getImage(getCodeBase(), "slika2.jpg") :getImage(getCodeBase(), "slika.jpg");
        text.setText(Krtica.brojPogodaka.toString());
       repaint();
    }
}
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;



public class Mile extends JApplet implements ActionListener {
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
        text.setLocation(200, 50);
    	b.setLocation(100, 100);
    	text.setSize(80, 20);
    	b.setSize(111, 111);  
    
      
    }


    @Override
    public void start() {
    }
    @Override
	public void paint(Graphics g) {
    	super.paint(g);
    	g.drawImage(backGround, 0, 0, this);
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
        
        text.setLocation(200, 50);
    	b.setLocation(ThreadLocalRandom.current().nextInt(0, getSize().width-b.getSize().width),ThreadLocalRandom.current().nextInt(0, getSize().height-b.getSize().height));//Znači, x na random od nula do (širina appleta-širina mileta), analogno za y
    
    	
       repaint();
    }
}
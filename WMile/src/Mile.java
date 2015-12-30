import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;



public class Mile extends JApplet implements ActionListener, MouseListener {
    private static final long serialVersionUID = 1;
   
    Image backGround;
    Krtica b;
    JTextField text;
    Boolean kliknuto;
    JLabel label;
    Timer t;
    
    @Override
    public void init() {
    	 
        setLayout(null);
        backGround = getImage(getCodeBase(), "slika.jpg");
        label = new JLabel(new ImageIcon(backGround));
        setContentPane(label);
        
    	
        b = new Krtica(new ImageIcon(getImage(getCodeBase(), "mile.jpg").getScaledInstance( 100, 100,  java.awt.Image.SCALE_SMOOTH )) );
        add(b);
        b.addActionListener(this);
        b.addMouseListener(this);
        b.setLocation(100, 100);
        b.setSize(111, 111); 
        kliknuto = false;
        text = new JTextField("tu \u0107e do\u0107 broj\u010di\u0107i");
        add(text);
        text.setHorizontalAlignment(JTextField.CENTER);
        text.setLocation(getSize().width*3/8, 5);
    	text.setSize(getSize().width/4, 20);
    	t=new Timer(ThreadLocalRandom.current().nextInt(1500,2000),this );
    	t.start();
	
      
    }


    @Override
    public void start() {
    }
 


    @Override
    public void stop() {
    	Krtica.brojPogodaka=0;
    	Krtica.brojPojavljivanja=0;
    }

    @Override
    public void destroy() {
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		   
		text.setText(Krtica.brojPogodaka.toString()+" / "+Krtica.brojPojavljivanja);
		Krtica.brojPojavljivanja ++;
		b.setLocation(ThreadLocalRandom.current().nextInt(0, getSize().width-b.getSize().width),ThreadLocalRandom.current().nextInt(0, getSize().height-b.getSize().height));//Znači, x na random od nula do (širina appleta-širina mileta), analogno za y
		t.restart();
		
	}
  


	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		Krtica.brojPogodaka ++;
        text.setText(Krtica.brojPogodaka.toString()+" / "+Krtica.brojPojavljivanja);
        //b.setLocation(ThreadLocalRandom.current().nextInt(0, getSize().width-b.getSize().width),ThreadLocalRandom.current().nextInt(0, getSize().height-b.getSize().height));//Znači, x na random od nula do (širina appleta-širina mileta), analogno za y
        //t.restart(); 
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



    
}
    

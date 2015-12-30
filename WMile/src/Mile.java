import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;




public class Mile extends JApplet implements ActionListener, MouseListener {
    private static final long serialVersionUID = 1;
   
    Image backGround;
    Krtica b;
    JTextField kolikoOdKoliko,bodovi;
    Boolean kliknuto;
    JLabel label;
    Timer t;
    int level=0;
    JPanel lo;
    int delay;
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
        kolikoOdKoliko = new JTextField("tu \u0107e do\u0107 broj\u010di\u0107i");
        add(kolikoOdKoliko);
        kolikoOdKoliko.setHorizontalAlignment(JTextField.CENTER);
        kolikoOdKoliko.setLocation(getSize().width*3/8, 5);
    	kolikoOdKoliko.setSize(getSize().width/4, 20);
    	bodovi = new JTextField("Ukupni bodovi");
    	add(bodovi);
    	bodovi.setHorizontalAlignment(JTextField.CENTER);
    	bodovi.setLocation(getSize().width*5/12, 25);
    	bodovi.setSize(getSize().width/6, 20);
    	
    	
    	lo=new JPanel();
    		lo.setLayout(new FlowLayout());
    		lo.addMouseListener(this);
    	
    	//delay=(level==0)?ThreadLocalRandom.current().nextInt(1200,2000): (int)(ThreadLocalRandom.current().nextInt(1200,2000)/(0.2*level)) ;
    	delay=1000;
    	t=new Timer(delay,this );
    	t.start();
	
      
    }


    @Override
    public void start() {
    }
 


    @Override
    public void stop() {
    	Krtica.brojPogodaka=0;
    	Krtica.brojPojavljivanja=0;
    	level=0;
    }

    @Override
    public void destroy() {
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		
			kolikoOdKoliko.setText(Krtica.brojPogodaka.toString()+" / "+Krtica.brojPojavljivanja+"    (level "+level+")  "+delay);
			Krtica.brojPojavljivanja ++;
			b.setLocation(ThreadLocalRandom.current().nextInt(0, getSize().width-b.getSize().width),ThreadLocalRandom.current().nextInt(0, getSize().height-b.getSize().height));//Znači, x na random od nula do (širina appleta-širina mileta), analogno za y
			if(Krtica.brojPojavljivanja==4+10*level){
				
				t.stop();
				b.setEnabled(false);
				b.setVisible(false);
				level++;
				delay=(int) ((delay>400) ? delay*0.95:400);
				t.setInitialDelay(delay);
				add(lo);lo.setSize(222, 222);lo.setLocation(222, 222);
			}
			else t.restart();
		
	}
  


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource()==lo){
			remove(lo);repaint();
			b.setEnabled(true);
			b.setVisible(true);
			t.restart();
		}
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource()==b){  
			Krtica.brojPogodaka ++;
			Krtica.bodovi+=level;
			kolikoOdKoliko.setText(Krtica.brojPogodaka.toString()+" / "+Krtica.brojPojavljivanja+"    (level "+level+")");
			bodovi.setText(Krtica.bodovi.toString());
		}
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
    

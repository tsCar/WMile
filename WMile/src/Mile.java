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
    int delay,sirinaSjene=30; //oprezno s ovim, množi se s konstantom u klasi Sjena a ne smije preć 254
    Sjena popupIzmeduLevela,popupVic;
    ImageIcon marina;
    JTextArea textZaPopupIzmeduLevela;
    
    
    @Override
    public void init() {
    	Krtica.bodovi=new Integer(0);
    	Krtica.brojPogodaka=new Integer(0);
    	Krtica.level=new Integer(0);
    	Krtica.brojPojavljivanja=new Integer(1);
    	Krtica.pogodakaOvajLevel=new Integer(0);//namjerno nije inicijalizirano u Krtici jer razmišljam o tome da usred igre možda dodam još jednu a ne želim da se vrijednosti izbrišu.   
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
        kolikoOdKoliko = new JTextField(Krtica.brojPogodaka.toString()+" / "+Krtica.brojPojavljivanja+"    (level "+Krtica.level+")  "+delay);
        add(kolikoOdKoliko);
        kolikoOdKoliko.setHorizontalAlignment(JTextField.CENTER);
        kolikoOdKoliko.setLocation(getSize().width*3/8, 5);
    	kolikoOdKoliko.setSize(getSize().width/4, 20);
    	bodovi = new JTextField("Ukupni bodovi");
    	add(bodovi);
    	bodovi.setHorizontalAlignment(JTextField.CENTER);
    	bodovi.setLocation(getSize().width*5/12, 25);
    	bodovi.setSize(getSize().width/6, 20);
    	
    	popupIzmeduLevela=new Sjena(sirinaSjene);
    		popupIzmeduLevela.setLayout(new BorderLayout());
    		popupIzmeduLevela.addMouseListener(this);
    		marina = new ImageIcon(getImage(getCodeBase(), "marinai.jpg")); 
    		textZaPopupIzmeduLevela =new JTextArea("Dobrodošli! Ovo je kratki tutorial. Znači, kad se Mile pojavi,\nti ga klikni. Ne bi čovjek rekao,\njel'da? \nKasnije postane teže...");
       		textZaPopupIzmeduLevela.setEditable(false);
       		textZaPopupIzmeduLevela.addMouseListener(this);
    		popupIzmeduLevela.add(textZaPopupIzmeduLevela,BorderLayout.WEST);
    		textZaPopupIzmeduLevela.setLineWrap(true);
    		textZaPopupIzmeduLevela.setSize(2*marina.getIconWidth(), marina.getIconHeight());
    		JLabel thumb = new JLabel();
    		thumb.setIcon(marina);
    		popupIzmeduLevela.add(thumb,BorderLayout.EAST);
    		b.setEnabled(false);
			b.setVisible(false);
		add(popupIzmeduLevela);
		popupIzmeduLevela.setSize(3*marina.getIconWidth()+2*sirinaSjene, marina.getIconHeight()+2*sirinaSjene);
		popupIzmeduLevela.setLocation(getSize().width/2-(3*marina.getIconWidth()+2*sirinaSjene)/2, 222);
    	//delay=(level==0)?ThreadLocalRandom.current().nextInt(1200,2000): (int)(ThreadLocalRandom.current().nextInt(1200,2000)/(0.2*level)) ;
    	delay=1000;
    	t=new Timer(delay,this );
    	//t.start();
	
      
    }


    @Override
    public void start() {
    }
 


    @Override
    public void stop() {
    	Krtica.brojPogodaka=0;
    	Krtica.brojPojavljivanja=0;
    	Krtica.level=0;
    	popupIzmeduLevela.setEnabled(true);
		popupIzmeduLevela.setVisible(true);
		textZaPopupIzmeduLevela.setText("Joj, joj, joj, "+Krtica.pogodakaOvajLevel.toString()+"/10");
		
    }

    @Override
    public void destroy() {
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Krtica.brojPojavljivanja ++;
		kolikoOdKoliko.setText(Krtica.brojPogodaka.toString()+" / "+Krtica.brojPojavljivanja+"    (level "+Krtica.level+")  "+delay);
		b.setLocation(ThreadLocalRandom.current().nextInt(0, getSize().width-b.getSize().width),ThreadLocalRandom.current().nextInt(0, getSize().height-b.getSize().height));//Znači, x na random od nula do (širina appleta-širina mileta), analogno za y
		if(Krtica.brojPojavljivanja==4+10*Krtica.level){
			kolikoOdKoliko.setText("---");
			Krtica.level++;
			kliknuto = !kliknuto;
			backGround = kliknuto ? getImage(getCodeBase(), "slika2.jpg") :getImage(getCodeBase(), "slika.jpg");
			label.setIcon(new ImageIcon(backGround));
			setContentPane(label);  //mijenja pozadinu appleta svaki level
			t.stop();//zaustavi timer, da se ne pojavljuje Mile dok je aktivan popup
			b.setEnabled(false);
			b.setVisible(false);//sakrije i disable-a zadnjeg Mileta koji je bio nacrtan
			delay=(int) ((delay>400) ? delay*0.95:400);
			t.setInitialDelay(delay);
			popupIzmeduLevela.setEnabled(true);
			popupIzmeduLevela.setVisible(true);
			if(Krtica.level==1) {
				switch (Krtica.brojPogodaka){
				case 0:	textZaPopupIzmeduLevela.setText("Pa niti jednog? Realno, nije bilo teško, trebali ste moć...");break;
				case 1: textZaPopupIzmeduLevela.setText("Ma da, kolega, pogodili ste jednoga ali to nije dovoljno. Nula bodova");break;
				case 2: textZaPopupIzmeduLevela.setText("Ma, kolega, pogodili ste 2/3 ali nije to bilo dobro.\nOvog prvog niste potpuno, ovog drugog niste objasnili kako ste pogodili... \nNišta, 0 bodova");break;
				case 3: textZaPopupIzmeduLevela.setText("Ma kolega, jeste Vi pogodili sva tri ali prvoga niste dovoljno precizno, drugog niste baš kako treba a ovog trećeg ste dobro ali ne onako kako sam ja zamislila. To će Vam bit 0 bodova.");break;
				}
			}
			else{
				if (Krtica.pogodakaOvajLevel<5)stop();
				else{
					textZaPopupIzmeduLevela.setText(Krtica.bodovi.toString());
					}
				}
			}
			else t.restart();
	}
  


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource()==popupIzmeduLevela || e.getSource()==textZaPopupIzmeduLevela){
			popupIzmeduLevela.setEnabled(false);
			popupIzmeduLevela.setVisible(false);
			b.setEnabled(true);
			b.setVisible(true);
			t.restart();
		}
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource()==b){  
			Krtica.brojPogodaka ++;
			Krtica.bodovi+=Krtica.level;
			Krtica.pogodakaOvajLevel ++;
			kolikoOdKoliko.setText(Krtica.brojPogodaka.toString()+" / "+Krtica.brojPojavljivanja+"    (level "+Krtica.level+")");
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
    

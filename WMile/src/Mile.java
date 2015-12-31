import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.concurrent.ThreadLocalRandom;


//TODO 
//Možda promijenit pointer
//Baza+score
//Džontra i vicevi --  još samo dodat viceve
//možda dodat još krtica na višim levelima
//promijenit slike pozadine

public class Mile extends JApplet implements ActionListener, MouseListener {
    private static final long serialVersionUID = 1;
   
    Image backGround;
    Krtica mile;
    JTextField kolikoOdKoliko,bodovi;
    Boolean kliknuto;
    JLabel label;
    Timer t;
    int delay,sirinaSjene=30; //oprezno s ovim, množi se s konstantom u klasi Sjena a ne smije preć 254
    Sjena popupIzmeduLevela,popupVic,popupFail;
    ImageIcon marina,dzontra,ipsix;
    JTextArea textZaPopupIzmeduLevela,textZaPopupVic,textZaPopupFail;
    String[] vicevi ={"Prvi vic","drugi vic","trećivic","sdfa","dfasdfJHFGHFGJHGHGHasd","fawd"};
    
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
            	
        mile = new Krtica(new ImageIcon(getImage(getCodeBase(), "mile.jpg").getScaledInstance( 100, 100,  java.awt.Image.SCALE_SMOOTH )) );
        	add(mile);
        	mile.addActionListener(this);
        	mile.addMouseListener(this);
        	mile.setSize(100, 100);
        	mile.setLocation(ThreadLocalRandom.current().nextInt(0, getSize().width-mile.getSize().width),ThreadLocalRandom.current().nextInt(0, getSize().height-mile.getSize().height));
        	mile.setBorderPainted(false);
        	mile.setEnabled(false);
			mile.setVisible(false);
        	 
        kliknuto = false;
        kolikoOdKoliko = new JTextField(Krtica.brojPogodaka.toString()+" / "+Krtica.brojPojavljivanja+"    (level "+Krtica.level+" - "+delay+"s)");
        	add(kolikoOdKoliko);
        	kolikoOdKoliko.setHorizontalAlignment(JTextField.CENTER);
        	kolikoOdKoliko.setLocation(getSize().width*3/8, 5);
        	kolikoOdKoliko.setSize(getSize().width/4, 20);
        	kolikoOdKoliko.setEditable(false);
        	kolikoOdKoliko.setBorder(null);
    	bodovi = new JTextField("Ukupni bodovi");
    		add(bodovi);
    		bodovi.setHorizontalAlignment(JTextField.CENTER);
    		bodovi.setLocation(getSize().width*5/12, 26);
    		bodovi.setSize(getSize().width/6, 20);
    		bodovi.setEditable(false);
    		bodovi.setBorder(null);
    	popupIzmeduLevela=new Sjena(sirinaSjene,254,-6,0,0,0,6);
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
    		add(popupIzmeduLevela);
			popupIzmeduLevela.setSize(3*marina.getIconWidth()+2*sirinaSjene, marina.getIconHeight()+2*sirinaSjene);
			popupIzmeduLevela.setLocation(getSize().width/2-(3*marina.getIconWidth()+2*sirinaSjene)/2, 222);
    	//delay=(level==0)?ThreadLocalRandom.current().nextInt(1200,2000): (int)(ThreadLocalRandom.current().nextInt(1200,2000)/(0.2*level)) ;
    	
		popupVic=new Sjena(sirinaSjene,0,0,254,-6,0,6);
			popupVic.setLayout(new BorderLayout());
			popupVic.addMouseListener(this);
			dzontra = new ImageIcon(getImage(getCodeBase(), "dzontra.jpg")); 
			textZaPopupVic =new JTextArea("vic");
			textZaPopupVic.setEditable(false);
			textZaPopupVic.addMouseListener(this);
			popupVic.add(textZaPopupVic,BorderLayout.WEST);
			textZaPopupVic.setLineWrap(true);
			textZaPopupVic.setSize(2*dzontra.getIconWidth(), dzontra.getIconHeight());
			JLabel thumb2 = new JLabel();
			thumb2.setIcon(dzontra);
			popupVic.add(thumb2,BorderLayout.EAST);
			popupVic.setVisible(false);
			popupVic.setEnabled(false);
			add(popupVic);
			popupVic.setSize(3*dzontra.getIconWidth()+2*sirinaSjene, dzontra.getIconHeight()+2*sirinaSjene);
			popupVic.setLocation(getSize().width/2-(3*dzontra.getIconWidth()+2*sirinaSjene)/2, 222);
		popupFail=new Sjena(sirinaSjene,254,-6,0,6,0,0);
	    	popupFail.setLayout(new BorderLayout());
	    	popupFail.addMouseListener(this);
			ipsix = new ImageIcon(getImage(getCodeBase(), "ipsix.jpg")); 
			textZaPopupFail =new JTextArea("Joj, joj, joj, "+Krtica.pogodakaOvajLevel.toString()+"/10\nTrebalo je to bolje...\nZnate, kolega, ovo Vam ne može iti za prolaz.");
			textZaPopupFail.setEditable(false);
			textZaPopupFail.addMouseListener(this);
			popupFail.add(textZaPopupFail,BorderLayout.WEST);
			textZaPopupFail.setLineWrap(true);
			textZaPopupFail.setSize(2*ipsix.getIconWidth(), ipsix.getIconHeight());
			textZaPopupFail.setFont(new Font("Arial",Font.ITALIC,6));
			JLabel thumb22 = new JLabel();
			thumb22.setIcon(ipsix);
			popupFail.add(thumb22,BorderLayout.EAST);
			popupFail.setEnabled(false);
			popupFail.setVisible(false);
			add(popupFail);
			popupFail.setSize(3*ipsix.getIconWidth()+2*sirinaSjene, ipsix.getIconHeight()+2*sirinaSjene);
			popupFail.setLocation(getSize().width/2-(3*ipsix.getIconWidth()+2*sirinaSjene)/2, 222);
	    	
		
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
    	popupIzmeduLevela.setVisible(false);
		popupIzmeduLevela.setEnabled(false);
		popupFail.setVisible(true);
		popupFail.setEnabled(true);

   
    }

    @Override
    public void destroy() {
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Krtica.brojPojavljivanja ++;
		kolikoOdKoliko.setText(Krtica.brojPogodaka.toString()+" / "+Krtica.brojPojavljivanja+"    (level "+Krtica.level+" - "+delay+"s)");
		mile.setLocation(ThreadLocalRandom.current().nextInt(0, getSize().width-mile.getSize().width),ThreadLocalRandom.current().nextInt(0, getSize().height-mile.getSize().height));//Znači, x na random od nula do (širina appleta-širina mileta), analogno za y
		if(Krtica.brojPojavljivanja==4+10*Krtica.level){//kad je gotov level
			kolikoOdKoliko.setText("---");
			Krtica.level++;
			kliknuto = !kliknuto;
			backGround = kliknuto ? getImage(getCodeBase(), "slika2.jpg") :getImage(getCodeBase(), "slika.jpg");
			label.setIcon(new ImageIcon(backGround));
			setContentPane(label);  //mijenja pozadinu appleta svaki level
			t.stop();//zaustavi timer, da se ne pojavljuje Mile dok je aktivan popup
			mile.setEnabled(false);
			mile.setVisible(false);//sakrije i disable-a zadnjeg Mileta koji je bio nacrtan
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
					textZaPopupIzmeduLevela.setText("Dakle, \n"+Krtica.pogodakaOvajLevel.toString()+" pogodaka x "+Krtica.level+ "+ stari bodovi\n="+Krtica.bodovi);
					}
				}
			textZaPopupVic.setText("Je vrijeme za vic? \nJesmo pričali onaj kad "+vicevi[ThreadLocalRandom.current().nextInt(0,vicevi.length)]);
			Krtica.pogodakaOvajLevel=0;
			}
		else t.restart();
	}
  


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource()==popupIzmeduLevela || e.getSource()==textZaPopupIzmeduLevela){
			popupIzmeduLevela.setEnabled(false);
			popupIzmeduLevela.setVisible(false);
			if(Krtica.level>1){
				popupVic.setVisible(true);
				popupVic.setEnabled(true);
				repaint();
			}
			else {
				mile.setEnabled(true);
				mile.setVisible(true);
				t.restart();
			}
		}
		else if (e.getSource()==popupVic || e.getSource()==textZaPopupVic){
			popupVic.setVisible(false);
			popupVic.setEnabled(false);
			mile.setEnabled(true);
			mile.setVisible(true);
			t.restart();
		}
		else if(e.getSource()==popupFail || e.getSource()==textZaPopupFail){
			popupFail.setEnabled(false);
			popupFail.setVisible(false);
			textZaPopupIzmeduLevela.setText("Znači, idemo ponovo:");
			popupIzmeduLevela.setVisible(true);
			popupIzmeduLevela.setEnabled(true);
			repaint();
		}
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource()==mile){  
			Krtica.brojPogodaka ++;
			Krtica.bodovi+=Krtica.level;
			Krtica.pogodakaOvajLevel ++;
			kolikoOdKoliko.setText(Krtica.brojPogodaka.toString()+" / "+Krtica.brojPojavljivanja+"    (level "+Krtica.level+" - "+delay+"s)");
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
    

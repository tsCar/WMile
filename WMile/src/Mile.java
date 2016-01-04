import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;


//TODO 
//Promijenit pointer
//validirat password, doradit formu (izgled)
//možda dodat još krtica na višim levelima
//promijenit slike pozadine
//dodat gamble na score na kraju
//napravit da stranica na nešto liči...
//riješit focus listenere, da ne budu anonimni (da se vrte na serveru)
public class Mile extends JApplet implements ActionListener, MouseListener {
    private static final long serialVersionUID = 1;
   
    Image backGround;
    Krtica mile;
    JTextField kolikoOdKoliko,bodovi,userZaBazu,passZaBazu;
    Boolean kliknuto;
    JLabel label;
    Timer t;
    int delay,sirinaSjene=30,actionCounter; //oprezno sa sirinom sjene, množi se s konstantom u klasi Sjena a ne smije preć 254
    Sjena popupIzmeduLevela,popupVic,popupFail,bazaBodova;
    JButton okZaBazuBodova,notOkZaBazu;
    ImageIcon marina,dzontra,ipsix;
    JTextArea textZaPopupIzmeduLevela,textZaPopupVic,textZaPopupFail;
    String[] vicevi={ "Pita tata \"Sine, zašto želiš postati nogometni sudac?\n-Zato jer mi se fućka za nogomet!","Dodje Bruce Willis u trgovinu informaticke opreme i kaze:daj hard",
    		"Sastanu se Kinez i Slovenac na poslovnom ručku i pita Kinez 'Koliko vas ima?'\n'Oko 2 milijuna' odvrati Slovenac.'U kojem hotelu ste odsjeli?'" +
    		",Tata, tata, jel istina da internet poglupljuje?\n- WTF? ROFLMAO!","Što radi Cigo na kompjuteru?\nKopa po recycle binu!",
    		"Idu dva psa ulicom jedan se češka drugi slovačka.","Što učiniti kad vas muče sinusi - jednostavno; derivirajte ih",
    		"I petog dana stvori Bog Microsoft.I sestog dana stvori Win95. A sedmog dana, malo stane, odmori se i formatira svemir.",
    		"Zašto su amebe zbunile matematičare?Zato što se množe dijeljenjem!","Sta kaze John Wayne kad vidi krdo bizona?? kaže gle! krdo bizona!",
    		"Idu dva broda, jedan tone, drugi kile.","plavuša zove hgspot...\nserviser: kako vam mogu pomoći?\nplavuša: ne radi mi printer...\nserviser: kakvu vam grešku javlja kompjuter?\nplavuša: on kaže da ga ne prepoznaje, a stavila sam ga ispred njega...",
    		"što se dogodi gubavcu kad vidi lijepu žensku?\nispadnu mu oči","Idu dva robota ulicom, jedan će drugome:\n- Čuo sam da ti se roditelji rastavljaju!","dolazi neka baba kod doktora\nKoji je Vas problem? pita doktor\n...suzi mi oko...\n...i on joj suzi oko",
    		"Što je to malo bijelo i ne vidi se?\nčaša mlijeka iza kuće...","Pitali Muja sta slusa\nSlusam sve zivo. I Michaela Jacksona.","Bacač koplja kaze klupskom kolegi: \"Danas se moram jako potruditi, u gledalistu mi je punica.\" Kolega odgovara: \"Čovječe, to je preko sto metara, tesko da ćes je pogoditi!\"",
    		"Kada je Pinokijo shvatio da je napravljen od drveta? - Kada mu se je zapalila desna ruka.","Sto bi se dogodilo kad bi se nasa Vlada nasla u pustinji? Prvo bi se čudili, zatim bi sazvali sastanak i onda bi pijesak poskupio.",
    		"Sto je zajedničko onome koji laze i onome koji krade? Obojicu čeka saborska mirovina.","Pesimist vidi samo tamu u tunelu. Optimist vidi svjetlo na kraju tunela. Realist vidi svjetla vlaka. Strojovodja vidi tri idiota na pruzi.",
    		"Pas je zaista čovjekov najbolji prijatelj. Ako ne vjerujete pokusajte sljedeći eksperiment. Stavite psa i zenu u gepek automobila i ostavite ih zaključane dva sata. Kada otvorite gepek - tko će vam se iskreno obradovati?",
    		"Mozak je fantastičan organ: počne raditi čim se ujutro probudis i ne prestaje sve dok ne dodjes na faks","Koja je razlika izmedju prijatelja kuće i kućnog prijatelja?\nPrijatelj kuće dodje kada hoće, a kućni prijatelj hoće kada dodje.",
    		"Kada zena od muza moze stvoriti milijunasa? Samo onda kada je muz milijarder.","Kako počinje i kako zavrsava zivot jednog motoriste? Pukne guma!"
    		};
    String s;
    GridBagConstraints c = new GridBagConstraints();
    int krticaPoLevelu=3;
    @Override
    public void init() {
    
    	Krtica.bodovi=new Integer(0);
    	Krtica.brojPogodaka=new Integer(0);
    	Krtica.level=new Integer(0);
    	Krtica.brojPojavljivanja=new Integer(1);
    	actionCounter=1;
    	Krtica.pogodakaOvajLevel=new Integer(0);//namjerno nije inicijalizirano u Krtici jer razmišljam o tome da usred igre možda dodam još jednu a ne želim da se vrijednosti izbrišu.   
        setLayout(null);        	
        backGround = getImage(getCodeBase(), "../resources/slika.jpg");
        label = new JLabel(new ImageIcon(backGround));
        setContentPane(label);        
        mile = new Krtica(new ImageIcon(getImage(getCodeBase(), "../resources/mile.jpg").getScaledInstance( 100, 100,  java.awt.Image.SCALE_SMOOTH )) );
        	add(mile);
        	mile.addActionListener(this);
        	mile.addMouseListener(this);
        	mile.setSize(100, 100);
        	mile.setLocation(ThreadLocalRandom.current().nextInt(0, getSize().width-mile.getSize().width),ThreadLocalRandom.current().nextInt(0, getSize().height-mile.getSize().height));
        	mile.setBorderPainted(false);
			mile.setVisible(false); 
        kliknuto = false;
        kolikoOdKoliko = new JTextField(actionCounter+" / "+krticaPoLevelu+"  ("+Krtica.pogodakaOvajLevel  +")   (level "+Krtica.level+" - "+delay+"s)");
        	add(kolikoOdKoliko);
        	kolikoOdKoliko.setHorizontalAlignment(JTextField.CENTER);
        	kolikoOdKoliko.setSize(getSize().width/3, 20);
        	kolikoOdKoliko.setLocation(getSize().width/2-kolikoOdKoliko.getSize().width/2, 5);        	
        	kolikoOdKoliko.setEditable(false);
        	kolikoOdKoliko.setBorder(null);
    	bodovi = new JTextField("Ukupni bodovi");
    		add(bodovi);
    		bodovi.setHorizontalAlignment(JTextField.CENTER);
    		bodovi.setSize(getSize().width/6, 20);
    		bodovi.setLocation(getSize().width/2-bodovi.getSize().width/2, 26);
    		bodovi.setEditable(false);
    		bodovi.setBorder(null);
    	popupIzmeduLevela=new Sjena(sirinaSjene,254,-6,0,0,0,6);
    		popupIzmeduLevela.setLayout(new BorderLayout());
    		popupIzmeduLevela.addMouseListener(this);
    		marina = new ImageIcon(getImage(getCodeBase(), "../resources/marinai.jpg")); 
    		textZaPopupIzmeduLevela =new JTextArea("Dobrodošli! Ovo je kratki tutorial. Znači, kad se Mile pojavi,\nVi ga kliknite. Ne bi čovjek rekao,\njel'da? \nKasnije postane teže...");
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
			popupIzmeduLevela.setLocation(getSize().width/2-popupIzmeduLevela.getSize().width/2, 222);
		popupVic=new Sjena(sirinaSjene,0,0,254,-6,0,6);
			popupVic.setLayout(new BorderLayout());
			popupVic.addMouseListener(this);
			dzontra = new ImageIcon(getImage(getCodeBase(), "../resources/dzontra.jpg")); 
			textZaPopupVic =new JTextArea("vic");
			textZaPopupVic.setEditable(false);
			textZaPopupVic.scrollRectToVisible(getBounds());
			textZaPopupVic.addMouseListener(this);
			popupVic.add(textZaPopupVic,BorderLayout.WEST);
			textZaPopupVic.setLineWrap(true);
			textZaPopupVic.setSize(2*dzontra.getIconWidth(), dzontra.getIconHeight());
			JLabel thumb2 = new JLabel();
			thumb2.setIcon(dzontra);
			popupVic.add(thumb2,BorderLayout.EAST);
			popupVic.setVisible(false);
			add(popupVic);
			popupVic.setSize(3*dzontra.getIconWidth()+2*sirinaSjene, dzontra.getIconHeight()+2*sirinaSjene);
			popupVic.setLocation(getSize().width/2-popupVic.getSize().width/2, 222);
		popupFail=new Sjena(sirinaSjene,0,6,254,-6,0,0);
	    	popupFail.setLayout(new BorderLayout());
	    	popupFail.addMouseListener(this);
			ipsix = new ImageIcon(getImage(getCodeBase(), "../resources/ipsix.jpg")); 
			textZaPopupFail =new JTextArea("");
			textZaPopupFail.setEditable(false);
			textZaPopupFail.addMouseListener(this);
			popupFail.add(textZaPopupFail,BorderLayout.WEST);
			textZaPopupFail.setLineWrap(true);
			textZaPopupFail.setSize(2*ipsix.getIconWidth(), ipsix.getIconHeight());
			textZaPopupFail.setFont(new Font("Arial",Font.ITALIC,6));
			JLabel thumb22 = new JLabel();
			thumb22.setIcon(ipsix);
			popupFail.add(thumb22,BorderLayout.EAST);
			popupFail.setVisible(false);
			add(popupFail);
			popupFail.setSize(3*ipsix.getIconWidth()+2*sirinaSjene, ipsix.getIconHeight()+2*sirinaSjene);
			popupFail.setLocation(getSize().width/2-popupFail.getSize().width/2, 222);
		bazaBodova=new Sjena(sirinaSjene,254,-6,254,-6,0,8);
			bazaBodova.setLayout(new GridBagLayout());
			c.gridx = 0;c.gridy=0;c.fill = GridBagConstraints.HORIZONTAL;c.gridwidth = 3;
			bazaBodova.add(new JLabel("Unesi bodove u bazu?",JLabel.CENTER),c);
			userZaBazu=new JTextField("nick");//System.getProperty("user.name"));  //Ne može, ne da Java! -.- 
			Slusac s=new Slusac(userZaBazu);
			userZaBazu.addFocusListener(s);				
			c.gridy=1;c.gridwidth = 2;c.fill = GridBagConstraints.HORIZONTAL;c.gridwidth = 2;
			bazaBodova.add(userZaBazu,c);
			passZaBazu=new JTextField("password");
			Slusac s2 =new Slusac(passZaBazu);
			passZaBazu.addFocusListener(s2);
			c.gridx = 1;c.gridy = 2;c.fill = GridBagConstraints.HORIZONTAL;c.insets = new Insets(3,0,0,0);c.gridwidth = 2;
			bazaBodova.add(passZaBazu,c);
			okZaBazuBodova=new JButton("Unesi");
			okZaBazuBodova.addMouseListener(this);
			c.gridx=0;c.gridy = 4;c.fill = GridBagConstraints.HORIZONTAL;c.insets = new Insets(10,0,0,0);c.ipadx=10;c.gridwidth = 1;
			bazaBodova.add(okZaBazuBodova,c);
			notOkZaBazu=new JButton("Nemoj unijeti");
			notOkZaBazu.addMouseListener(this);
			c.gridx=3;c.gridy = 4;c.fill = GridBagConstraints.HORIZONTAL;c.gridwidth = 1;
			bazaBodova.add(notOkZaBazu,c);	
			//bazaBodova.setVisible(false);
			add(bazaBodova);
			bazaBodova.setSize(3*ipsix.getIconWidth()+2*sirinaSjene, ipsix.getIconHeight()+2*sirinaSjene);
			bazaBodova.setLocation(getSize().width/2-(3*ipsix.getIconWidth()+2*sirinaSjene)/2, 22);
			
		delay=1000;
    	t=new Timer(delay,this );
   
    }

    @Override
    public void start() {}

    @Override
    public void stop() {
    	Krtica.brojPogodaka=0;
    	Krtica.brojPojavljivanja=1;
    	popupIzmeduLevela.setVisible(false);
		textZaPopupFail.setText("Joj, joj, joj, "+Krtica.pogodakaOvajLevel.toString()+"/"+krticaPoLevelu+"\nTrebalo je to bolje...\nZnate, kolega, ovo Vam ne može iti za prolaz.");
		Krtica.pogodakaOvajLevel=0;
		popupFail.setVisible(true);
		popupFail.setEnabled(false);//timer ga vrati nakon 0.75s, da se ne može slučajno stisnut u igri
		Tibor t=new Tibor(popupFail); 
		Timer cekajFail = new Timer(750, t); //kad stavim u konstruktor od timera new event listener onda se na kompu vrti ali na serveru ne, java security mu ne da
		cekajFail.setRepeats(false);
		cekajFail.start();
    }

    @Override
    public void destroy() {
    }
    
    
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==mile || e.getSource()==t){
			Krtica.brojPojavljivanja ++;
			actionCounter++;
			bodovi.setText(Krtica.bodovi.toString());
			kolikoOdKoliko.setText(actionCounter+" / "+krticaPoLevelu+"  ("+Krtica.pogodakaOvajLevel  +")   (level "+Krtica.level+" - "+delay+"s)");
			mile.setLocation(ThreadLocalRandom.current().nextInt(0, getSize().width-mile.getSize().width),ThreadLocalRandom.current().nextInt(0, getSize().height-mile.getSize().height));//Znači, x na random od nula do (širina appleta-širina mileta), analogno za y
			if(actionCounter==krticaPoLevelu+1){//kad je gotov level
				if(Krtica.level>5)krticaPoLevelu++;
				kolikoOdKoliko.setText("---");
				actionCounter=1;
				Krtica.level++;
				kliknuto = !kliknuto;
				backGround = kliknuto ? getImage(getCodeBase(), "../resources/slika2.jpg") :getImage(getCodeBase(), "../resources/slika.jpg");
				label.setIcon(new ImageIcon(backGround));
				setContentPane(label);  //mijenja pozadinu appleta svaki level
				t.stop();//zaustavi timer, da se ne pojavljuje Mile dok je aktivan popup
				mile.setVisible(false);//sakrije  zadnjeg Mileta koji je bio nacrtan
				delay=(int) ((delay>400) ? delay*0.95:400);
				t.setInitialDelay(delay);
				popupIzmeduLevela.setVisible(true);
				popupIzmeduLevela.setEnabled(false);
				Tibor t2=new Tibor(popupIzmeduLevela);//isto kao i za popupFail
				Timer cekajIzmedu = new Timer(750, t2);
				cekajIzmedu.setRepeats(false);
				cekajIzmedu.start();
			
				if(Krtica.level==1) {//nakon tutoriala
					switch (Krtica.brojPogodaka){
					case 0:	textZaPopupIzmeduLevela.setText("Pa niti jednog? Realno, nije bilo teško, trebali ste moć...");break;
					case 1: textZaPopupIzmeduLevela.setText("Ma da, kolega, pogodili ste\n jednoga ali to nije dovoljno. \n0 bodova");break;
					case 2: textZaPopupIzmeduLevela.setText("Ma, kolega, pogodili ste 2/3 ali nije to bilo dobro.\nOvog prvog niste potpuno, ovog drugog niste objasnili kako ste pogodili... \nNišta, 0 bodova");break;
					case 3: textZaPopupIzmeduLevela.setText("Ma kolega, jeste Vi pogodili sva \ntri ali prvoga niste dovoljno \nprecizno, drugog niste baš \nkako treba a ovog trećeg ste \ndobro ali ne onako kako sam \nja zamislila. \nTo će Vam bit \n0 bodova.");break;
					}
					Krtica.pogodakaOvajLevel=0;
					krticaPoLevelu=10;
				}
				else{
					if (Krtica.pogodakaOvajLevel<krticaPoLevelu/2)stop();
					else{
						textZaPopupIzmeduLevela.setText("Dakle, \n"+Krtica.pogodakaOvajLevel.toString()+" pogodaka x "+(Krtica.level-1)+ "+ stari bodovi\n="+Krtica.bodovi);
						Krtica.pogodakaOvajLevel=0;
						}
					}
				textZaPopupVic.setText("Je vrijeme za vic? \nJesmo pričali onaj kad...\n "+vicevi[ThreadLocalRandom.current().nextInt(0,vicevi.length)]);
				}
			else t.restart();
		}
	}
  


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource()==popupIzmeduLevela || e.getSource()==textZaPopupIzmeduLevela){
			if(popupIzmeduLevela.isEnabled()){
				popupIzmeduLevela.setVisible(false);
				if(Krtica.level>1){
					popupVic.setVisible(true);
					repaint();
				}
				else {
					mile.setVisible(true);
					kolikoOdKoliko.setText(actionCounter+" / "+krticaPoLevelu+"  ("+Krtica.pogodakaOvajLevel  +")   (level "+Krtica.level+" - "+delay+"s)");
					t.restart();
				}
			}
		}
		else if (e.getSource()==popupVic || e.getSource()==textZaPopupVic){
			popupVic.setVisible(false);
			mile.setVisible(true);
			kolikoOdKoliko.setText(actionCounter+" / "+krticaPoLevelu+"  ("+Krtica.pogodakaOvajLevel  +")   (level "+Krtica.level+" - "+delay+"s)");
			t.restart();
		}
		else if(e.getSource()==popupFail || e.getSource()==textZaPopupFail){
			if(popupFail.isEnabled()){
				popupFail.setVisible(false);
				textZaPopupIzmeduLevela.setText("Znači, idemo ponovo:");
				Krtica.level=1;
				krticaPoLevelu=10;
				bazaBodova.setVisible(true);
				//bazaBodova.add(new JLabel(Krtica.bodovi.toString()));
				repaint();
			}
		}
		else if(e.getSource()==okZaBazuBodova){
		/*	if(passZaBazu.toString()=="password"){
				passZaBazu.setBackground(Color.ORANGE);				
			}
			else{*/
				try {
					s=Db.upis(getCodeBase().toString(),userZaBazu.getText(),passZaBazu.getText(),Krtica.bodovi);
					//textZaPopupIzmeduLevela.setText(s);
					
					
				} catch (Exception ex) {
					textZaPopupIzmeduLevela.setText(ex.toString());
				}
				finally{
					bazaBodova.setVisible(false);
					popupIzmeduLevela.setVisible(true);
					repaint();
				}
			//}
			Krtica.bodovi=0;
			bodovi.setText(Krtica.bodovi.toString());
			kolikoOdKoliko.setText(actionCounter+" / "+krticaPoLevelu+"  ("+Krtica.pogodakaOvajLevel  +")   (level "+Krtica.level+" - "+delay+"s)");
		}
		else if(e.getSource()==notOkZaBazu){
			bazaBodova.setVisible(false);
			Krtica.bodovi=0;
			bodovi.setText(Krtica.bodovi.toString());
			kolikoOdKoliko.setText(actionCounter+" / "+krticaPoLevelu+"  ("+Krtica.pogodakaOvajLevel  +")   (level "+Krtica.level+" - "+delay+"s)");
			popupIzmeduLevela.setVisible(true);
			repaint();
	
		}
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource()==mile){  
			Krtica.brojPogodaka ++;
			Krtica.bodovi+=Krtica.level;
			Krtica.pogodakaOvajLevel ++;
			kolikoOdKoliko.setText(actionCounter+" / "+krticaPoLevelu+"  ("+Krtica.pogodakaOvajLevel  +")   (level "+Krtica.level+" - "+delay+"s)");
		}
	}


	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}



    
}
    

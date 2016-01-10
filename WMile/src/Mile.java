import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;

import javafx.scene.layout.Background;

import javax.swing.*;

import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;


//TODO 


//napravit da stranica na nešto liči...


//možda:
//dodat gamble na score na kraju


public class Mile extends JApplet implements ActionListener, MouseListener {
	public Mile() {
	}
    private static final long serialVersionUID = 1;
   
    ImageIcon[] backGround;
    Krtica mile,sanja;
    JTextField kolikoOdKoliko,bodovi,userZaBazu;
    JPasswordField passZaBazu;
    JLabel label;
    Timer t,tsanja,cekajIzmedu,cekajFail,cekajVic;
    int delay,sirinaSjene=30,actionCounter,krticaPoLevelu,kliknutihSanja,zaIvu; //oprezno sa sirinom sjene, množi se s konstantom u klasi Sjena a ne smije preć 254
    Sjena popupIzmeduLevela,popupVic,popupFail,popupBaza;
    JButton okZaBazuBodova,notOkZaBazu;
    JScrollPane scrollVic,scrollLevel;
    ImageIcon marina,dzontra,ipsix,mileico,mile2ico,sanjaico,sanja2ico;
    JEditorPane textZaPopupIzmeduLevela,textZaPopupVic,textZaPopupFail;
    String[] vicevi={ "Pita tata \"Sine, zašto želiš postati nogometni sudac?\n-Zato jer mi se fućka za nogomet!","Dodje Bruce Willis u trgovinu informaticke opreme i kaze:daj hard",
    		"Sastanu se Kinez i Slovenac na poslovnom ručku i pita Kinez 'Koliko vas ima?'\n'Oko 2 milijuna' odvrati Slovenac.'U kojem hotelu ste odsjeli?'" ,
    		"Tata, tata, jel istina da internet poglupljuje?\n- WTF? ROFLMAO!","Što radi Cigo na kompjuteru?\nKopa po recycle binu!",
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
    String s,pass;
    GridBagConstraints c; 
    ListenerZaSanju listenerZaSanju;
    ListenerZaSjenu t2,t3,t4;

    
    @Override
    public void init() {
    	backGround=new ImageIcon[16];
    		backGround[0]=new ImageIcon(getImage(getCodeBase(), "../resources/slika0.jpg").getScaledInstance( getSize().width,getSize().height,  java.awt.Image.SCALE_SMOOTH ));
    		backGround[1]=new ImageIcon(getImage(getCodeBase(), "../resources/slika1.jpg").getScaledInstance( getSize().width,getSize().height,  java.awt.Image.SCALE_SMOOTH ));
    		backGround[2]=new ImageIcon(getImage(getCodeBase(), "../resources/slika2.jpg").getScaledInstance( getSize().width,getSize().height,  java.awt.Image.SCALE_SMOOTH ));
    		backGround[3]=new ImageIcon(getImage(getCodeBase(), "../resources/slika3.jpg").getScaledInstance( getSize().width,getSize().height,  java.awt.Image.SCALE_SMOOTH ));
    		backGround[4]=new ImageIcon(getImage(getCodeBase(), "../resources/slika4.jpg").getScaledInstance( getSize().width,getSize().height,  java.awt.Image.SCALE_SMOOTH ));
    		backGround[5]=new ImageIcon(getImage(getCodeBase(), "../resources/slika5.jpg").getScaledInstance( getSize().width,getSize().height,  java.awt.Image.SCALE_SMOOTH ));
        	backGround[6]=new ImageIcon(getImage(getCodeBase(), "../resources/slika6.jpg").getScaledInstance( getSize().width,getSize().height,  java.awt.Image.SCALE_SMOOTH ));
    		backGround[7]=new ImageIcon(getImage(getCodeBase(), "../resources/slika7.jpg").getScaledInstance( getSize().width,getSize().height,  java.awt.Image.SCALE_SMOOTH ));
    		backGround[8]=new ImageIcon(getImage(getCodeBase(), "../resources/slika8.jpg").getScaledInstance( getSize().width,getSize().height,  java.awt.Image.SCALE_SMOOTH ));
    		backGround[9]=new ImageIcon(getImage(getCodeBase(), "../resources/slika9.jpg").getScaledInstance( getSize().width,getSize().height,  java.awt.Image.SCALE_SMOOTH ));
        	backGround[10]=new ImageIcon(getImage(getCodeBase(), "../resources/slika10.jpg").getScaledInstance( getSize().width,getSize().height,  java.awt.Image.SCALE_SMOOTH ));
        	backGround[11]=new ImageIcon(getImage(getCodeBase(), "../resources/slika11.jpg").getScaledInstance( getSize().width,getSize().height,  java.awt.Image.SCALE_SMOOTH ));
        	backGround[12]=new ImageIcon(getImage(getCodeBase(), "../resources/slika12.jpg").getScaledInstance( getSize().width,getSize().height,  java.awt.Image.SCALE_SMOOTH ));
        	backGround[13]=new ImageIcon(getImage(getCodeBase(), "../resources/slika13.jpg").getScaledInstance( getSize().width,getSize().height,  java.awt.Image.SCALE_SMOOTH ));
        	backGround[14]=new ImageIcon(getImage(getCodeBase(), "../resources/slika14.jpg").getScaledInstance( getSize().width,getSize().height,  java.awt.Image.SCALE_SMOOTH ));
        	backGround[15]=new ImageIcon(getImage(getCodeBase(), "../resources/slika15.jpg").getScaledInstance( getSize().width,getSize().height,  java.awt.Image.SCALE_SMOOTH ));
    	Krtica.bodovi=new Integer(0);
    	Krtica.brojPogodaka=new Integer(0);
    	Krtica.level=new Integer(0);
    	Krtica.brojPojavljivanja=new Integer(0);
    	actionCounter=0;//broj pojavljivanja po levelu
    	Krtica.pogodakaOvajLevel=new Integer(0);//namjerno nije inicijalizirano u Krtici jer razmišljam o tome da usred igre možda dodam još jednu a ne želim da se vrijednosti izbrišu.   
    	krticaPoLevelu=3;
    	kliknutihSanja=0;
    	pass=new String("password");
    	setLayout(null); //da mogu Mileta stavljat gdje hoću       	
    	this.addMouseListener(this);//izgleda ko da ne može bit točno. Nadam se da to dodaje ML na cijeli applet 
        label = new JLabel(new ImageIcon(getImage(getCodeBase(), "../resources/slika00.jpg").getScaledInstance( getSize().width,getSize().height,  java.awt.Image.SCALE_SMOOTH )));
        setContentPane(label); 
        mileico=new ImageIcon(getImage(getCodeBase(), "../resources/mile.jpg").getScaledInstance( 100, 100,  java.awt.Image.SCALE_SMOOTH ));
        mile2ico=new ImageIcon(getImage(getCodeBase(), "../resources/mile2.png").getScaledInstance( 100, 100,  java.awt.Image.SCALE_SMOOTH ));
        mile = new Krtica(mileico );
        	mile.setOpaque(false);
        	mile.setContentAreaFilled(false);
        	mile.setBorderPainted(false);
        	add(mile);
        	mile.addActionListener(this);
        	mile.addMouseListener(this);
        	mile.setSize(100, 100);
        	mile.setLocation(ThreadLocalRandom.current().nextInt(0, getSize().width-mile.getSize().width),ThreadLocalRandom.current().nextInt(0, getSize().height-mile.getSize().height));
        	mile.setBorderPainted(false);
			mile.setVisible(false); 
		sanjaico=new ImageIcon(getImage(getCodeBase(), "../resources/sanja.jpg").getScaledInstance( 100, 100,  java.awt.Image.SCALE_SMOOTH ));
		sanja2ico=new ImageIcon(getImage(getCodeBase(), "../resources/sanja2.png").getScaledInstance( 100, 100,  java.awt.Image.SCALE_SMOOTH ));
		sanja = new Krtica(sanjaico );
        	add(sanja);
        	sanja.setOpaque(false);
        	sanja.setContentAreaFilled(false);
        	sanja.setBorderPainted(false);
        	sanja.addMouseListener(this);
        	sanja.setSize(100, 100);
        	sanja.setLocation(ThreadLocalRandom.current().nextInt(0, getSize().width-sanja.getSize().width),ThreadLocalRandom.current().nextInt(0, getSize().height-sanja.getSize().height));
        	sanja.setBorderPainted(false);
			sanja.setVisible(false); 			
        kolikoOdKoliko = new JTextField("n/n (pogodenih)(level-vrijeme)");
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
    		textZaPopupIzmeduLevela =new JEditorPane();
    		textZaPopupIzmeduLevela.setContentType("text/html");
    		textZaPopupIzmeduLevela.setText("<div style=\" text-align: center; font-size:9;\">[ klikni za nastavak ]</div><div style=\" text-align: center; font-size: 22; \"><b>Dobrodošli!</b></div>Znači, kad se Mile pojavi, Vi ga kliknite. Ne bi čovjek rek'o, jel'da? :)<br>Sanju ne dirat', za to se gube bodovi.");
    		textZaPopupIzmeduLevela.setEditable(false);
       		textZaPopupIzmeduLevela.addMouseListener(this);
			scrollLevel=new JScrollPane(textZaPopupIzmeduLevela);//,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    		popupIzmeduLevela.add(scrollLevel,BorderLayout.WEST);
    		textZaPopupIzmeduLevela.setPreferredSize(new Dimension(2*marina.getIconWidth()-scrollLevel.getVerticalScrollBar().getWidth(), marina.getIconHeight()));
    		scrollLevel.setPreferredSize(new Dimension(2*marina.getIconWidth()-scrollLevel.getVerticalScrollBar().getWidth(), marina.getIconHeight()));
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
			textZaPopupVic =new JEditorPane();
			textZaPopupVic.setContentType("text/html");
			textZaPopupVic.setText("Vic");
			textZaPopupVic.setEditable(false);
			textZaPopupVic.addMouseListener(this);
			scrollVic=new JScrollPane(textZaPopupVic);//,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			popupVic.add(scrollVic,BorderLayout.WEST);
			scrollLevel.setPreferredSize(new Dimension(2*marina.getIconWidth()-scrollLevel.getVerticalScrollBar().getWidth(), marina.getIconHeight()));
			textZaPopupVic.setPreferredSize(new Dimension(2*dzontra.getIconWidth()-scrollLevel.getVerticalScrollBar().getWidth(), dzontra.getIconHeight()));
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
			textZaPopupFail =new JEditorPane();
			textZaPopupFail.setText("");
			textZaPopupFail.setEditable(false);
			textZaPopupFail.addMouseListener(this);
			popupFail.add(textZaPopupFail,BorderLayout.WEST);
			textZaPopupFail.setPreferredSize(new Dimension (2*ipsix.getIconWidth(), ipsix.getIconHeight()));
			textZaPopupFail.setFont(new Font("Arial",Font.ITALIC,7));
			JLabel thumb22 = new JLabel();
			thumb22.setIcon(ipsix);
			popupFail.add(thumb22,BorderLayout.EAST);
			popupFail.setVisible(false);
			add(popupFail);
			popupFail.setSize(new Dimension(3*ipsix.getIconWidth()+2*sirinaSjene, ipsix.getIconHeight()+2*sirinaSjene));
			popupFail.setLocation(getSize().width/2-popupFail.getSize().width/2, 222);
		popupBaza=new Sjena(sirinaSjene,254,-6,254,-6,0,8);
			JPanel bazaPanel=new JPanel();
			bazaPanel.setBackground(new Color(201,245,221));
			popupBaza.add(bazaPanel);
			bazaPanel.setLayout(new GridBagLayout());
			c = new GridBagConstraints();c.fill=GridBagConstraints.BOTH;
			c.gridx = 0;c.gridy=0;	c.gridwidth=2;c.insets=new Insets(10, 0, 10, 0);	
			JLabel la=new JLabel("Unijeti bodove u bazu?",JLabel.CENTER);
			bazaPanel.add(la,c);
			userZaBazu=new JTextField("nick");//*/	System.getProperty("user.name"));  //Ne može, ne da Java! -.- 
			LstenerZaFokus s=new LstenerZaFokus(userZaBazu);
			userZaBazu.addFocusListener(s);				
			c.gridx=0;c.gridy=1;c.gridwidth=2;c.insets=new Insets(0, 10, 10, 10);
			bazaPanel.add(userZaBazu,c);
			passZaBazu=new JPasswordField(pass);
			passZaBazu.setEchoChar((char)0);
			LstenerZaFokus s2 =new LstenerZaFokus(passZaBazu);
			passZaBazu.addFocusListener(s2);
			c.gridx=0;c.gridy = 2;c.gridwidth=2;
			bazaPanel.add(passZaBazu,c);
			okZaBazuBodova=new JButton("Unesi");
			okZaBazuBodova.addMouseListener(this);
			c.gridx=0;c.gridy = 3;c.gridwidth=1;
			bazaPanel.add(okZaBazuBodova,c);
			notOkZaBazu=new JButton("Nemoj unijeti");
			notOkZaBazu.addMouseListener(this);
			c.gridx=1;c.gridy = 3;c.fill=GridBagConstraints.BOTH;c.gridwidth=1;
			bazaPanel.add(notOkZaBazu,c);	
			popupBaza.setVisible(false);
			add(popupBaza);
			popupBaza.setSize(3*ipsix.getIconWidth()+2*sirinaSjene, ipsix.getIconHeight()+2*sirinaSjene);
			popupBaza.setLocation(getSize().width/2-popupBaza.getSize().width/2, 62);
			popupBaza.validate();
		t2=new ListenerZaSjenu(popupIzmeduLevela);//isto kao i za popupFail	
		cekajIzmedu = new Timer(750, t2);
	    t3=new ListenerZaSjenu(popupFail);
	    cekajFail=new Timer(750, t3);
		t4=new ListenerZaSjenu(popupVic);//isto kao i za popupFail
		cekajVic = new Timer(750, t4);
		delay=1000;
    	t=new Timer(delay,this );t.setRepeats(false);
    	listenerZaSanju=new ListenerZaSanju(sanja,getSize().width-sanja.getSize().width,getSize().height-sanja.getSize().height,sanjaico);
    	tsanja=new Timer(3500,listenerZaSanju);
    }

    @Override
    public void start() {}
    @Override
    public void stop() {}
    @Override
    public void destroy() {
    }
   
    
	@Override
	public void actionPerformed(ActionEvent e) {
		
		actionCounter++;
		t.setInitialDelay(delay);
		if(actionCounter==krticaPoLevelu+1){//kad je gotov level
			if(Krtica.level>=5)	krticaPoLevelu++;
			kolikoOdKoliko.setText("Level "+Krtica.level);
			actionCounter=0;
			mile.setVisible(false);//sakrije  zadnjeg Mileta koji je bio nacrtan
			if(tsanja.isRunning())tsanja.stop();
			if(sanja.isVisible())sanja.setVisible(false);
			if(listenerZaSanju.t.isRunning())listenerZaSanju.t.stop(); //Ovo je ružno, ovako na silu u tuđu klasu ali ne da mi se smišljat ljepše.
			
			delay=(int) ((delay>400) ? delay*0.95:400);
			t.setInitialDelay(delay);
			Krtica.level++;
			label.setIcon(backGround[Krtica.level%backGround.length]);
			setContentPane(label);  //mijenja pozadinu appleta svaki level		izgleda do ovo dodaje pozadinu i na popupove. 	
			//ne kužim kako. I zašto doda i bodove i sve. Riješio sam bug tako da sam stavio da sjena bude prozirna ali to je više maskiranje nego rješenje... :/
			if(Krtica.level==1) {//nakon tutoriala
				switch (Krtica.pogodakaOvajLevel){
				case 0:	textZaPopupIzmeduLevela.setText("Realno, nije bilo teško, trebali ste bar jednog pogodit'...<br><b>0 bodova</b>");break;
				case 1: textZaPopupIzmeduLevela.setText("Ma da, kolega, pogodili ste jednoga ali to nije dovoljno. <br><b>0 bodova</b>");break;
				case 2: textZaPopupIzmeduLevela.setText("Ma, kolega, pogodili ste 2/3 ali nije to bilo dobro.<br>Ovog prvog niste potpuno, ovog drugog niste objasnili kako ste pogodili... <br>Ništa, <b>0 bodova</b>");break;
				case 3: textZaPopupIzmeduLevela.setText("Ma kolega, jeste Vi pogodili sva tri ali prvoga niste dovoljno precizno, drugog niste baš kako treba a ovog trećeg ste dobro ali ne onako kako sam ja zamislila. <br>To će Vam bit <b>0 bodova</b> ");break;
				}
				krticaPoLevelu=10;
				popupIzmeduLevela.setVisible(true);
				popupIzmeduLevela.setEnabled(false);
				ListenerZaSjenu t2=new ListenerZaSjenu(popupIzmeduLevela);//isto kao i za popupFail
				Timer cekajIzmedu = new Timer(750, t2);
				cekajIzmedu.setRepeats(false);
				cekajIzmedu.start();
			}
			else{
				zaIvu=(krticaPoLevelu>10)? (krticaPoLevelu-1):10; //jer se nakon 10 levela počnu povećavat a poveća se prije nego se Ivo pojavi
				if (Krtica.pogodakaOvajLevel<zaIvu/2){
				 	Krtica.brojPogodaka=0;
			    	Krtica.brojPojavljivanja=0;
			    	delay=1000;
			    	t.setInitialDelay(delay);
					textZaPopupFail.setText("\n\nJoj, joj, joj, "+Krtica.pogodakaOvajLevel.toString()+" od "+zaIvu+"\nTrebalo je to bolje...\nZnate, kolega, ovo Vam ne može iti za prolaz.");
					krticaPoLevelu=10;
					popupFail.setVisible(true);
					popupFail.setEnabled(false);//timer ga vrati nakon 0.75s, da se ne može slučajno stisnut u igri
					cekajFail.setRepeats(false);
					cekajFail.start();
				}
				else{
					if(Krtica.level<=5)textZaPopupIzmeduLevela.setText("Bodovi za Level "+(Krtica.level-1)+":<br> "+Krtica.pogodakaOvajLevel+" pogodaka x "+(Krtica.level-1)+ " = <b>"+Krtica.pogodakaOvajLevel*(Krtica.level-1)+"</b><br>Ukupno pogodaka:"+Krtica.brojPogodaka+"/"+Krtica.brojPojavljivanja+"     "+(int)((double)Krtica.brojPogodaka/Krtica.brojPojavljivanja*100)+"%"+"<hr><p style= text-align: center;><b>Ukupno bodova:<br>"+Krtica.bodovi+"</b></p><br><hr>");
					else textZaPopupIzmeduLevela.setText("Bodovi za Level "+(Krtica.level-1)+":<br> "+Krtica.pogodakaOvajLevel+" pogodaka x "+(Krtica.level-1)+ " - "+kliknutihSanja+" x Sanja = <b>"+(Krtica.pogodakaOvajLevel*(Krtica.level-1)-3*kliknutihSanja*(Krtica.level-1))+"</b><br>Ukupno pogodaka:"+Krtica.brojPogodaka+"/"+Krtica.brojPojavljivanja+"     "+(int)((double)Krtica.brojPogodaka/Krtica.brojPojavljivanja*100)+"%"+"<hr><p style= text-align: center;><b>Ukupno bodova:<br>"+Krtica.bodovi+"</b></p><br><hr>");
					popupIzmeduLevela.setVisible(true);
					popupIzmeduLevela.setEnabled(false);
					cekajIzmedu.setRepeats(false);
					cekajIzmedu.start();
				}
			}
			kliknutihSanja=0;
			Krtica.pogodakaOvajLevel=0;
			textZaPopupVic.setText("<p>Je vrijeme za vic? <br>Jesmo pričali onaj kad...<br>"+vicevi[ThreadLocalRandom.current().nextInt(0,vicevi.length)]+"</p>");
		}
		
		else {
			Krtica.brojPojavljivanja ++;
			kolikoOdKoliko.setText(actionCounter+" / "+krticaPoLevelu+"  ("+Krtica.pogodakaOvajLevel  +")   (level "+Krtica.level+" - "+delay+"ms)");
			mile.setLocation(ThreadLocalRandom.current().nextInt(0, getSize().width-mile.getSize().width),ThreadLocalRandom.current().nextInt(0, getSize().height-mile.getSize().height));//Znači, x na random od nula do (širina appleta-širina mileta), analogno za y
			mile.setIcon(mileico);
			mile.setVisible(true);
			repaint();
			t.restart();
		}
		bodovi.setText(Krtica.bodovi.toString());
	}
  


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource()==popupIzmeduLevela || e.getSource()==textZaPopupIzmeduLevela ){
			if(popupIzmeduLevela.isEnabled()){
				popupIzmeduLevela.setVisible(false);repaint();
				if(Krtica.level>1){
					popupVic.setVisible(true);
					popupVic.setEnabled(false);
					cekajVic.setRepeats(false);
					cekajVic.start();					
				}
				else {
					//mile.setVisible(true);
					if(Krtica.level==0)label.setIcon(backGround[0]);
					kolikoOdKoliko.setText(actionCounter+" / "+krticaPoLevelu+"  ("+Krtica.pogodakaOvajLevel  +")   (level "+Krtica.level+" - "+delay+"ms)");
					t.setInitialDelay(250);//toliko čeka prije nego nacrta prvog
					t.restart();
				}
			}
		}
		else if (e.getSource()==popupVic || e.getSource()==textZaPopupVic){
			if(popupVic.isEnabled()){
				popupVic.setVisible(false);
				kolikoOdKoliko.setText(actionCounter+" / "+krticaPoLevelu+"  ("+Krtica.pogodakaOvajLevel  +")   (level "+Krtica.level+" - "+delay+"ms)");
				t.setInitialDelay(250);
				t.restart();
				if(Krtica.level>=5) tsanja.restart();
			}
		}
		else if(e.getSource()==popupFail || e.getSource()==textZaPopupFail){
			if(popupFail.isEnabled()){
				popupFail.setVisible(false);
				textZaPopupIzmeduLevela.setText("<p>Znači, idemo ponovo:</p>");
				krticaPoLevelu=10;
				kliknutihSanja=0;
				popupBaza.setVisible(true);
				repaint();
			}
		}
		else if(e.getSource()==okZaBazuBodova){
			pass= new String(passZaBazu.getPassword());
			if(pass.equalsIgnoreCase("password")||pass.equalsIgnoreCase("")){
				passZaBazu.setBackground(Color.ORANGE);	
				repaint();
			}
			else{
				passZaBazu.setBackground(Color.WHITE);	
				try {
					s=Db.upis(getCodeBase().toString(),userZaBazu.getText(),pass,Krtica.bodovi.intValue(),Krtica.level-1);
					textZaPopupIzmeduLevela.setText("<p>Spremljeno!<br />Idemo ponovo!<br /Bez tuoriala, odmah level 1<p>");
					
					
				} catch (Exception ex) {
					textZaPopupIzmeduLevela.setText(ex.toString());
				}
				finally{
					popupBaza.setVisible(false);
					popupIzmeduLevela.setVisible(true);
					label.setIcon(backGround[1]);
					setContentPane(label); 
					repaint();					
				}
			}
			
			Krtica.level=1;
			Krtica.bodovi=0;
			bodovi.setText(Krtica.bodovi.toString());
			kolikoOdKoliko.setText(actionCounter+" / "+krticaPoLevelu+"  ("+Krtica.pogodakaOvajLevel  +")   (level "+Krtica.level+" - "+delay+"ms)");
		}
		else if(e.getSource()==notOkZaBazu){
			popupBaza.setVisible(false);
			textZaPopupIzmeduLevela.setText("<p>Idemo ponovo!<br /Bez tuoriala, odmah level 1<p></p>");
			Krtica.bodovi=0;
			Krtica.level=1;
			label.setIcon(backGround[1]);
			setContentPane(label); 
			bodovi.setText(Krtica.bodovi.toString());
			kolikoOdKoliko.setText(actionCounter+" / "+krticaPoLevelu+"  ("+Krtica.pogodakaOvajLevel  +")   (level "+Krtica.level+" - "+delay+"ms)");
			popupIzmeduLevela.setVisible(true);
			repaint();
	
		}
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource()==mile){  
			mile.setCursor (Toolkit.getDefaultToolkit().createCustomCursor(getImage(getCodeBase(), "../resources/prazan.png"), new Point(0,0), "img"));
			mile.setIcon(mile2ico);
			Krtica.brojPogodaka ++;
			Krtica.bodovi+=Krtica.level;
			Krtica.pogodakaOvajLevel ++;
			kolikoOdKoliko.setText(actionCounter+" / "+krticaPoLevelu+"  ("+Krtica.pogodakaOvajLevel  +")   (level "+Krtica.level+" - "+delay+"ms)");
			repaint();
			t.stop();
		}
		if(e.getSource()==sanja){
			Krtica.bodovi= (Krtica.bodovi>=3*Krtica.level)? Krtica.bodovi-3*Krtica.level:0;
			bodovi.setText(Krtica.bodovi.toString());
			kliknutihSanja++;
			sanja.setIcon(sanja2ico);
		}
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		
		if(!t.isRunning()&&actionCounter>0){t.setInitialDelay(0);t.restart();}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==mile){
			mile.setCursor (Toolkit.getDefaultToolkit().createCustomCursor(getImage(getCodeBase(), "../resources/nisan.png"), new Point(32,35), "img"));
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource()==mile){
			mile.setCursor(Cursor.getDefaultCursor());
		}
	}
}
    

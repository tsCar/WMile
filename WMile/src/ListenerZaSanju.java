import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;
import javax.swing.Timer;




public class ListenerZaSanju implements ActionListener{
Krtica krtica;
int a,b;
ImageIcon ic;
public Timer t;
	ListenerZaSanju(Krtica krtica, int a, int b,ImageIcon ic){
		super();
		this.krtica=krtica;
		this.a=a;this.b=b;
		this.ic=ic;
		t=new Timer(1750,this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		krtica.setLocation(ThreadLocalRandom.current().nextInt(0, a),ThreadLocalRandom.current().nextInt(0, b));
		if(krtica.isVisible()){
			krtica.setVisible(false);
		}
		else {
			krtica.setIcon(ic);
			krtica.setVisible(true);
			t.setRepeats(false);
			t.start();
		}
		
	}

}

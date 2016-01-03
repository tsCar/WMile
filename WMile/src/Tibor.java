import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class Tibor implements ActionListener{
Sjena sjena;
	Tibor(Sjena sjena){
		super();
		this.sjena=sjena;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		sjena.setEnabled(true);
		
	}

}

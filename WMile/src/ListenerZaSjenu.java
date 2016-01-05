import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class ListenerZaSjenu implements ActionListener{
Sjena sjena;
	ListenerZaSjenu(Sjena sjena){
		super();
		this.sjena=sjena;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		sjena.setEnabled(true);
		
	}

}

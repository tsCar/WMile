import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;




public class LstenerZaFokus implements FocusListener{
	JTextField textField;
	LstenerZaFokus(JTextField textField) {
		super();
		this.textField=textField;
	}
		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
		}				
		@Override
		public void focusGained(FocusEvent e) {
			textField.setText("");					
		}
		
}

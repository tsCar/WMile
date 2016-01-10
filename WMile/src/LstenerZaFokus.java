import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;




public class LstenerZaFokus implements FocusListener{
	JTextField textField=null;
	JPasswordField passField=null;
	LstenerZaFokus(JTextField textField) {
		super();
		this.textField=textField;
	}
	LstenerZaFokus(JPasswordField passField) {
		super();
		this.passField=passField;
	}
		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
		}				
		@Override
		public void focusGained(FocusEvent e) {
			if (textField != null) textField.setText("");	
			if(passField != null){
				passField.setEchoChar('*');
				passField.setText("");
			}
		}
		
}

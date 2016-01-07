//Preuzeto sa http://stackoverflow.com/questions/13368103/jpanel-drop-shadow 
//preuređeno
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Sjena extends JPanel {

    private static final long serialVersionUID = 1L;

    public int pixels,crvena,modCrvena,zelena,modZelena, plava,modPlava;
    public Sjena(int a){
    	this(a,0,0,0,0,0,0);
    }
    public Sjena(){
    	this(10,0,0,0,0,0,0);
    }
    public Sjena(int pix, int crvena,int modCrvena, int zelena, int modZelena, int plava, int modPlava) {
        this.pixels = pix;
        this.crvena=crvena;this.modCrvena=modCrvena;
        this.zelena=zelena;this.modZelena=modZelena;
        this.plava=plava;this.modPlava=modPlava;
        Border border = BorderFactory.createEmptyBorder(pixels, pixels, pixels, pixels);
        this.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), border));
        this.setLayout(new BorderLayout());
        setOpaque(false); //ovo skriva onaj čudni bug
    }

    @Override
    protected void paintComponent(Graphics g) {
        int topOpacity = 180;
        for (int i = 0; i < pixels; i++) {
            if(!isEnabled()) g.setColor(new Color(crvena+modCrvena*i/3, zelena+modZelena*i/3, plava+modPlava*i/3, ((topOpacity / pixels) * i)));
            else g.setColor(new Color(crvena+modCrvena*i, zelena+modZelena*i, plava+modPlava*i, ((topOpacity / pixels) * i)));
            g.drawRect(i, i, this.getWidth() - ((i * 2) + 1), this.getHeight() - ((i * 2) + 1));
        }
    }
}
//Preuzeto sa http://stackoverflow.com/questions/13368103/jpanel-drop-shadow 
//malo preuređeno
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Sjena extends JPanel {

    private static final long serialVersionUID = 1L;

    public int pixels;

    public Sjena(int pix) {
        this.pixels = pix;
        Border border = BorderFactory.createEmptyBorder(pixels, pixels, pixels, pixels);
        this.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), border));
        this.setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        int shade = 0;
        int topOpacity = 180;
        for (int i = 0; i < pixels; i++) {
            g.setColor(new Color(254-5*i, shade+i, shade+8*i, ((topOpacity / pixels) * i)));
            g.drawRect(i, i, this.getWidth() - ((i * 2) + 1), this.getHeight() - ((i * 2) + 1));
        }
    }
}
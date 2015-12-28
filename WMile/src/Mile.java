import java.applet.*;
import java.awt.*;
import java.awt.event.*;
public class Mile extends Applet implements ActionListener {
    private static final long serialVersionUID = 1;
    Font f = new Font("TimesRoman", 1, 8);
    Image backGround;
    Krtica b;
    TextField text;
    Boolean kliknuto;

    @Override
    public void init() {
        this.setLayout(new FlowLayout());
        this.backGround = this.getImage(this.getCodeBase(), "slika.jpg");
        this.b = new Krtica("b");
        this.add((Component)this.b);
        this.b.addActionListener((ActionListener)this);
        this.kliknuto = false;
        this.text = new TextField("tu \u0107e do\u0107 broj\u010di\u0107i");
        this.add(this.text);
    }

    @Override
    public void start() {
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(this.backGround, 0, 0, this);
    }

    @Override
    public void stop() {
    	Krtica.brojPogodaka=0;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Krtica.brojPogodaka = Krtica.brojPogodaka + 1;
        this.kliknuto = this.kliknuto == false;
        this.backGround = this.kliknuto != false ? this.getImage(this.getCodeBase(), "slika2.jpg") : this.getImage(this.getCodeBase(), "slika.jpg");
        this.text.setText(Krtica.brojPogodaka.toString());
        this.repaint();
    }
}
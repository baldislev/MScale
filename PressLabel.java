import javax.swing.*;
import java.awt.*;

public class PressLabel extends JComponent {
    int id;
    int x;
    int y;
    String note;

    PressLabel(int x, int y, int id, String note){
        super();
        this.x = x;
        this.y = y;
        this.note = note;
    }

    /*@Override
    public void paintComponent(Graphics g){
        //super.paintComponent(g);
        Font f = new Font("Comic Sans MS", Font.BOLD, 20);
        g.setFont(f);
        g.setColor(Color.black);
        g.drawString( note, x, y );
    }*/

    public void drawLabel(Graphics g){
        Font f = new Font("Comic Sans MS", Font.BOLD, 20);
        g.setFont(f);
        g.setColor(Color.black);
        g.drawString( note, x, y );
    }
}

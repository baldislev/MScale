import javax.swing.*;
import java.awt.*;

class PressLabel extends JComponent {
    int id;
    private int x;
    private int y;
    private String note;

    PressLabel(int x, int y, int id, String note){
        super();
        this.x = x;
        this.y = y;
        this.note = note;
    }


    void drawLabel(Graphics g){
        Font f = new Font("Comic Sans MS", Font.BOLD, 20);
        g.setFont(f);
        g.setColor(Color.black);
        g.drawString( note, x, y );
    }
}

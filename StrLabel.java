import javax.swing.*;
import java.awt.*;

/**Any string that is going to be drawn will be instantiated as an object of this class.**/
class StrLabel extends JComponent {
    private int x;
    private int y;
    private String message;

    /**@param x  accepts the horizontal coordinate a the center of an object in which we draw a
     * @param message that holds the string.
     * @param y the same as x but for the vertical coordinate.**/
    StrLabel(int x, int y, String message){
        super();
        this.x = x;
        this.y = y;
        this.message = message;
    }

    /**Since x assumed to be the center of an object in which we draw a string, we need to compensate
     *  a dynamically changing width of a message we want to draw and it is done via xBackPad and
     *  yForwPad.**/
    void drawLabel(Graphics g){
        Font f = new Font("Comic Sans MS", Font.BOLD, 20);
        g.setFont(f);
        g.setColor(Color.black);
        int xBackPad = g.getFontMetrics(f).stringWidth(message)/2;
        int yForwPad = 8;
        g.drawString( message, x - xBackPad, y + yForwPad);
    }
}

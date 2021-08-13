import java.awt.*;

class Mfret extends Rectangle {

    Mfret(int x, int y, int width, int height){
        super(x, y, width, height);
    }

    void draw(Graphics g){
        g.setColor(new Color(255, 255, 255));
        g.fillRect(x, y, width, height);
    }

}

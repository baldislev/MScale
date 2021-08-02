import java.awt.*;

class Mfret extends Rectangle {
    private int id;

    Mfret(int x, int y, int width, int height, int id){
        super(x, y, width, height);
        this.id = id;
    }

    void draw(Graphics g){
        g.setColor(new Color(255, 255, 255));
        g.fillRect(x, y, width, height);
    }

}

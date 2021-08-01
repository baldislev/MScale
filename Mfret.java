import java.awt.*;

public class Mfret extends Rectangle {
    int id;

    Mfret(int x, int y, int width, int height, int id){
        super(x, y, width, height);
        this.id = id;
    }

    public void draw(Graphics g){
        g.setColor(new Color(255, 255, 255));
        g.fillRect(x, y, width, height);
    }

}

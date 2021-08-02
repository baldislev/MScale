import java.awt.*;

public class MString extends Rectangle {
    private int id;
    private int baseNote;

    MString(int x, int y, int width, int height, int baseNote, int id){
        super(x, y, width, height);
        this.baseNote = baseNote;
        this.id = id;
    }

    void draw(Graphics g){
        g.setColor(new Color(187, 194, 204));
        g.fillRect(x, y, width, height);
    }

    int getBaseNote(){
        return this.baseNote;
    }
}

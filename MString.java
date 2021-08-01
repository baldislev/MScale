import java.awt.*;

public class MString extends Rectangle {
    private int id;
    private int baseNote; //may not need since Guitar has this value in tuning array;

    MString(int x, int y, int width, int height, int baseNote, int id){
        super(x, y, width, height);
        this.baseNote = baseNote;
        this.id = id;
    }

    void draw(Graphics g){
        g.setColor(new Color(187, 194, 204));
        g.fillRect(x, y, width, height);
    }

    int getId(){
        return this.id;
    }

    int getBaseNote(){
        return this.baseNote;
    }
}

import java.awt.*;

class MString extends Rectangle {
    /** Graphical object that will be used to draw on the neck of a guitar a string.
     * according to a guitar's tune each string is initiated with a base note - note that is on the
     * fret number 0.**/
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

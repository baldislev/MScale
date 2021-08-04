import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Guitar extends JPanel {

    /**This class is a representation of a guitar's neck, it holds such global
     * information as guitar's measurements, guitar's tuning and a scale that will be
     * represented on the neck of a guitar
     * @mFrets holds objects that represent the frets of a guitar,
     * @mStrings - same holds for this field with respect to strings of a guitar,
     * @strLabels - is a dynamic container of the labels that represent places on the
     * neck of a guitar where a user should press in order to get a note in a scale from the input.**/

    static final int NUM_OF_STRINGS = 6;
    static final int NUM_OF_FRETS = 20;
    static final int GUITAR_WIDTH = 1200;
    static final int GUITAR_HEIGHT = GUITAR_WIDTH/5;//240px
    static final int EXTERNAL_STRING_PAD = GUITAR_HEIGHT/12;//20px
    static final int INTERNAL_STRING_PAD = EXTERNAL_STRING_PAD*2;//40px
    static final int STRING_HEIGHT = INTERNAL_STRING_PAD/10;//4px
    static final int FRET_WIDTH = STRING_HEIGHT;
    static final int FRET_PAD = (GUITAR_WIDTH - NUM_OF_FRETS*FRET_WIDTH)/NUM_OF_FRETS;//56px

    String currentScale;
    int currentBase;

    private MString[] mStrings;
    private Mfret[] mFrets;
    private ArrayList<StrLabel> StrLabels;

    Guitar(String[] strTune) throws MScaleException {
        super();
        Dimension gtr_dim = new Dimension(GUITAR_WIDTH,GUITAR_HEIGHT);
        MTune tune = new MTune(strTune);
        createMStrings(tune);
        createMFrets();
        StrLabels = new ArrayList<StrLabel>();
        this.setBackground(new Color(212,168,83));
        this.setPreferredSize(gtr_dim);
        this.setLayout(new GridLayout());
        this.setScale(MScale.majString, 0);
    }

    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       draw(g);
    }

    private void draw(Graphics g){
        drawMStrings(g);
        drawStrLabels(g);
        drawMFrets(g);
    }

    private void createMStrings(MTune gtrTune){
        mStrings = new MString[NUM_OF_STRINGS];
        int y = EXTERNAL_STRING_PAD;
        int baseNote;
        for(int i = 0; i < NUM_OF_STRINGS; i++){
            baseNote = gtrTune.tune[i];
            mStrings[i] = new MString(0, y, GUITAR_WIDTH, STRING_HEIGHT, baseNote, i);
            y += INTERNAL_STRING_PAD;
        }
    }

    private void drawMStrings(Graphics g){
        for(int i = 0; i < NUM_OF_STRINGS; i++){
            mStrings[i].draw(g);
        }
    }

    private void drawStrLabels(Graphics g){
        if(!StrLabels.isEmpty())
            for (StrLabel StrLabel : StrLabels) {
                StrLabel.drawLabel(g);
            }
    }

    /**the most important method of this class and probably the whole app.
     * Here the neck of a guitar gets labeled in relevant places that will show strings and frets
     * that create the desired notes with respect to a current sCale which is set by:
     * @param scaleType - the type of a scale: minor, major...
     * @param baseNote - the base note upon which the scale is built: C, D...**/
    void setScale(String scaleType, int baseNote) throws MScaleException {
        currentBase = baseNote;
        currentScale = scaleType;

        MScale scale = new MScale(scaleType, baseNote);
        int testNote;
        StrLabel label;
        int x;
        int y = EXTERNAL_STRING_PAD;
        String note;

        for(int i = 0; i < NUM_OF_STRINGS; i++){
            x = FRET_PAD/2 ;
            for(int j = 0; j < NUM_OF_FRETS; j++){
                testNote = (mStrings[i].getBaseNote() + j) % MScale.NUM_OF_NOTES;
                if(scale.isInScale(testNote)){
                    note = MScale.getNoteStrFromInt(testNote);
                    label = new StrLabel(x, y, note);
                    StrLabels.add(label);
                    this.add(label);
                }
                x += FRET_PAD + FRET_WIDTH;
            }
            y += INTERNAL_STRING_PAD;
        }
    }

    /**This method will clear all the labels of a scale and will leave the neck
     * of a guitar clear - just strings and frets. **/
    void clearScale(){
        if(!StrLabels.isEmpty()) {
            for (StrLabel StrLabel : StrLabels) {
                this.remove(StrLabel);
            }
            StrLabels.clear();
        }
    }

    private void createMFrets(){
        mFrets = new Mfret[NUM_OF_FRETS];
        int x = FRET_PAD;
        for(int i = 0; i < NUM_OF_FRETS; i++){
            mFrets[i] = new Mfret(x, 0, FRET_WIDTH, GUITAR_HEIGHT, i);
            x += FRET_PAD + FRET_WIDTH;
        }
    }

    private void drawMFrets(Graphics g){
        for(int i = 0; i < NUM_OF_FRETS; i++){
            mFrets[i].draw(g);
        }
    }
}

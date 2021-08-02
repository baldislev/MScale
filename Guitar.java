import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Guitar extends JPanel {
    static final int NUM_OF_STRINGS = 6;
    static final int NUM_OF_FRETS = 20;
    static final int GUITAR_WIDTH = 1200;
    static final int GUITAR_HEIGHT = GUITAR_WIDTH/5;//240px
    static final int EXTERNAL_STRING_PAD = GUITAR_HEIGHT/12;//20px
    static final int INTERNAL_STRING_PAD = EXTERNAL_STRING_PAD*2;//40px
    static final int STRING_HEIGHT = INTERNAL_STRING_PAD/10;//4px
    static final int FRET_WIDTH = STRING_HEIGHT;
    static final int FRET_PAD = (GUITAR_WIDTH - NUM_OF_FRETS*FRET_WIDTH)/NUM_OF_FRETS;//56px

    static String currentScale;
    static int currentBase;

    private MString[] mStrings;
    private Mfret[] mFrets;
    private ArrayList<PressLabel> pressLabels;

    Guitar(String[] strTune) throws Exception {
        super();
        Dimension gtr_dim = new Dimension(GUITAR_WIDTH,GUITAR_HEIGHT);
        MTune tune = new MTune(strTune);
        createMStrings(tune);
        createMFrets();
        pressLabels = new ArrayList<PressLabel>();
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
        drawPressLabels(g);
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

    private void drawPressLabels(Graphics g){
        if(!pressLabels.isEmpty())
            for (PressLabel pressLabel : pressLabels) {
                pressLabel.drawLabel(g);
            }
    }

    void setScale(String scaleType, int baseNote) throws Exception {
        currentBase = baseNote;
        currentScale = scaleType;

        MScale scale = new MScale(scaleType, baseNote);
        int testNote;
        PressLabel label;
        int x;
        int y = EXTERNAL_STRING_PAD + 2*STRING_HEIGHT;
        String note;

        for(int i = 0; i < NUM_OF_STRINGS; i++){
            x = FRET_PAD/2 - 7;
            for(int j = 0; j < NUM_OF_FRETS; j++){
                testNote = (mStrings[i].getBaseNote() + j) % MScale.NUM_OF_NOTES;
                if(scale.isInScale(testNote)){
                    note = MScale.getNoteStrFromInt(testNote);
                    if(note.length()>1){
                        x -= 8;
                    }
                    label = new PressLabel(x, y, i*NUM_OF_FRETS + j, note);
                    if(note.length()>1){
                        x += 8;
                    }
                    pressLabels.add(label);
                    this.add(label);
                }
                x += FRET_PAD + FRET_WIDTH;
            }
            y += INTERNAL_STRING_PAD;
        }
    }

    void clearScale(){
        if(!pressLabels.isEmpty()) {
            for (PressLabel pressLabel : pressLabels) {
                this.remove(pressLabel);
            }
            pressLabels.clear();
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

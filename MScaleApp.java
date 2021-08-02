import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MScaleApp {
    public static void main(String[] args) throws Exception {

        MScale.initNoteMap();
        MScaleFrame frame = new MScaleFrame(MTune.classicTune);
    }

}

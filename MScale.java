import java.util.ArrayList;
import java.util.HashMap;

class MScale {
    static HashMap<String, Integer> noteMap;
    static final int NUM_OF_NOTES = 12;
    private static int[] majSteps = {2, 2, 1, 2, 2, 2, 1};
    private static int[] minSteps = {2, 1, 2, 2, 1, 2 ,2};
    static final String majString = "Maj";
    static final String minString = "Min";

    private ArrayList<Integer> scaleNotes;

    MScale(String scaleType, int baseNote) throws Exception {
        int[] steps;

        if (scaleType.equals(majString)) {
            steps = majSteps;
        } else {
            if (scaleType.equals(minString)) {
                steps = minSteps;
            } else {
                throw new Exception();
        }
    }
        scaleNotes = new ArrayList<Integer>();
        int noteToAdd = baseNote;
        for (int step : steps) {
            this.scaleNotes.add(noteToAdd % NUM_OF_NOTES);
            noteToAdd += step;
        }
    }

    boolean isInScale(int note){
        return scaleNotes.contains(note);
    }

    static void initNoteMap() {
        noteMap = new HashMap<String, Integer>();
        noteMap.put("C", 0);
        noteMap.put("D", 2);
        noteMap.put("E", 4);
        noteMap.put("F", 5);
        noteMap.put("G", 7);
        noteMap.put("A", 9);
        noteMap.put("B", 11);

        noteMap.put("C#", 1);
        noteMap.put("D#", 3);
        //noteMap.put("E#", 5);
        noteMap.put("F#", 6);
        noteMap.put("G#", 8);
        noteMap.put("A#", 10);
        //noteMap.put("B#", 0);

        /*
        noteMap.put("Cb", 11);
        noteMap.put("Db", 1);
        noteMap.put("Eb", 3);
        noteMap.put("Fb", 4);
        noteMap.put("Gb", 6);
        noteMap.put("Ab", 8);
        noteMap.put("Bb", 10);
        */
    }

    static int strNoteToInt(String note){
        return noteMap.get(note);
    }

    static String getNoteStrFromInt(int noteVal) {
        for(HashMap.Entry<String, Integer> entry : noteMap.entrySet()){
            if(noteVal == entry.getValue()) {
                return entry.getKey();
            }
        }
        return null;
    }
}

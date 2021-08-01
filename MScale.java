import java.util.ArrayList;

public class MScale {
    static final int NUM_OF_NOTES = 12;
    static int[] majSteps = {2, 2, 1, 2, 2, 2, 1};
    static int[] minSteps = {2, 1, 2, 2, 1, 2 ,2};
    static final String majString = "Maj";
    static final String minString = "Min";

    ArrayList<Integer> scaleNotes;

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
}

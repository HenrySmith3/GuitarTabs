import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Henry
 * Date: 9/17/13
 * Time: 10:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class ChordFinder {
    public ArrayList<Chord> chords;

    public ChordFinder(ArrayList<Chord> chords) {
        this.chords = chords;
    }

    public void getAllChordPermutations(PrintWriter pw) {
        StringBuilder retVal = new StringBuilder();
        for (Chord chord : chords) {
            getAllPossibilities(chord, pw);
        }
    }

    //Takes a chord and returns a string of all possibilities of that chord.
    public String getAllPossibilities(Chord chord, PrintWriter pw) {
        ArrayList<SpecificChord> possibilities = new ArrayList<SpecificChord>();
        Note baseNote = getBaseNoteForStringNumber(0);
        int fret = 0;
        while (fret <= 26) {   //SHould this be 25?
            if (chord.notes.contains(baseNote.noteName)) {
                possibilities.add(new SpecificChord(chord.name, fret));
            }
            fret = fret+1;
            baseNote = baseNote.nextUp();
        }
        //So now we have possibilities with the first strings set.
        for (int stringNumber = 1; stringNumber <=6; stringNumber++) {  //second string to last string.
            ArrayList<SpecificChord> newPossibilities = new ArrayList<SpecificChord>();
            fret = 0;
            Note note = getBaseNoteForStringNumber(stringNumber);
            while (fret <= 26) {
                if (chord.notes.contains(note.noteName)) {//if we even want this note;
                    for (SpecificChord specificChord : possibilities) {
                        if (specificChord.canTake(note)) {
                            SpecificChord newChord = new SpecificChord(specificChord);
                            specificChord.add(note);
                            newPossibilities.add(newChord);
                        }
                    }
                }
                note = note.nextUp();
                fret = fret+1;
            }
            possibilities.addAll(newPossibilities);
        }
        String retVal = "";
        for (SpecificChord specificChord : possibilities) {
            if (specificChord.hasAllNotesFor(chord)) {
                pw.write(specificChord.toString());
            }
        }
        return retVal;
    }
    public static Note getBaseNoteForStringNumber(int stringNumber) {
        switch (stringNumber) {
            case 0:
                return new Note("B", 0, 0);
            case 1:
                return new Note("E", 1, 0);
            case 2:
                return new Note("A", 2, 0);
            case 3:
                return new Note("D", 3, 0);
            case 4:
                return new Note("G", 4, 0);
            case 5:
                return new Note("B", 5, 0);
            case 6:
                return new Note("E", 6, 0);
        }
        throw new RuntimeException();  //Invalid name.
    }
}

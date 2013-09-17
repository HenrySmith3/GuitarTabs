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

    public String getAllChordPermutations() {
        String retVal = "";
        for (Chord chord : chords) {
            retVal += getAllPossibilities(chord);
        }
        return retVal;
    }

    //Takes a chord and returns a string of all possibilities of that chord.
    public String getAllPossibilities(Chord chord) {
        ArrayList<SpecificChord> possibilities = new ArrayList<SpecificChord>();
        Note baseNote = getBaseNoteForStringNumber(0);
        int fret = 0;
        while (fret <= 24) {   //SHould this be 25?
            if (chord.notes.contains(baseNote.noteName)) {
                possibilities.add(new SpecificChord(chord.name, fret));
            }
            fret = fret+1;
            baseNote = baseNote.nextUp();
        }
        //So now we have possibilities with the first strings set.
        for (int stringNumber = 1; stringNumber <=5; stringNumber++) {  //second string to last string.
            ArrayList<SpecificChord> newPossibilities = new ArrayList<SpecificChord>();
            fret = 0;
            Note note = getBaseNoteForStringNumber(stringNumber);
            while (fret <= 24) {
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
                retVal += specificChord.toString();
            }
        }
        return retVal;
    }
    public static Note getBaseNoteForStringNumber(int stringNumber) {
        switch (stringNumber) {
            case 0:
                return new Note("E", 0, 0);
            case 1:
                return new Note("A", 1, 0);
            case 2:
                return new Note("D", 2, 0);
            case 3:
                return new Note("G", 3, 0);
            case 4:
                return new Note("B", 4, 0);
            case 5:
                return new Note("E", 5, 0);
        }
        throw new RuntimeException();  //Invalid name.
    }
}

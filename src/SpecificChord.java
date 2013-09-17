import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Henry
 * Date: 9/17/13
 * Time: 10:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class SpecificChord {
    public ArrayList<Note> notes = new ArrayList<Note>(7);
    public String name;

    public SpecificChord(String name, int fretForFirstString) {
        this.name = name;
        for (int i = 0; i <= 6; i++) {
            notes.add(i, new Note(i, Note.NOTPLAYED));//initalize with open
        }
        notes.get(0).fretNumber = fretForFirstString;
        notes.get(0).correctNoteName();
    }
    public SpecificChord(SpecificChord specificChord) {
        this.name = specificChord.name;
        this.notes = new ArrayList<Note>(7);
        for (Note note : specificChord.notes) {
            this.notes.add(note.stringNumber, new Note(note));
        }
    }
    public boolean canTake(Note newNote) {
        if (newNote.fretNumber == 0 || newNote.fretNumber == -1) {
            return true;//can always take an open string.
        }
        for (Note note : notes) {
            if (Math.abs(note.fretNumber - newNote.fretNumber) > 6) {
                return false;
            }
        }
        return true;
    }
    public void add(Note note) {
        notes.get(note.stringNumber).fretNumber = note.fretNumber;
        notes.get(note.stringNumber).correctNoteName();
    }

    public boolean hasAllNotesFor(Chord chord) {
        ArrayList<String> chordNotes = new ArrayList<String>(chord.notes);
        for (Note note : notes) {
            note.correctNoteName();
            if (chordNotes.contains(note.noteName)) {
                chordNotes.remove(note.noteName);
            }
        }
        return (chordNotes.isEmpty());
    }

    public String toString() {
        String retVal = "Chord:"+name+"\n";
        for (int i = 0; i <= 6; i++) {
            Note note = ChordFinder.getBaseNoteForStringNumber(i);
            retVal += note.noteName;
            retVal += "---";
            if (notes.get(i).fretNumber == Note.NOTPLAYED) {
                retVal += "X";
            } else {
                retVal += notes.get(i).fretNumber;
            }
            retVal += "---\n";
        }
        return retVal;
    }

}

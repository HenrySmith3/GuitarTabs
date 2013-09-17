/**
 * Created with IntelliJ IDEA.
 * User: Henry
 * Date: 9/17/13
 * Time: 10:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class Note {
    public static final int UNDEFINED = -2;
    public static final int NOTPLAYED = -1;
    public String noteName;
    public int stringNumber = UNDEFINED;
    public int fretNumber = UNDEFINED;

    public Note(String noteName) { //flats only makes this easier.
        if (noteName.contains("#")) {
            noteName = nextLetter(Character.toString(noteName.charAt(0))) + "#";
        }
        this.noteName = noteName;
    }

    public Note(Note note) {
        this.noteName = note.noteName;
        this.stringNumber = note.stringNumber;
        this.fretNumber = note.fretNumber;
    }
    public Note(String noteName, int stringNumber, int fretNumber) {
        this.noteName = noteName;
        this.stringNumber = stringNumber;
        this.fretNumber = fretNumber;
    }
    public Note(int stringNumber, int fretNumber) {
        this.stringNumber = stringNumber;
        this.fretNumber = fretNumber;
        this.noteName = getNoteName(stringNumber, fretNumber);
    }

    public void correctNoteName() {
        this.noteName = getNoteName(stringNumber, fretNumber);
    }

    public static String getNoteName(int stringNumber, int fretNumber) {
        if (fretNumber == -1) {
            return "X";
        }
        Note note = ChordFinder.getBaseNoteForStringNumber(stringNumber);
        note = note.upBy(fretNumber);
        return note.noteName;
    }

    public Note upBy(int frets) {
        Note retVal = new Note(this);
        while (frets > 0) {
            retVal = retVal.nextUp();
            frets = frets-1;
        }
        return retVal;
    }

    public Note nextUp() {

        Note retVal = new Note(this);
        retVal.fretNumber = retVal.fretNumber+1;
        if (retVal.noteName.contains("b")) {
            retVal.noteName = Character.toString(retVal.noteName.charAt(0));
        } else if (noteName.equals("E")) {
            retVal.noteName = "F";
        } else if (noteName.equals("B")) {
            retVal.noteName = "C";
        } else {
            retVal.noteName = nextLetter(retVal.noteName) + "b";
        }
        return retVal;
    }

    private String nextLetter(String s) {
        if (s.equals("A")) {
            return "B";
        }
        else if (s.equals("B")) {
            return "C";
        }
        else if (s.equals("C")) {
            return "D";
        }
        else if (s.equals("D")) {
            return "E";
        }
        else if (s.equals("E")) {
            return "F";
        }
        else if (s.equals("F")) {
            return "G";
        }
        else {
            return "A";
        }
    }

    public boolean equals(Note otherNote) {
        return (otherNote.fretNumber == fretNumber && otherNote.stringNumber == stringNumber);
    }
}

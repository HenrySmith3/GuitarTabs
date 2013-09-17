import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Henry
 * Date: 9/17/13
 * Time: 10:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class Chord {
    public ArrayList<String> notes = new ArrayList<String>();
    public String name;

    public Chord(String name, String notesString) {
        this.name = name;
        for (String note : notesString.split(",")) {
            notes.add(note.trim());
        }
    }
}

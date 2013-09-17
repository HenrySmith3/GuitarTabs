import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException{
        FileInputStream fis = new FileInputStream("src/chords.json");
        BufferedReader br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
        String line;
        String total = "";
        ArrayList<Chord> chords = new ArrayList<Chord>();
        while ((line = br.readLine()) != null) {
            total += line;
        }
        //System.out.println(total);
        JSONArray array = JSONArray.fromObject(total);
        ListIterator iterator = array.listIterator();
        while(iterator.hasNext()) {
            JSONObject object = (JSONObject) iterator.next();
            chords.add(new Chord((String)object.get("Name"), (String)object.get("Notes")));
        }

        ChordFinder chordFinder = new ChordFinder(chords);

        PrintWriter pw = new PrintWriter("Smith_Henry_3.txt");

        chordFinder.getAllChordPermutations(pw);

    }
}

package sudoku;

import java.io.*;
public class Serialize {
    public Serialize(Object obj, File file) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(obj);
            oos.flush();
        } catch(IOException e) {
            System.out.println("Cannot Serialize(IOException)");
        }
    }
}

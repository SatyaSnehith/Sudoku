package sudoku;

import java.io.*;
public class Deserialize {
    Object obj;
    public static void main(String[] args) {
    }
    public Deserialize(File file) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            obj = ois.readObject();
            ois.close();
        } catch(IOException e1) {
            System.out.println(e1);
        } catch(ClassNotFoundException e2) {
            System.out.println(e2);
        }
    }
    public Object getObject() {
        return obj;
    }
}

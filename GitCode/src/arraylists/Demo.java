package arraylists;

import java.util.ArrayList;
import java.util.Collections;

public class Demo {

   private static ArrayList<String> student = new ArrayList<String>();

    public static void main(String[] args) {
        student.add("Joël");
        student.add("Jacco");
        student.add("Wessel");
        student.add("Pepijn");
        student.add("Larissa");
        student.add("Heleen");
        student.add("Erik");
        student.add("Dennis");
        student.add("Mathijs");
        student.add("Martijn");
        student.add("Elaine");
        student.add("Renske");
        student.add("Rowan");
        student.add("Emre");
        student.add("Peter");
        
        
        System.out.println(student);
        Collections.sort(student);
        System.out.println(student);
        Collections.shuffle(student);
        System.out.println(student);
        System.out.println(student.get(5));
    }
}

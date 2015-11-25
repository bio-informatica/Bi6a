package arraylists;

/**
 *
 * @author Martijn
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class ArrayList1 {

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList();

        list.add("G");
        list.add("A");
        list.add("B");
        list.add("D");
        list.add("C");
        list.add("Z");
        list.add("99");
        list.set(2,"W");
        System.out.println(list);
        for (String str:list){
            System.out.println(str);
        }

        Collections.sort(list);
        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);
    }
}

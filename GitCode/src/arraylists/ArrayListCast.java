package arraylists;

/**
 *
 * @author Martijn
 */
import java.util.ArrayList;
import java.util.Collections;

public class ArrayListCast {

    public static void main(String[] args) {
        ArrayList list = new ArrayList();

        list.add(new Integer(10));
        list.add(new Integer(10));
        list.add(new Integer(10));
        list.add(new Character('a'));
        list.add("C");
        list.add("F");
        list.set(2, "X");

        System.out.println((Integer) list.get(1));
        System.out.println(((Integer) list.get(0)).floatValue());
        Collections.sort(list);
        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);
    }
}

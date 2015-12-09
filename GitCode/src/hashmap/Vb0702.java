package hashmap;

// Voorbeeld 0702  Views van HashMap
import java.util.*;

public class Vb0702 {

    public static void main(String args[]) {
        Map<String, Integer> temptabel =
                new HashMap<String, Integer>();
        temptabel.put("maandag", 21);
        temptabel.put("dinsdag", 23);
        temptabel.put("woensdag", 24);
        temptabel.put("donderdag", 21);
        temptabel.put("vrijdag", 19);

        // View van de keys
        Set<String> dagen = temptabel.keySet();

        for (String dag : dagen) {
            System.out.print(dag + " ");
        }

        System.out.println();
        Iterator<String> iter = dagen.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }

        System.out.println();

        // View van de values
        Collection<Integer> temperaturen = temptabel.values();

        for (int temp : temperaturen) {
            System.out.print(temp + " ");
        }

        System.out.println();

        // View van de entry's
        Set<Map.Entry<String, Integer>> entryset = temptabel.entrySet();
        for (Map.Entry<String, Integer> e : entryset) {
            System.out.println(e);
        }


        // Iterator voor de entry's
        Iterator<Map.Entry<String, Integer>> pos = entryset.iterator();
        while (pos.hasNext()) {
            Map.Entry<String, Integer> entry = pos.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}

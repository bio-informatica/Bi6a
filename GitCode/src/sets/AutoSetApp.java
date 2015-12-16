/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sets;

import java.util.HashSet;

/**
 *
 * @author bgnmh
 */
public class AutoSetApp {

    static HashSet<Auto> autoos1, autoos2, autoos3, autoos4;

    public static void main(String[] args) {
        autoos1 = new HashSet<Auto>();
        autoos1.add(new Auto("Stefan", "rood", 110));
        autoos1.add(new Auto("Stan", "groen", 1337));
        autoos1.add(new Auto("Dani", "oranje", 110));
        autoos1.add(new Auto("Tjeerd", "blauw", 1328));
        autoos1.add(new Auto("Anna", "rood", 110));

        autoos2 = new HashSet<Auto>();
        autoos2.add(new Auto("Anna", "rood", 110));
        autoos2.add(new Auto("Dyogo", "rood", 1337));
        autoos2.add(new Auto("Edwin", "wit", 110));
        autoos2.add(new Auto("Charlotte", "roze", 1328));

        System.out.println(autoos1);
        System.out.println(autoos2);

        HashSet verschil1 = new HashSet<Auto>(autoos1);
        verschil1.removeAll(autoos2);

        System.out.println(verschil1);

        HashSet overeenkomst = new HashSet<Auto>(autoos1);
        overeenkomst.retainAll(autoos2);
        System.out.println("Overeenkomst");
        System.out.println(overeenkomst);
    }
}

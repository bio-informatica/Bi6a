/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashmapdemo;

import java.util.HashMap;

/**
 *
 * @author bgnmh
 */
public class HashMapDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<String, String>(6);

        map.put("Osman", "06-84328743");
        map.put("Maarten", "06-23762376327");
        map.put("Lysanne", "023-3467342");
        map.put("Edwin", "06-232734834");
        map.put("Richard", "06-4376342");
        
        System.out.println(map.get("Lysanne"));

    }

}

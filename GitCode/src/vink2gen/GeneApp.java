/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vink2gen;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author bgnmh
 */
public class GeneApp {
    
    static ArrayList genen = new ArrayList<Gene>();
    
    public static void main(String[] args) {
        genen.add(new Gene(782));
        genen.add(new Gene(199));
        genen.add(new Gene(952));
        genen.add(new Gene(235));
        
        
        System.out.println(genen);
        
        Gene g = (Gene)genen.get(1);
        g.setId(209);
        genen.set(1, g);
        
        System.out.println(genen);
        
        Collections.sort(genen, null);
        
        System.out.println(genen);
        
        Collections.reverse(genen);
        
        System.out.println(genen);
        
        
        
    }
    
}

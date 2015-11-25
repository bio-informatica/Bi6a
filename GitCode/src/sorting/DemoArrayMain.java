package sorting;


import sorting.Auto;
import java.util.ArrayList;
import java.util.Collections;

public class DemoArrayMain {
    
    public static void main(String[] args) {
        
        ArrayList<Auto> arr = new ArrayList<Auto>();
        arr.add(new Auto("Khalil","blauw",1000));
        arr.add(new Auto("Brecht","Turquoise",30));
        arr.add(new Auto("Stefan","rood",80));
        arr.add(new Auto("Frank","wit",1337));
        arr.add(new Auto("Stan","geel",1338));
        
        
        
        Collections.sort(arr);
        System.out.println(arr);
        Collections.shuffle(arr);
        System.out.println(arr);
        System.out.println("Dit is de krachtigste auto");
        System.out.println(Collections.max(arr));
        for (Object z:arr){
            System.out.println(((Auto)z).getEigenaar());
        }
        
       
    }
    
}

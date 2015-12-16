package sets;

// Voorbeeld 0805  HashSet
import java.util.*;

public class Vb0805 {
  public static void main( String args[] ) {
    HashSet<String> groep1, groep2, doorsnede, vereniging,
                verschil1, verschil2, symVerschil;

    groep1 = new HashSet<String>();
    groep2 = new HashSet<String>();

    groep1.add( "A" );
    groep1.add( "B" );
    groep1.add( "C" );
    groep1.add( "D" );
    groep1.add( "E" );

    groep2.add( "W" );
    groep2.add( "X" );
    groep2.add( "Y" );
    groep2.add( "Z" );
    groep2.add( "C" );
    groep2.add( "D" );

    // intersect in SQL en Python
    doorsnede = new HashSet<String>( groep1 );
    doorsnede.retainAll( groep2 );
    
    // Union in SQL en Python
    vereniging = new HashSet<String>( groep1 );
    vereniging.addAll( groep2 );
    
    // minus in SQL, difference in Python
    verschil1 = new HashSet<String>( groep1 );
    verschil1.removeAll( groep2 );

    
    verschil2 = new HashSet<String>( groep2 );
    verschil2.removeAll( groep1 );
    
   
    System.out.println( "groep1:          " + groep1 );
    System.out.println( "groep2:          " + groep2 );
    System.out.println( "doorsnede:       " + doorsnede );
    System.out.println( "vereniging:      " + vereniging );
    System.out.println( "groep1 - groep2: " + verschil1 );
    System.out.println( "groep2 - groep1: " + verschil2 );
   
  }
}

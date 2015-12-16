package boom;

// Voorbeeld 6.1a Boomwandeling
import java.util.*;

public class Vb0601a {
  public static void main( String args[] ) {
    BKnoop_1 <String> root, knoopB, knoopC, knoopD,
                   knoopE, knoopF, knoopG;

    // Maak eerst alle knopen
    root = new BKnoop_1<String>( "A" );
    knoopB = new BKnoop_1<String>( "B" );
    knoopC = new BKnoop_1<String>( "C" );
    knoopD = new BKnoop_1<String>( "D" );
    knoopE = new BKnoop_1<String>( "E" );
    knoopF = new BKnoop_1<String>( "F" );
    knoopG = new BKnoop_1<String>( "G" );

    // Verbind nu ouders en kinderen met elkaar
    root.add( knoopB );
    root.add( knoopC );

    knoopB.add( knoopD );
    knoopB.add( knoopE );

    knoopE.add( knoopF );
    knoopE.add( knoopG );
    //System.out.println( root.preOrderToString() );
    
    //knoopC.insert( knoopE, BKnoop.LEFT );
    //System.out.println( root.preOrderToString() );
    
    System.out.println( root.preOrderToString());
  }
}



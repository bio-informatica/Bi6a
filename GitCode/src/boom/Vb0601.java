package boom;

// Voorbeeld 6.1 Boom met BKnoop
import java.util.*;

public class Vb0601 {
  public static void main( String args[] ) {
    BKnoop<String> root, knoopB, knoopC, knoopD,
                   knoopE, knoopF, knoopG;

    // Maak eerst alle knopen
    root = new BKnoop<String>( "A" );
    knoopB = new BKnoop<String>( "B" );
    knoopC = new BKnoop<String>( "C" );
    knoopD = new BKnoop<String>( "D" );
    knoopE = new BKnoop<String>( "E" );
    knoopF = new BKnoop<String>( "F" );
    knoopG = new BKnoop<String>( "G" );

    // Verbind nu ouders en kinderen met elkaar
    root.add( knoopB );
    root.add( knoopC );

    knoopB.add( knoopD );
    knoopB.add( knoopE );

    knoopE.add( knoopF );
    knoopE.add( knoopG );

    // Maak van knoopE, die een kind van knoopB is,
    // een kind van knoopC
    knoopC.insert( knoopE, BKnoop.LEFT );
  }
}



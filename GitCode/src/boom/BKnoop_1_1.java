package boom;

// Klasse voor een knoop voor een binaire boom
// Met boomwandelingen pre-order en level-order
import java.util.*;

public class BKnoop_1<E> {
  private BKnoop_1<E> parent, leftChild, rightChild;
  private E userObject;
  private static StringBuffer buffer;
  private Queue<BKnoop_1<E>> q; // queue voor level-order wandeling
  
  public static final int LEFT = 0;  // public constanten voor het 
  public static final int RIGHT = 1; // toevoegen van linker- of rechterkind

  // Constructors
  public BKnoop_1() {
    this( null );
  }

  public BKnoop_1( E userObject ) {
    parent = null;
    leftChild = null;
    rightChild = null;
    this.userObject = userObject;
  }

  // Methoden
   
  // preOrderToString() levert het resultaat van 
  // een preorder wandeling af in een string
  public String preOrderToString() {
    buffer = new StringBuffer();
    preOrder();            // roep recursieve methode aan
    return buffer.toString();
  }
  
  private void preOrder() {
    buffer.append( userObject.toString() );
    if( leftChild != null )
      leftChild.preOrder();
    if( rightChild != null )
      rightChild.preOrder();
  }
  
  
  // levelOrderToString() levert het resultaat van
  // een level-order wandeling af in een string
  public String levelOrderToString() {
    buffer = new StringBuffer();
    //q = new ArrayDeque< BKnoop<E> >();
    q = new LinkedList<BKnoop_1<E> >();
    q.add( this );
    levelOrder();   
    return buffer.toString();
  }
  
  private void levelOrder() {
    while( !q.isEmpty() ) {
      BKnoop_1<E> knoop = q.remove();
      buffer.append( knoop.userObject.toString() );
      if( knoop.leftChild != null )
        q.add( knoop.leftChild );
      if( knoop.rightChild != null )
        q.add( knoop.rightChild );
    }
  }
  
  
  
  
  public void add( BKnoop_1<E> newChild ) {
    if( leftChild == null ) 
      insert( newChild, LEFT );
    else
      if( rightChild == null )
        insert( newChild, RIGHT );
      else
        throw new IllegalArgumentException( 
                         "Meer dan 2 kinderen" );
  }

  public E get() {
    return userObject;
  }

  public BKnoop_1<E> getLeftChild() {
    return leftChild;
  }

  public BKnoop_1<E> getRightChild() {
    return rightChild;
  }

  public BKnoop_1<E> getParent() {
    return parent;
  }

  public void insert( BKnoop_1<E> newChild, int childIndex ) {
    if( isAncestor( newChild ) )
      throw new IllegalArgumentException( 
                "Nieuw kind is voorouder" );
    if( childIndex != LEFT && 
        childIndex != RIGHT )
      throw new IllegalArgumentException(
                "Index moet 0 of 1 zijn" );

     if( newChild != null ) {
      BKnoop_1<E> oldParent = newChild.getParent();
      if( oldParent != null )
        oldParent.remove( newChild );
    }

    newChild.parent = this;
    if( childIndex == LEFT )
      leftChild = newChild;
    else
      rightChild = newChild;
  }
  
  public boolean isChild( BKnoop_1<E> aNode ) {
    return aNode == null? 
           false :
           aNode.getParent() == this;
  }

  public boolean isAncestor( BKnoop_1<E> aNode ) {
    BKnoop_1<E> ancestor = this;
    while( ancestor != null && ancestor != aNode )
      ancestor = ancestor.getParent();
    return ancestor != null;
  }

  public void remove( BKnoop_1<E> aChild ) {
    if( aChild == null )
      throw new IllegalArgumentException(
                "Argument is null" );

    if( !isChild( aChild ) )
      throw new IllegalArgumentException(
                "Argument is geen kind" );

    if( aChild == leftChild ) {
      leftChild.parent = null;
      leftChild = null;
    }
    else {
      rightChild.parent = null;
      rightChild = null;
    }
  }

  public String toString() {
    return userObject.toString();
  }
}

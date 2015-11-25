package linkedlist;

import java.util.*;

public class LinkedListExample {

  public static void main(String[] args) {
    System.out.println("Linked List Example!");
    LinkedList list = new LinkedList<Integer>();
    int num1 = 11, num2 = 22, num3 = 33, num4 = 44;
    int size;
    Iterator iterator;
    //Adding data in the list
    list.add(num1);
    list.add(num2);
    list.add(num3);
    list.add(num4);

    size = list.size();
    

    System.out.println("-----Print lijst----for loop");
    for (Object i : list) {
      System.out.println(i);
    }
  }
}
package huffman;
/*
 * A HuffMannNode is a node that will be part of the HuffMannTree
 * It contains the character, the number of times the character is present in the text
 * The bitstring that will be used to represent the charachter 
 **/

public class HuffMannNode implements Comparable {

  private HuffMannNode leftSubTreeNode;
  private HuffMannNode rightSubTreeNode;
  private char character;
  private int frequence;
  private static StringBuffer bits;
  private String bitString;

  public HuffMannNode(char character, int frequentie) {
    this.character = character;
    this.frequence = frequentie;
    leftSubTreeNode = null;
    rightSubTreeNode = null;
    bits = new StringBuffer();
  }

  public HuffMannNode(char character, int frequentie, HuffMannNode linkerSubboom, HuffMannNode rechterSubboom) {
    this.character = character;
    this.frequence = frequentie;
    this.leftSubTreeNode = linkerSubboom;
    this.rightSubTreeNode = rechterSubboom;
    bits = new StringBuffer();
  }

  public void setBitString(String str) {
    this.bitString = str;
  }

  public String getBitString() {
    return this.bitString;
  }

  public HuffMannNode getLeftSubTreeNode() {
    return this.leftSubTreeNode;
  }

  public HuffMannNode getRightSubTreeNode() {
    return this.rightSubTreeNode;
  }

  public void setLeftSubTreeNode(HuffMannNode linkerSubboom) {
    this.leftSubTreeNode = linkerSubboom;
  }

  public void setRightSubTreeNode(HuffMannNode rechterSubboom) {
    this.rightSubTreeNode = rechterSubboom;
  }

  public int getFrequence() {
    return frequence;
  }

  public char getCharacter() {
    return this.character;
  }

  public int compareTo(Object objOtherNode) {
    HuffMannNode otherNode = (HuffMannNode) objOtherNode;
    if (this.frequence < otherNode.getFrequence()) {
      return 1;
    } else {
      return -1;
    }

  }

  public void setFrequence(int i) {
    this.frequence = i;
  }

  public void setKarakter(char c) {
    this.character = c;
  }

  public static void addNode(HuffMannNode root, HuffMannNode node) {
    if (root.getRightSubTreeNode() == null) {
      //make new root node
      //the rightnode will refer to the 'root' node that was passed to this method
      HuffMannNode newRoot = new HuffMannNode(root.getCharacter(), root.getFrequence());
      //root = new HuffManKnoop('_', oorspronkelijkeKnoop.getFrequentie() + node.getFrequentie(),node, oorspronkelijkeKnoop);
      newRoot.setBitString(root.getBitString());

      //The real 'root' node is assigned a 'dummy' character.
      //The real node will never use a value that is used... 
      root.setKarakter('_');
      root.setFrequence(newRoot.getFrequence() + node.getFrequence());

      newRoot.setBitString(root.getBitString() + '1');
      node.setBitString(root.getBitString() + '0');
      root.setLeftSubTreeNode(node);
      root.setRightSubTreeNode(newRoot);
      bits.delete(0, bits.length());

    } else if (root.getLeftSubTreeNode().getFrequence() == root.getRightSubTreeNode().getFrequence()) {
      //The frequence of the subnode and the root are equal. 
      //The right subnode becomes the 'parent node'
      addNode(root.getRightSubTreeNode(), node);
    } else if (root.getLeftSubTreeNode().getFrequence() < root.getRightSubTreeNode().getFrequence()) {
      //The frequence of the left node is less then the right node
      //Add the node to the left treenode
      addNode(root.getLeftSubTreeNode(), node);
    } else {
      //The frequence of the right node is less then the left node
      //Add the node to the right treenode
      addNode(root.getRightSubTreeNode(), node);
    }
  }
  /*
   * Static method.
   * 
   * Find the string for a specific bit combination.
   * Also pass the root of the huffmann tree.
   * */

  public static String decodeBitstring(String bitstring, HuffMannNode root) {
    StringBuffer result = new StringBuffer();
    StringBuffer bitstringStringBuffer = new StringBuffer(bitstring);
    HuffMannNode node;

    node = root;
    int counter = 0;

    while (bitstringStringBuffer.length() > 0) {
      while (node.getLeftSubTreeNode() != null) {
        if (bitstringStringBuffer.charAt(counter) == '1') {
          node = node.getRightSubTreeNode();
        } else {
          node = node.getLeftSubTreeNode();
        }
        counter++;
      }
      result.append(node.getCharacter());
      System.out.println("Character found: " + node.getCharacter());
      bitstringStringBuffer.delete(0, counter);
      node = root;
      counter = 0;
    }
    return result.toString();
  }
}

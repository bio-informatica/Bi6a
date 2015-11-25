package bracketchecker;

import java.io.IOException;

public class BracketChecker {

  private String input;

  public BracketChecker(String in) {
    input = in;
  }

  public void check() {
    int stackSize = input.length();
    Stack theStack = new Stack(stackSize);

    for (int j = 0; j < input.length(); j++) {
      char ch = input.charAt(j);
      switch (ch) {
        case '{': // opening symbols
        case '[':
        case '(':
          theStack.push(ch); // push them
          break;

        case '}': // closing symbols
        case ']':
        case ')':
          if (!theStack.isEmpty()) // if stack not empty,
          {
            char chx = theStack.pop(); // pop and check
            if ((ch == '}' && chx != '{') || (ch == ']' && chx != '[') || (ch == ')' && chx != '(')) {
              System.out.println("Error: " + ch + " at " + j);
            }
          } else // prematurely empty
          {
            System.out.println("Error: " + ch + " at " + j);
          }
          break;
        default: // no action on other characters
          break;
      }
    }
    if (!theStack.isEmpty()) {
      System.out.println("Error: missing right delimiter");
    }
  }

  public static void main(String[] args) throws IOException {
    String input = "(9*9)+[((10*10)]/7)";
      System.out.println(input);
    BracketChecker theChecker = new BracketChecker(input);
    theChecker.check();
  }
}


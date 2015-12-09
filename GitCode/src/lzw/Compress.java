package lzw;

import java.io.*;
import java.util.*;

public class Compress {

    final static int MAX_CODES = 4096;
    final static int BYTE_SIZE = 8;
    final static int EXCESS = 4;
    final static int ALPHA = 256;
    final static int MASK1 = 255;
    final static int MASK2 = 15;
    static int leftOver;
    static boolean bitsLeftOver;
    static BufferedInputStream in;
    static BufferedOutputStream out;

    private static void setFiles(String[] args) throws IOException {
        String inputFile, outputFile;


        inputFile = "/home/mart1nus/han/courses/jaar2/owe7/code/HashMap/src/lzw/file.txt";
        System.out.println(args[0]);
        in = new BufferedInputStream(new FileInputStream(inputFile));
        outputFile = inputFile + ".zzz";
        out = new BufferedOutputStream(new FileOutputStream(outputFile));
    }

    private static void output(int pcode) throws IOException {
        int c, d;
        if (bitsLeftOver) {
            d = pcode & MASK1;
            c = (leftOver << EXCESS) + (pcode >> BYTE_SIZE);
            out.write(c);
            out.write(d);
            bitsLeftOver = false;
        } else {
            leftOver = pcode & MASK2;
            c = pcode >> EXCESS;
            out.write(c);
            bitsLeftOver = true;
        }
    }

    private static void compress() throws IOException {
        Hashtable table = new Hashtable();
        for (int i = 0; i < ALPHA; i++) {
            table.put(new Integer(i), new Integer(i));
        }

        int codeUsed = ALPHA;

        int c = in.read();
        if (c != -1) {
            int pcode = c;
            c = in.read();
            while (c != -1) {
                int k = (pcode << BYTE_SIZE) + c;
                Integer e = (Integer) table.get(new Integer(k));
                if (e == null) {
                    output(pcode);
                    if (codeUsed < MAX_CODES) {
                        table.put(new Integer((pcode << BYTE_SIZE) + c), new Integer(codeUsed++));
                    }
                    pcode = c;
                } else {
                    pcode = e.intValue();
                }
                c = in.read();
            }

            output(pcode);
            if (bitsLeftOver) {
                out.write(leftOver << EXCESS);
            }
        }
        in.close();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        setFiles(args);
        compress();
    }
}

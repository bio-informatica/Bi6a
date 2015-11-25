package vink2gen;

/**
 *
 * @author Martijn
 */
import java.io.*;

public class ReadFile {

    private static BufferedReader inFile;

    public static void main(String[] args) {
        File f = new File("");
        String pad = f.getAbsolutePath();
        System.out.println(pad);
        String bestand = pad + "/src/vink2gen/Homo_sapiens.gene_info";
        System.out.println(bestand);
        try {
            String line;
            inFile = new BufferedReader(new FileReader(bestand));
            while ((line = inFile.readLine()) != null) {
                System.out.println(line + "\n");
            }
            inFile.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Bestand niet gevonden");
        } catch (IOException ioe) {
            System.out.println("Kan niet lezen in bestand");
        } catch (Exception e) {
            System.out.println("Onbekende fout: raadpleeg uw systeembeheerder");
        }

    }
}

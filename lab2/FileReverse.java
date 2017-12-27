/**
 * Created by Rhea lingaiah on 7/9/17.
 * 12B
 * rlingaia@ucsc.edu
 */

import java.io.*;
import java.util.Scanner;

public class FileReverse {
    public static void main(String[] args) throws IOException {

        Scanner in = null;
        PrintWriter out = null;
        String line = null;
        String[] reverse = null;
        int n, lineNumber = 0;

        // check number of command line arguments is at least 2
        if (args.length < 2) {
            System.out.println("Usage: FileReverse <input file> <output file>");
            System.exit(1);
        }

        // open files
        in = new Scanner(new File(args[0]));
        out = new PrintWriter(new FileWriter(args[1]));

        // read lines from in, extract and print tokens from each line
        while (in.hasNextLine()) {
            lineNumber++;

            // trim leading and trailing spaces, then add one trailing space so
            // split works on blank lines
            line = in.nextLine().trim() + " ";

            // split line around white space
            reverse = line.split("\\s+");
            for(int i=0;i<reverse.length;i++){
                String stringReversed= stringReverse(reverse[i], reverse[i].length());
                out.println(stringReversed);
            }
        }

        // close files
        in.close();
        out.close();
    }

    public static String stringReverse(String s, int n){
        if (s.length()<2) {
            return s;
        }
        else
            return stringReverse(s.substring(1), n)+ s.charAt(0);

    }
}

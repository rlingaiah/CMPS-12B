/**
 * Created by Rhea Lingaiah
 * rlingaia@ucsc.edu
 * 12B
 * PA 2
 */

import java.io.*;
import java.util.Scanner;

public class Search {

    public static void main(String[] args) throws IOException {
        Scanner in;
        String target;
        if (args.length < 2) {
            System.err.println("Usage: Search file target [target2..]");
            System.exit(1);
        }

        // count the number of lines in file

        int lineCount = 0;
        in = new Scanner(new File(args[0]));
        while (in.hasNextLine()) {
            in.nextLine();
            lineCount++;
        }
        // print out the number of lines in file
        in.close();
        String[] wordArr = new String[lineCount];
        int[] index = new int[lineCount];

        in = new Scanner(new File(args[0]));


        while (in.hasNextLine()) {
            //load arguments into array
            for (int i = 0; i < wordArr.length; i++) {
                wordArr[i] = in.nextLine();
                index[i] = i + 1;

            }
        }
        mergeSort(wordArr, index, 0, wordArr.length - 1);

        if (args.length >= 2) {
            for (int i = 1; i <= args.length - 1; i++) {
                target = args[i];
                binarySearch(wordArr, index, 0, wordArr.length - 1, target);
            }
        }
        in.close();
    }

    //mergeSort and merge will work together to order to put the words in alphabetical order
    static void mergeSort(String[] word, int[] lineNumber, int p, int r) {
        int q;
        if (p < r) {
            q = (p + r) / 2;

            mergeSort(word, lineNumber, p, q);
            mergeSort(word, lineNumber, q + 1, r);
            merge(word, lineNumber, p, q, r);
        }
    }

    static void merge(String[] word, int[] lineNumber, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        String[] L = new String[n1];
        String[] R = new String[n2];
        int[] left = new int[n1];
        int[] right = new int[n2];
        int i, j, k;

        for (i = 0; i < n1; i++) {
            L[i] = word[p + i];
            left[i] = lineNumber[p + i];
        }
        for (j = 0; j < n2; j++) {
            R[j] = word[q + j + 1];
            right[j] = lineNumber[q + j + 1];
        }

        i = 0;
        j = 0;
        for (k = p; k <= r; k++) {
            if (i < n1 && j < n2) {
                if (L[i].compareTo(R[j]) < 0) {
                    word[k] = L[i];
                    lineNumber[k] = left[i];
                    i++;
                } else {
                    word[k] = R[j];
                    lineNumber[k] = right[j];
                    j++;
                }
            } else if (i < n1) {
                word[k] = L[i];
                lineNumber[k] = left[i];
                i++;
            } else {
                word[k] = R[j];
                lineNumber[k] = right[j];
                j++;
            }
        }
    }

    //binarySearch method will look for the key target words
    //if found will print what line number the target is found else print not found
    static String binarySearch(String[] word, int[] lineNumber, int p, int r, String target) {
        int q;
        if (p == r) {
            System.out.println(target + " not found");
            return target + " not found";
        } else {
            q = (p + r) / 2;
            if (target.compareTo(word[q]) == 0) {
                System.out.println(target + " is found on line " + lineNumber[q]);
                return target + "found on line " + lineNumber[q];

            } else if (target.compareTo(word[q]) < 0) {
                return (binarySearch(word, lineNumber, p, q, target));
            } else { // target > A[q]
                return (binarySearch(word, lineNumber, q + 1, r, target));
            }
        }
    }


}
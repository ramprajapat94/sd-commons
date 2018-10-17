package com.sdigitizers.utils.crypto.exp.ioc;

/*
 * Calculate the IOC of a text file
 * 
 * Program usage:
 * java CIT2563Assignment3 ioctext.txt report.txt
 * The program expects the input file to be in the same
 * directory as the program class file.
 * Program prints output to the console and to the file specified
 * on the command line
 */
// Import required libraries for io
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gene Harris
 */
public class IndexOfCoincidence {

    /**
     * Declare main for program entry point
     *
     * @param args this program uses the arguments args[0] = input file args[1]
     * = output file
     */
    public static void main(String[] args) throws FileNotFoundException {

        // Yes, this is both lazy and verbose.  Saves writing a loop.
        int[] letterCount = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int totalLetters = 0;
        int totalChars = 0;
        int numerator = 0;
        int denominator = 0;
        double IOC = 0.0;

        if (2 > args.length) {
            // Failed IQ Test
            System.out.println("Program usage: java HarrisMarvinCIT2563Assignment3 ioctext.txt report.txt");
            System.exit(120);
        } else {
            // Tell the user what we thing we have
            System.out.println("Input file name: " + args[0]);
            System.out.println("Output file name: " + args[1]);
        }

        // Tell the user where we are looking for the file
        System.out.printf(
                "The system is looking for the input file\n"
                + "in the directory: %s\n", new File(".").getAbsolutePath());

        // Create a handle to the input file
        File inFile = new File(args[0]);

        // Create a scanner to
        Scanner scanner = new Scanner(new FileReader(inFile));

        try {
            //first use a Scanner to get each line
            while (scanner.hasNextLine()) {
                // Read the line
                String thisLine = scanner.nextLine().toUpperCase();

                // Count the characters
                for (int i = 0; i < thisLine.length(); i++) {
                    // If we have a letter, then count it    
                    if (Character.isLetter(thisLine.charAt(i))) {
                        letterCount[thisLine.charAt(i) - 'A']++;
                        totalLetters++;
                    }
                    totalChars++;
                }
            }
        } finally {
            scanner.close();
        }

        // 
        // Calculate the IOC
        // First, loop through the characters and
        // calculate the numerator
        for (int i = 0; i < 26; i++) {
            numerator += letterCount[i] * (letterCount[i] - 1);
        }

        // Calculate the denominator
        denominator = totalLetters * (totalLetters - 1);

        // Calculate the IOC
        IOC = (double) numerator / (double) denominator;

        // Write the output
        // Create the file handle
        File outFile = new File(args[1]);

        // Check for errors
        if (outFile == null) {
            throw new IllegalArgumentException("File cannot be null.");
        }

        BufferedWriter output;
        try {
            output = new BufferedWriter(new FileWriter(outFile));
            output.write("Results of IOC calculation");
            System.out.println("Results of IOC calculation");
            output.newLine();
            output.newLine();
            for (int i = 0; i < 26; i++) {

                String tmp = String.format("Character %c Count %d", (char) ('A' + i), letterCount[i]);
                output.write(tmp);
                output.newLine();
                System.out.println(tmp);
            }
            output.newLine();
            output.newLine();
            String tmp = String.format("Total letters in file = %d", totalLetters);
            output.write(tmp);
            System.out.println(tmp);
            output.newLine();
            output.newLine();
            tmp = String.format("IOC = %f", IOC);
            output.write(tmp);
            System.out.println(tmp);
            output.newLine();
            output.newLine();
            tmp = String.format("Total characers in file = %d", totalChars);
            output.write(tmp);
            System.out.println(tmp);
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(
                    IndexOfCoincidence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

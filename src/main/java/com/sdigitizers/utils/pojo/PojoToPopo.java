package com.sdigitizers.utils.pojo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shriram Prajapat
 */
public class PojoToPopo {

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                System.err.println("Please specify a directory path where POJOs are kept");
                return;
            }
            File f = new File(args[0]);
            if (!f.exists()) {
                System.err.println("Such directory doesn't exist");
                return;
            }
            if (f.isFile()) {
                System.err.println("Specify directory path (with only POJOs), not file");
                return;
            }

            File outputDir = new File(f.getAbsolutePath() + File.separator + "popo");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            for (File file : f.listFiles()) {
                if(file.getName().endsWith(".java"))
                convertToPopo(file);
            }

            System.out.println("Prcoess Completed");

            //Desktop.getDesktop().open(outputDir);

        } catch (IOException ex) {
            Logger.getLogger(PojoToPopo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void convertToPopo(File inputFile) throws FileNotFoundException, IOException {
        String filePath = inputFile.getAbsolutePath();
        String fileName = inputFile.getName();

        FileReader reader = new FileReader(filePath);
        BufferedReader br = new BufferedReader(reader);
        List<String[]> variablesLines = new ArrayList<>();

        int variableIndex = 2;

        String line = null;
        while ((line = br.readLine()) != null) {
            if (line.contains("private") && line.endsWith(";")) {
                variablesLines.add(line.split(" "));
            }
        }
        int i = 0;

        outer:
        for (String lines[] : variablesLines) {
            for (String s : lines) {
                if (s.endsWith(";")) {
                    variableIndex = i;
                    break outer;
                } else {
                    i++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        System.out.println("Working for : " + fileName + "----\n");
        sb.append("\n").append("public class " + fileName.substring(0, fileName.indexOf(".")) + " {");
        for (String[] s : variablesLines) {
            sb.append("\n").append("  private $" + s[variableIndex] + "");
            s[variableIndex] = s[variableIndex].substring(0, s[variableIndex].indexOf(";"));
        }

        sb.append("\n").append("\n");

        for (String[] s : variablesLines) {
            sb.append("\n").append("public function get" + s[variableIndex].substring(0, 1).toUpperCase() + s[variableIndex].substring(1) + "(){");
            sb.append("\n").append("  return $" + s[variableIndex] + ";");
            sb.append("\n").append("}");

            sb.append("\n").append("public function set" + s[variableIndex].substring(0, 1).toUpperCase() + s[variableIndex].substring(1) + "( $" + s[variableIndex] + " ){");
            sb.append("\n").append("  this->$" + s[variableIndex] + " = " + s[variableIndex] + ";");
            sb.append("\n").append("}");
        }

        sb.append("\n").append("}");

        File outputFile = new File(inputFile.getParent() + File.separator + "popo" + File.separator + fileName.substring(0, fileName.lastIndexOf(".")) + ".php");

        try {
            try (PrintWriter out = new PrintWriter(outputFile)) {
                out.println(sb);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Error writing file - " + ex.getMessage());
        }
    }
}

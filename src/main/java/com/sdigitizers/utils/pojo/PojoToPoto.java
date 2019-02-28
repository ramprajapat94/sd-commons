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
public class PojoToPoto {

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

            File outputDir = new File(f.getAbsolutePath() + File.separator + "poto");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            for (File file : f.listFiles()) {
                if(file.getName().endsWith(".java"))
                convertToPoto(file);
            }

            System.out.println("Prcoess Completed");

            //Desktop.getDesktop().open(outputDir);

        } catch (IOException ex) {
            Logger.getLogger(PojoToPoto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void convertToPoto(File inputFile) throws FileNotFoundException, IOException {
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
        sb.append("\n").append("export class ").append(fileName.substring(0, fileName.indexOf("."))).append(" {");
        for (String[] s : variablesLines) {
            String javaDataType = s[variableIndex-1];
            String tsDataType = javaDataType;
            if(javaDataType.contains("oolean")){
                tsDataType = "boolean";
            }else if(javaDataType.contains("String") || javaDataType.contains("Local")){
                tsDataType = "string";
            }else if(javaDataType.contains("ouble") || javaDataType.contains("nt") || javaDataType.contains("long")
                    || javaDataType.contains("loat") || javaDataType.contains("hort")){
                tsDataType = "number";
            }else if(javaDataType.contains("List<")){
                String innerType = javaDataType.substring(javaDataType.indexOf("<")+1, javaDataType.indexOf(">"));
                if(innerType.contains("String"))innerType="string";
                tsDataType = "Array<"+innerType+">";
            }
            s[variableIndex] = s[variableIndex].substring(0, s[variableIndex].indexOf(";"));
            sb.append("\n").append(" ").append(s[variableIndex]).append(": ").append(tsDataType).append(";");
        }

        sb.append("\n").append("}");

        File outputFile = new File(inputFile.getParent() + File.separator + "poto" + File.separator + fileName.substring(0, fileName.lastIndexOf(".")) + ".ts");

        try {
            try (PrintWriter out = new PrintWriter(outputFile)) {
                out.println(sb);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Error writing file - " + ex.getMessage());
        }
    }
}

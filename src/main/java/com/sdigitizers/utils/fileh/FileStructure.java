package com.sdigitizers.utils.fileh;

/**
 * Recursively listing files and folders in a directory
 * @author Shriram Prajapat
 */
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FileStructure {

    int tabCounter = 0;

    /**
     * Obtain the list of files and folders in particular location
     * @param directoryPath Path of directory/folder
     */
    public void listFilesAndFolders(String directoryPath) {

        File file = new File(directoryPath);

        if (!file.exists() || !file.isDirectory()) {
            System.out.println("Parameter is not a directory");
            System.exit(1);
        }

        File[] fileArray = file.listFiles();

        for (File fileArray1 : fileArray) {
            if (fileArray1.isDirectory()) {
                System.out.println(getTabs() + "- " + fileArray1.getName());
                tabCounter++;
                listFilesAndFolders(fileArray1.getAbsolutePath());
            } else {
                System.out.println(getTabs() + fileArray1.getName());
            }
        }
        tabCounter--;

    }
    // provide a tab space to represent tree structure

    private String getTabs() {

        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < tabCounter; i++) {
            buffer.append("\t");
        }

        return buffer.toString();
    }

    public static void main(String[] args) {
    while(true){
        FileStructure fileStructure = new FileStructure();
        System.out.println("Which movie you would like to watch..?");
        Scanner scn = new Scanner(System.in);
        fileStructure.searchFile("E:\\Entertainment\\Movies", scn.next());
       }
    }
    
    
    int count = 0;
    private File searchFile(String dirPath, String fileNametoSearch) {
        File f = new File(dirPath);
        File[] files = f.listFiles();

        if (files != null)
        for (File file : files) {
            count++;
            if(file.isFile() && file.getName().contains(fileNametoSearch)){
                return file;
            }
            if (file.isDirectory()) {
                searchFile(file.getAbsolutePath(), fileNametoSearch); 
            }
        }
        return null;
    }

   //Files.list(Paths.get("your/path/here")).count();
    public static int getFilesCount(File file) {
        File[] files = file.listFiles();
        int count = 0;
        for (File f : files)
          if (f.isDirectory())
            count += getFilesCount(f);
          else
            count++;

        return count;
    }
    
    public static Integer countFiles(File folder, Integer count) {
        File[] files = folder.listFiles();
        for (File file: files) {
            if (file.isFile()) {
                count++;
            } else {
                countFiles(file, count);
            }
        }

        return count;
    }
    
    
  public static long fileCount(Path dir) throws IOException { 
    return Files.walk(dir)
                .parallel()
                .filter(p -> !p.toFile().isDirectory())
                .count();
  }
  
}

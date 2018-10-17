package com.sdigitizers.utils.crypto.exp.classical;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Shriram Prajapat
 */
public class PlayFair {
    
    public static String getCipherText(String key, String plainText) {
        key = key.toUpperCase();
        plainText = plainText.toUpperCase();
        String alphabets = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        String completeSet = key+alphabets;
        StringBuilder cipherText = new StringBuilder("");
        
        List<Character> alphabetSet = new ArrayList<>(25);
        for(int i=0; i<completeSet.length(); i++){
         if(!alphabetSet.contains(completeSet.charAt(i))){
             alphabetSet.add(completeSet.charAt(i));
         }
        }
        
        ///Generating Matrix
        char matrix[][] = new char[5][5];
        int i=0,j=0;
        for(char c : alphabetSet){
            matrix[i][j] = c;
            j++;
            if(j==5){i++;j=0;}
        }
        
        ///Printing Information
        System.out.println("KeyWord is : "+key);
        System.out.println("Plain text is : "+plainText);
        System.out.println("The Matrix is : ");
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 5; l++) {
                System.out.print(matrix[k][l]+"\t");
            }System.out.println();
        }
        
        
        ///Removing spaces
        plainText = plainText.replaceAll(" ", "");
        ///Making pairs / Collecting digraphs
        System.out.print("The digraphs are : ");
        StringBuilder digraphString = new StringBuilder("");
        for (int k = 0; k < plainText.length(); k+=2) {
            char c1 = plainText.charAt(k);
            char c2;
            if(k==plainText.length()-1){
              c2 = 'X';
            }else{
              c2 = plainText.charAt(k+1);
            }
            
            if(c1==c2) {c2 = 'X';  k--;}
            digraphString.append(c1);
            digraphString.append(c2);
           
            System.out.print(c1+""+c2+" ");
        }
        
        String digraph = digraphString.toString();
        for (int k = 0; k < digraph.length(); k+=2) {
            cipherText.append(getSubstitute(digraph.charAt(k), digraph.charAt(k+1), matrix)).append(" ");
        }
        System.out.println("\nThe cipher text is : "+cipherText.toString());
        
        return cipherText.toString();
    }
    
    static char[] getSubstitute(char c1, char c2, char[][] matrix){
        char s1,s2;
        int pos1[] = findPosition(c1, matrix);
        int pos2[] = findPosition(c2, matrix);
        
        if(pos1[0]==pos2[0] && pos1[1]==pos2[1]){
            s1 = c1;
            s2 = c2;
        }else
        ///If in the same row
        if(pos1[0]==pos2[0]){
            if(pos1[1]<4){
                s1 = matrix[pos1[0]][pos1[1]+1];
            }else{
                s1 = matrix[pos1[0]][0];
            }
            if(pos2[1]<4){
                s2 = matrix[pos2[0]][pos2[1]+1];
            }else{
                s2 = matrix[pos2[0]][0];
            }
        } else
        
        ///If in the same column
        if(pos1[1]==pos2[1]){
            if(pos1[0]<4){
                s1 = matrix[pos1[0]+1][pos1[1]];
            }else{
                s1 = matrix[0][pos1[1]];
            }
            if(pos2[0]<4){
                s2 = matrix[pos2[0]+1][pos2[1]];
            }else{
                s2 = matrix[0][pos2[1]];
            }
        } else{
        
        ///If they form a rectangle
        
           s1 = matrix[pos1[0]][pos2[1]];
           s2 = matrix[pos2[0]][pos1[1]];
        }
        
        return new char[]{s1,s2};
    }
    
    static int[] findPosition(char c, char[][] matrix){
        if(c=='J')c='I';
        for(int i=0; i<5; i++){
            for (int j = 0; j < 5; j++) {
                if(matrix[i][j]==c){
                   return new int[]{i,j};
                }
            }
        }
        return new int[]{0,0};
    }
    
}

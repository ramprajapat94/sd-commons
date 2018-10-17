package com.sdigitizers.utils.crypto.exp.ioc;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class IOC {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Input a string of characters: ");
        String text = input.nextLine();
        
        double indexOfCoincidence = indexOfCoincidence(text);
        System.out.println("The index of Coincidence is " + indexOfCoincidence);
    }
    
    public static double indexOfCoincidence(String text) {
       text = text.trim();
       int length = text.length();
       Map<Character,Integer> map = new HashMap<>();
        
       for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            if(map.containsKey(c)){map.replace(c, map.get(c)+1);}
            else{map.put(c, 1);}
        }
       
       for(char c : map.keySet()){
           map.replace(c, map.get(c)*(map.get(c)-1));
       }
        System.out.println(map);
        
       double sum=0;
       for(int i : map.values()){
           sum += i;
       }
       
       System.out.println(sum);
       double ic = sum / (length * (length-1));
       return ic;
    }
}

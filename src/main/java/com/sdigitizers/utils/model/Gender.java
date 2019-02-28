
package com.sdigitizers.utils.model;

/**
 *
 * @author Shriram Prajapat
 */
public enum Gender {
    MALE('M'), FEMALE('F'), TRANSGENDER('T'), OTHER('O');
    
    private final char code;
    private Gender(char c){
        this.code = c;
    }
    
    public final char getCode(){
        return code;
    }
    
    public static Gender valueOf(char c){
        switch(c){
            case 'M' : return MALE;
            case 'F' : return FEMALE;
            case 'T' : return TRANSGENDER;
            case 'O' : return OTHER;
            default  : return OTHER;
        }
    }
}

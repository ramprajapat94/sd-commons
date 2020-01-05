
package com.sdigitizers.utils.java8;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shriramprajapat
 */
public class LocalDateUtil {
    
    public static List<LocalDate> getAllDatesBetween(LocalDate from, LocalDate upto){
        long days = ChronoUnit.DAYS.between(from, upto);
        
        List<LocalDate> dates = new ArrayList<>((int)days+2);
        for(long i=0; i<=days; i++){
            dates.add(from.plusDays(i));
        }
        return dates;
    }
    
//    public static void main(String[] args) {
//        List<LocalDate> dates = getAllDatesBetween(LocalDate.parse("2019-09-18"), LocalDate.parse("2019-10-05"));
//        for(LocalDate ld : dates){
//            System.out.println(ld);
//        }
//    }
    
}

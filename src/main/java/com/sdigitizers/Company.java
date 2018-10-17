
package com.sdigitizers;

import java.util.ResourceBundle;

/**
 *
 * @author Shriram Prajapat
 */
public class Company {
   private final static String RESOURCE_FILE = "company";
   
   public final static String SHORT_NAME;
   public final static String NAME;
   public final static String TITLE;
   public final static String WEBSITE;
   public final static String EMAIL;
   public final static String PHONE;
   
   static{
       SHORT_NAME = ResourceBundle.getBundle(RESOURCE_FILE).getString("short_name");
       NAME = ResourceBundle.getBundle(RESOURCE_FILE).getString("name");
       TITLE = ResourceBundle.getBundle(RESOURCE_FILE).getString("title");
       WEBSITE = ResourceBundle.getBundle(RESOURCE_FILE).getString("website");
       EMAIL = ResourceBundle.getBundle(RESOURCE_FILE).getString("email");
       PHONE = ResourceBundle.getBundle(RESOURCE_FILE).getString("phone");
       
       System.out.println("************      ************");
       System.out.println("*          *      *           *");
       System.out.println("*                 *            *");
       System.out.println("*                 *            *");
       System.out.println("*                 *            *");
       System.out.println("************      *            *");
       System.out.println("           *      *            *");
       System.out.println("           *      *            *");
       System.out.println("           *      *            *");
       System.out.println("*          *      *           *");
       System.out.println("************      ************");
       
       System.out.println("(C) 2018, SANATAN DIGITIZERS PRIVATE LIMITED");
       
       
   }
 
}

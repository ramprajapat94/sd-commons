
package com.sdigitizers.utils.model.address;

import com.google.gson.reflect.TypeToken;
import com.sdigitizers.utils.fileh.JsonUtil;

/**
 * POJO file for address representation
 * @author Shriram Prajapat
 */
public class Address {
    
   private String street;
   private String locality;
   private String landmark;
   private String city;
   private String district;
   private State state;
   private int pincode;
   private String country;
   private double latitude;
   private double longitude;
   
    /**
     * Get Street From Address
     * @return Street From Address
     */
    public String getStreet() {
        return street;
    }
    /**
     * Set Street for Address
     * @param street
     * @return Street for Address
     */
    public Address setStreet(String street) {
        this.street = street;
        return this;
    }
    /**
     * Get Pincode From Address
     * @return Pincode From Address
     */
    public int getPincode() {
        return pincode;
    }
    /**
     * Set Pincode for Address
     * @param pincode
     * @return Pincode for Address
     */
    public Address setPincode(int pincode) {
        this.pincode = pincode;
        return this;
    }
    
    
    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
    
    /**
     * Get City From Address
     * @return City From Address
     */
    public String getCity() {
        return city;
    }
    /**
     * Set city for Address
     * @param city
     * @return city for Address
     */
    public Address setCity(String city) {
        this.city = city;
        return this;
    }
    /**
     * Get District From Address
     * @return District From Address
     */
    public String getDistrict() {
        return district;
    }
    /**
     * Set District for Address
     * @param district
     * @return District for Address
     */
    public Address setDistrict(String district) {
        this.district = district;
        return this;
    }
    /**
     * Get State From Address
     * @return State From Address
     */
    public State getState() {
        return state;
    }
    /**
     * Set State for Address
     * @param state
     * @return State for Address
     */
    public Address setState(State state) {
        this.state = state;
        return this;
    }
    /**
     * Get Country From Address
     * @return Country From Address
     */
    public String getCountry() {
        return country;
    }
    /**
     * Set Country for Address
     * @param country
     * @return Country for Address
     */
    public Address setCountry(String country) {
        this.country = country;
        return this;
    }
    
    /**
     * @return Latitude of the place
     */
    public double getLatitude() {
        return latitude;
    }
    /**
     * @param latitude Latitude of the place
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    /**
     * @return Longitude of the place
     */
    public double getLongitude() {
        return longitude;
    }
    /**
     * @param longitude Longitude of the place
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    
    /**
     * Represents Complete Address in Standard Form
     * @return Complete Address String
     */
    public String getCompleteAddress(){
        return getStreet()+", "+getCity()+", "+getDistrict()+", "+getState().getCode()+", "+getCountry()+" ["+getPincode()+"]";
    }

    @Override
    public String toString() {
        return getCompleteAddress();
    }
    
    
    /**
     * Convert this java object to JSON String
     * @return JSON String
     */
    public final String toJson(){
        return JsonUtil.GSON.toJson(this);
    }
    /**
     * Convert JSON String to Java object
     * @param jsonData
     * @return Java object
     */
    public static final Address fromJSON(String jsonData) {
        return JsonUtil.GSON.fromJson(jsonData, new TypeToken<Address>(){}.getType());
    }

    

   
}


package com.sdigitizers.utils.model.address;

/**
 *
 * @author Shriram Prajapat
 */
public class State {
    
    private String code;
    private String name;
    private int tin;
    private String type;
    /**
     * Default Constructor 
     */
    public State() {
    }
    /**
     * 
     * @param code State Code
     */
    public State(String code) {
        this.code = code;
    }
    /**
     * 
     * @param code State Code 
     * @param name State Name
     */
    public State(String code, String name) {
        this.code = code;
        this.name = name;
    }
    /**
     * 
     * @param code State Code 
     * @param name State Name
     * @param tin  State TIN
     */
    public State(String code, String name, int tin) {
        this.code = code;
        this.name = name;
        this.tin = tin;
    }
    /**
     * Get State Code (e.g AS for Assam)
     * @return State Code
     */
    public String getCode() {
        return code;
    }
    /**
     * Set State Code (e.g AS for Assam)
     * @param code
     * @return State Code
     */
    public State setCode(String code) {
        this.code = code;
        return this;
    }
    /**
     * Get State Name 
     * @return State Name
     */
    public String getName() {
        return name;
    }
    /**
     * Set State Name
     * @param name
     * @return State Name
     */
    public State setName(String name) {
        this.name = name;
        return this;
    }
    /**
     * Get State TIN (e.g 18 for Assam)
     * @return State Tin
     */
    public int getTIN() {
        return tin;
    }
    /**
     * Set State TIN
     * @param TIN
     * @return State TiN
     */
    public State setTIN(int TIN) {
        this.tin = TIN;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    

    @Override
    public String toString() {
        return getName()+" ["+getTIN()+"]";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        return this.getCode().equalsIgnoreCase(other.getCode());
    }

    
}

package arenaCharacter;

import java.util.ArrayList;

/**
 * Program Name:    Stat.java
 *<p>
 * Purpose:         The purpose of this class is to make stats that can be
 *                  implemented on ArenaCharacters.
 *<p>
 * @version         0.0
 *<p>
 * Created:         February 16, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

public class Stat{
    /**
     * This class will create a stat object that can be modified.
     *
     * @param name:   The name of the stat.
     * @param minVal: The minimum value of the stat.
     * @param maxVal: The maximum value of the stat.
     * @param val:    The current value of the stat.
     *
     * @param healRate: The rate at which this state heals.
     * @param dmgRate:  The rate at which this stat takes damage.
    */
	
	public enum StatType {
		HP,
		MP,
		SP,
		PHYSDEF,
		MAGDEF,
		CRIT,
		SPEED;
		
	}

    private final double WEAK_MULT = 1.2567;

    private StatType statType;
    private double minVal;
    private double maxVal;
    private double val;

    private boolean immune;
    private boolean weak;
    private double healRate;
    private double dmgRate;

    // These will store the last value that changed the stat.
    private double lastHeal;
    private double lastDmg;
    
    private ArrayList<String> info;

//CONSTRUCTORS---------------------------------------------------------------------

    public Stat(){
        /**
         * Default constructor for class Stat.
        */
        statType   = StatType.HP;
        minVal = 0.0;
        maxVal = 0.0;
        val    = 0.0;

        immune = false;
        weak = false;
        healRate = 0.01;
        dmgRate  = 0.01;
        lastHeal = 0.0;
        lastDmg = 0.0;
        
        info = new ArrayList<String>();
        setInfo();
    }

    public Stat(StatType statType){
        /**
         * Constructor for class Stat.
         *
        */

        this.statType = statType;
        this.minVal = 0.0;
        this.maxVal = 0.0;
        this.val    = 0.0;

        immune = false;
        weak = false;
        healRate = 0.01;
        dmgRate  = 0.01;
        lastHeal = 0.0;
        lastDmg = 0.0;
        
        info = new ArrayList<String>();
        setInfo();
    }

//SETTERS--------------------------------------------------------------------------

    public void setStatType(StatType statType){
        /**
         * Setter for field: statType
        */

        this.statType = statType;
    }

    public void setMinVal(double minVal){
        /**
         * Setter for field: minVal
        */

        // Check if minVal is less than maxVal.
        if (minVal < maxVal){
            this.minVal = minVal;
        }
    }

    public void setMaxVal(double maxVal){
        /**
         * Setter for field: maxVal
        */

        // Check if maxVal is greater than minVal.
        if (maxVal > minVal){
            this.maxVal = maxVal;
        }
        setInfo();
    }

    public void setVal(double val){
        /**
         * Setter for field: val
        */

        if (!immune) {
            // Check if value is in-between min and max.
            if (val >= minVal && val <= maxVal){
                this.val = val;
            }
            else if (val < minVal){ // Set to min if less than min.
                this.val = minVal;
            }
            else if (val > maxVal){ // Set to max if greater than max.
                this.val = maxVal;
            }
        }
        setInfo();
    }

    public void immune(boolean immune) {
        /**
         * Setter for field: immune
        */

        this.immune = immune;

        if (this.immune) {
            weak(false);
        }
    }

    public void weak(boolean weak) {
        /**
         * Setter for field: weak
        */

        this.weak = weak;

        if (this.weak) {
            immune(false);
        }
    }

    public void setHealRate(double healRate){
        /**
         * Setter for field: healRate
        */

        // Must be greater than 0.
        if (healRate > 0){
            this.healRate = healRate;
        }
    }

    public void setDmgRate(double dmgRate){
        /**
         * Setter for field: dmgRate
        */

        // Must be greater than 0.
        if (dmgRate > 0){
            this.dmgRate = dmgRate;
        }
    }

    public void setLastHeal(double lastHeal){
        /**
         * Setter for field: lastHeal
        */

        this.lastHeal = lastHeal;
    }

    public void setLaastDmg(double lastDmg){
        /**
         * Setter for field: lastDmg
        */

        this.lastDmg = lastDmg;
    }

//GETTERS--------------------------------------------------------------------------

    public StatType getStatType(){
        /**
         * Getter for field: statType
        */

        return statType;
    }

    public double getMinVal(){
        /**
         * Getter for field: minVal
        */

        return minVal;
    }

    public double getMaxVal(){
        /**
         * Getter for field: maxVal
        */

        return maxVal;
    }

    public double getVal(){
        /**
         * Getter for field: val
        */

        return val;
    }

    public boolean isImmune() {
        /**
         * Getter for field: immune
        */

        return immune;
    }

    public boolean isWeak() {
        /**
         * Getter for field: weak
        */

        return weak;
    }

    public double getWeakMult() {
        /**
         *
        */

        return WEAK_MULT;
    }

    public double getHealRate(){
        /**
         * Getter for field: healRate
        */

        return healRate;
    }

    public double getDmgRate(){
        /**
         * Getter for field: dmgRate
        */

        return dmgRate;
    }

    public double getLastHeal(){
        /**
         * Getter for field: lastHeal
        */

        return lastHeal;
    }

    public double getLaastDmg(){
        /**
         * Getter for field: lastDmg
        */

        return lastDmg;
    }


//HEAL---------------------------------------------------------------------------

    public void fullHeal(){
        /**
         * This method will set field val to field maxVal.
        */

        setLastHeal(getMaxVal() - getVal());
        setVal(getMaxVal());
    }

    public void heal(double val){
        /**
         * This method will add healRate to the field val until it has reached
         * param val or until the field val >= maxVal.
        */

        // Must check for getVal < maxVal to stop redundant iterations.
        // Otherwise will continuosly set to maxVal after maxVal has been reached.
        for (double check = 0.0;
             check < val && getVal() < getMaxVal();
             check += getHealRate()
        ){
            setVal(getVal() + getHealRate());
        }

        setLastHeal(val);
    }

//DMG-------------------------------------------------------------------------------

    public void fullDmg(){
        /**
         * This method will set field val to field minVal.
        */

        setLaastDmg(getVal());
        setVal(getMinVal());
    }

    public void dmg(double val){
        /**
         * This method will minus dmgRate from the field val until it has reached
         * param val or until the field val <= minVal.
        */

        if (isWeak()) {
            weak(false);
            val *= WEAK_MULT;
        }

        // Must check for getVal > minVal to stop redundant iterations.
        // Otherwise will continuosly set to minVal after minVal has been reached.
        for (double check = 0.0;
             check < val && getVal() > getMinVal();
             check += getDmgRate()
        ){
            setVal(getVal() - getDmgRate());
        }

        setLaastDmg(val);
    }

//DISP-----------------------------------------------------------------------------

    public void disp() {
        /**
         *
        */

        System.out.println("[" + getStatType() + "] - - -");
        System.out.println("MIN: " + getMinVal());
        System.out.println("VAL: " + getVal());
        System.out.println("MAX: " + getMaxVal());
        System.out.println("IMMUNE: " + isImmune());
        System.out.println("WEAK: " + isWeak());
        System.out.println("HEAL_RATE: " + getHealRate());
        System.out.println("DMG_RATE: " + getDmgRate());
    }
    
    public void setInfo() {
    	/*
    	 * 
    	*/
    	
    	String valInfo;
    	
    	getInfo().clear();
    	getInfo().add(getStatType().toString() + ":");
    	
    	valInfo = "[ " + String.format("%.2f", getVal());
    	
    	switch (getStatType()) {
    	case HP:
    	case MP:
    	case SP: 
    		valInfo += " / " + String.format("%.2f", getMaxVal());
    		valInfo += " ]";
    		break;
		default: valInfo += " ]";
			break;
    	}
    	
    	getInfo().add(valInfo);
    }
    
    public ArrayList<String> getInfo() {
    	/*
    	 * 
    	*/
    	
    	return info;
    }
}

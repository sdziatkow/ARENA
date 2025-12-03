package item.weapon;

import arenaEnum.itemInfo.AttackType;

/**
 * Program Name:    WeaponAttack.java
 *<p>
 * Purpose:         The purpose of this program is to make weapon attacks that can
 *                  be used with class Weapon.
 *<p>
 * @version         0.0
 *<p>
 * Created:         February 23 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

public class WeaponAttack {
    /**
     * @param name:   The name of the attack.
     * @param minDmg: The min damage of attack.
     * @param maxDmg: The max damage of attack.
     * @param dmg:    The current damage of the attack.
     * @param dmgMod: Will always be ADDED to damage.
     *
     * @param randGen: Random generator for DOUBLE dmg.
    */
	
	private AttackType type;

    private String name;

    private double minDmg;
    private double maxDmg;

    private double dmg;
    private double dmgMod;
    private RandGen dmgGen;


//CONSTRUCTORS---------------------------------------------------------------------
    
    public WeaponAttack() {
        /**
         * Default constructor.
         *
        */
    	
    	this.type = AttackType.PHYS;
    	
        this.name   = "ERROR";
        this.minDmg = 0.0;
        this.maxDmg = 1.0;

        dmgGen = new RandGen(this.minDmg, this.maxDmg);
    }

    public WeaponAttack(
    		AttackType type, 
    		String name, 
    		double minDmg, 
    		double maxDmg
    		) 
    {
        /**
         * Constructor for class WeaponAttack.
         *<p>
         * Parameters:
         *<p>
         * name:   The name of the attack.
         * maxDmg: The max damage of attack.
         * minDmg: The min damage of attack.
         *
        */
    	
    	this.type = type;
    	
        this.name   = name;
        this.minDmg = minDmg;
        this.maxDmg = maxDmg;

        dmgGen = new RandGen(this.minDmg, this.maxDmg);
    }

//SETTERS--------------------------------------------------------------------------

    public void setName(String name) {
        /**
         * Setter for field: name
        */

        this.name = name;
    }

    public void setDmg(double dmg) {
        /**
         * Setter for field: dmg
        */

        this.dmg = dmg;
    }

    public void setDmgMod(double dmgMod) {
        /**
         * Setter for field: dmg
        */

        this.dmgMod = dmgMod;
    }

//GETTERS--------------------------------------------------------------------------
    
    public AttackType getType() {
    	/*
    	 * Getter for field: type 
    	*/
    	
    	return type;
    }

    public String getName() {
        /**
         * Getter for field: name
        */

        return name;
    }
    
    public double getMinDmg() {
        /**
         * Getter for field: dmg
        */

        return minDmg;
    }
    
    public double getMaxDmg() {
        /**
         * Getter for field: dmg
        */

        return maxDmg;
    }

    public double getDmg() {
        /**
         * Getter for field: dmg
        */

        return dmg;
    }

    public double getDmgMod() {
        /**
         * Getter for field: dmg
        */

        return dmgMod;
    }

//attks--------------------------------------------------------------------------

    public double genDmgNoMod() {
        /**
         * This method will gen damage without adding the damage mod.
        */

        setDmg(dmgGen.genDouble());

        return getDmg();

    }

    public double genDmg() {
        /**
         * This method will set damage to a random double between minDmg and
         * maxDmg plus dmgMod.
        */

        setDmg(dmgGen.genDouble() + getDmgMod());

        return getDmg();
    }

}

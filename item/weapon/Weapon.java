package item.weapon;

/**
 * Program Name:    Weapon.java
 *<p>
 * Purpose:         The purpose of this program is to create Weapon Items that 
 *                  can be used by ArenaCharacters.
 *<p>
 * @version         0.0
 *<p>
 * Created:         February 23 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import item.*;
import arenaCharacter.*;
import arenaCharacter.Stat.StatType;
import item.weapon.WeaponAttack.AttackType;
import sprite.weaponSprite.WeaponSprite;

public abstract class Weapon extends Item{
    /**
     *
    */

    // field attk will be defined with attk1 and attk2
    private WeaponAttack attk;
    private WeaponAttack attk1;
    private WeaponAttack attk2;
    
    // Base dmg of weapon.
    private double baseDmg;

    // This value will be defined based on attk
    private double attkDmg;

    private ArenaCharacter target;
    private ArenaCharacter self;
    
    private WeaponSprite sprite;

//CONSTRUCTORS---------------------------------------------------------------------

    public Weapon() {
        /**
         *
        */

        super(ItemType.WEAPON);
        sprite = new WeaponSprite();
    }

//SETTERS--------------------------------------------------------------------------
    
    public void setBaseDmg(double baseDmg) {
    	/**
    	 * 
    	*/
    	
    	this.baseDmg = baseDmg;
    }

    public void setAttk1(WeaponAttack attk1) {
        /**
         * Setter for field: attk1
        */

        this.attk1 = attk1;
    }

    public void setAttk2(WeaponAttack attk2) {
        /**
         * Setter for field: attk1
        */

        this.attk2 = attk2;
    }

    public void setAttk(int num) {
        /**
         * Setter for field: attk
        */

        switch (num) {
            case 1:
                this.attk = attk1;
                break;
            case 2:
                this.attk = attk2;
                break;
        }
    }

    public void setTarget(ArenaCharacter target) {
        /**
         * Setter for field: target
        */

        this.target = target;
    }

    public void setSelf(ArenaCharacter self) {
        /**
         * Setter for field: target
        */

        this.self = self;
    }

    public void setAttkDmg(double attkDmg) {
        /**
         * Setter for field: attkDmg
        */

        this.attkDmg = attkDmg;
    }
    
    public void setSprite(WeaponSprite sprite) {
    	/*
    	 * 
    	*/
    	
    	this.sprite = sprite;
    }

//GETTERS--------------------------------------------------------------------------
    
    public AttackType getAttkType() {
    	/**
    	 * 
    	*/
    	
    	return attk.getType();
    }
    public double getBaseDmg() {
    	/**
    	 * Getter for field: baseDmg
    	*/
    	
    	return baseDmg;
    }

    public WeaponAttack getAttk1() {
        /**
         * Getter for field: attk
        */

        return attk1;
    }

    public WeaponAttack getAttk2() {
        /**
         * Getter for field: attk
        */

        return attk2;
    }

    public WeaponAttack getAttk() {
        /**
         * Getter for field: attk
        */

        return attk;
    }

    public ArenaCharacter getTarget() {
        /**
         * Getter for field: target
        */

        return target;
    }

    public ArenaCharacter getSelf() {
        /**
         * Getter for field: target
        */

        return self;
    }

    public double getAttkDmg() {
        /**
         * Setter for field: attkDmg
        */

        return attkDmg;
    }
    
    public WeaponSprite getSprite() {
    	/*
    	 * 
    	*/
    	
    	return sprite;
    }

//ATTACK---------------------------------------------------------------------------

    public void genAttack() {
        /**
         * This method will set field target to target and generate field dmg
         * based on the weapon's current attk.
         * ATTK MUST BE SET FIRST USING setattk()
         * TARGET MUST BE SET FIRST USING setTarget()
         *
        */

        String currattk;
        boolean isattk1;
        boolean isattk2;

        // Set currattk to the name of the current attk
        // (SET BEFORE with setattk)
        currattk = getAttk().getName();

        // Check which attk it is.
        isattk1  = currattk.equals(attk1.getName());
        isattk2  = currattk.equals(attk2.getName());

        // Perform the correct method depending on the attk.
        if (isattk1) {
            genAttk1();
        }
        else if (isattk2) {
            genAttk2();
        }
    }

    public void genAttk1() {
        /**
         * This method can be used to set the dmgMod of WeaponAttack attkX
         *                      ^(in subclasses)^
         * This method will always  generate a new damage value for field attkDmg
         * by calling: WeaponAttack attkX.genDmg()
        */

        // Set dmg to the generated damage of attk1
        setAttkDmg(getAttk1().genDmg());

    }

    public void genAttk2() {
        /**
         * This method can be used to set the dmgMod of WeaponAttack attkX
         *                     ^(in subclasses)^
         * This method will always  generate a new damage value for field attkDmg
         * by calling: WeaponAttack attkX.genDmg()
        */

        // Set dmg to the generated damage of attk2
        setAttkDmg(getAttk2().genDmg());

    }

//DISPLAY--------------------------------------------------------------------------

    public void dispAttk() {
        /**
         *
        */

        double dispDmg;

        dispDmg = getAttkDmg();

        if (getTarget().stat(StatType.HP).isWeak()) {
            dispDmg *= getTarget().stat(StatType.HP).getVal();
        }

        System.out.print(getName() + " " + getAttk().getName() + " ");
        System.out.printf("%.2f", dispDmg);
        System.out.println();

    }

}

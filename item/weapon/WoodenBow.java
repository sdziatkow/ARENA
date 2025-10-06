package item.weapon;

/**
 * Program Name:    WoodenBow.java
 *<p>
 * Purpose:         The purpose of this program is to create a bow Weapon that 
 * 					ArenaCharacters can use.
 *<p>
 * @version         0.0
 *<p>
 * Created:         March 06, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import arenaCharacter.CharAttr.Attribute;
import arenaCharacter.Stat.StatType;
import item.weapon.WeaponAttack.AttackType;

public class WoodenBow extends Weapon {
    /**
     * Default weapon for class Ranger
    */

    private WeaponAttack pierce = new WeaponAttack(
    		AttackType.PHYS, "PIERCE", 5.0, 12.0
    		)
    ;
    private WeaponAttack cripple = new WeaponAttack(
    		AttackType.PHYS, "CRIPPLE", 5.0, 10.0
    		)
    ;

    public WoodenBow() {
        /**
         *
        */

        setName("Wooden Bow");
        
        setBaseDmg(10.0);
        setAttk1(pierce);
        setAttk2(cripple);
    }

    public void genAttk1() {
        /**
         * Attk1 PIERCE
         * Will do 33% missing hp dmg plus half of a normal roll as dmg mod if
         * target is crippled.
         * OtherWise dmg mod is 0.
         * Crippled is only set to false if the target is hit while crippled and
         * above half hp.
        */
    	
    	getAttk1().setDmgMod(
    			getBaseDmg() +
    			( 0.02 * getSelf().stat(StatType.SP).getVal() ) +
    			( 0.02 * getSelf().stat(StatType.SPEED).getVal() ) +
    			( 0.5 * getSelf().attr(Attribute.DEXTERITY).getVal() )
    			)
    	;
    	
    	setAttkDmg(getAttk1().genDmg());
    }

    public void genAttk2() {
        /**
         * Attk2 CRIPPLE
         * Will always apply cripple.
         * If target has cripple applied, set dmgMod to a normal attk1 roll.
         * Otherwise dmgMod is 0
        */

    }

}

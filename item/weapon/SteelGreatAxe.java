package item.weapon;

/**
 * Program Name:    SteelGreatAxe.java
 *<p>
 * Purpose:         The purpose of this program is to create a large axe Weapon
 * 					for ArenaCharacters to use.
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

public class SteelGreatAxe extends Weapon {
    /**
     * Default weapon for class Goblin
    */

    private WeaponAttack roundSwing = new WeaponAttack(
    		AttackType.PHYS, "ROUND SWING", 15.0, 25.0
    		)
    ;
    private WeaponAttack fell     = new WeaponAttack(
    		AttackType.PHYS, "FELL", 7.0, 14.0
    		)
    ;

    public SteelGreatAxe() {
        /**
         *
        */

        setName("Steel Great Axe");
        
        setBaseDmg(3.0);
        setAttk1(roundSwing);
        setAttk2(fell);
    }

    public void genAttk1() {
        /**
         * Attk1 ROUND SWING
         *
         * dmgMod plus 3x normal roll
         * Will heal for 100% of dmg dealt plus 40% missing health
        */

    }

    public void genAttk2() {
        /**
         * Attk2 FELL
         * dmgMod is 10% of target's current hp
        */
    	
    	getAttk2().setDmgMod(
    			getBaseDmg() +
    			(0.02 * getSelf().stat(StatType.HP).getVal()) +
    			(0.5 * getSelf().attr(Attribute.VIGOR).getVal())
    			)
    	;
    	
    	setAttkDmg(getAttk2().genDmg());

    }
}

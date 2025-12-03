
package item.weapon;


/**
 * Program Name:    BronzeHandAxe.java
 *<p>
 * Purpose:         The purpose of this program is to create an axe Weapon for 
 * 					ArenaCharacters to use.
 *<p>
 * @version         0.0
 *<p>
 * Created:         March 06, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import arenaEnum.itemInfo.AttackType;

public class BronzeHandAxe extends Weapon {
    /**
     * Default weapon for class Barbarian
    */

    private WeaponAttack ravage = new WeaponAttack(
    		AttackType.PHYS, "RAVAGE", 6.0, 15.0
    		)
    ;
    private WeaponAttack unWilling = new WeaponAttack(
    		AttackType.PHYS, "UN-WILLING", 10.0, 12.0
    		)
    ;

    public BronzeHandAxe() {
        /**
         *
        */

        setName("Bronze Hand Axe");
        setBaseDmg(10.0);

        setAttk1(ravage);
        setAttk2(unWilling);
        
        setInfo();
    }

    public void genAttk1() {
        /**
         * Attk1 RAVAGE
         * 
        */
    	
    	

    }

    public void genAttk2() {
        /**
         * Attk2 UN-WILLING
         * dmgMod will do 40% of self's current missing HP
        */

    }
}

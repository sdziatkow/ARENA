package item.weapon;

/**
 * Program Name:    CrystalSceptre.java
 *<p>
 * Purpose:         The purpose of this program is to create a scepter Weapon for
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

import arenaCharacter.CharAttr.Attribute;
import arenaCharacter.Stat.StatType;
import item.weapon.WeaponAttack.AttackType;

public class CrystalSceptre extends Weapon {
    /**
     * Default class for class Mage
    */
	
	private double MP_COST = 15;

    private WeaponAttack crystalBlast = new WeaponAttack(
    		AttackType.MAG, "CRYSTAL-BLAST", 6.0, 13.0
    		)
    ;
    private WeaponAttack reflect = new WeaponAttack(
    		AttackType.DEF, "REFLECT", 5.0, 10.0
    		)
    ;

    public CrystalSceptre() {
        /**
         *
        */

        setName("Crystal Sceptre");
        setBaseDmg(0.0);

        setAttk1(crystalBlast);
        setAttk2(reflect);
    }

    public void genAttk1() {
        /**
         * Attk1 CRYSTAL-BLAST
         * dmgMod will do 2% current hp dmg plus 3x lastHeal val
         *
        */
    	
    	getAttk1().setDmgMod(
    			getBaseDmg() +
    			(0.02 * getSelf().stat(StatType.MP).getVal()) +
    			(0.04 * getSelf().stat(StatType.MAGDEF).getVal()) +
    			(0.68 * getSelf().attr(Attribute.INTELLIGENCE).getVal())
    			)
    	;
    	
    	setAttkDmg(getAttk1().genDmg());
    	
    	getSelf().stat(StatType.MP).dmg(MP_COST);
    }

    public void genAttk2() {
        /**
         * Attk2 REFLECT
         * dmgMod will do 4% missing HP plus 80% last dmg taken plus 5% target
         * maxHp dmg
         * Finally, heal self for 10% of dmg done.
        */
    }

}
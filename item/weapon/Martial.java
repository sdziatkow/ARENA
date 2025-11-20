package item.weapon;

/**
 * Program Name:    Martial.java
 *<p>
 * Purpose:         The purpose of this program is to create a hand-to-hand 
 * 					Weapon that ArenaCharacters can use.
 *<p>
 * @version         0.0
 *<p>
 * Created:         March 06, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import arenaCharacter.ArenaCharacter.CharClass;
import arenaCharacter.CharAttr.Attribute;
import item.weapon.WeaponAttack.AttackType;

public class Martial extends Weapon {
    /**
     * Default weapon for all Arena Characters.
     *
     * ArenaCharacters of class Monk get special bonuses to their martial dmg
    */

    private WeaponAttack punch = new WeaponAttack(
    		AttackType.MIX, "PUNCH", 5.0, 10.0
    		)
    ;
    private WeaponAttack kick  = new WeaponAttack(
    		AttackType.MIX, "KICK", 6.0, 11.0
    		)
    ;
    
    private boolean combo;

    public Martial() {
        /**
         *
        */

        setName("Martial");

        setBaseDmg(10.0);
        setAttk1(punch);
        setAttk2(kick);
        
        setInfo();
    }

    public void genAttk1() {
        /**
         * Attk1 PUNCH
         * dmgMod for class Monk only; see below.
        */
    	
    	if (getSelf().getCharClass().equals(CharClass.MONK)) {
    		getAttk1().setDmgMod(
    				(2 * getSelf().attr(Attribute.WILLPOWER).getVal()) +
    				(0.5 * getSelf().attr(Attribute.INTELLIGENCE).getVal())
    				)
    		;
    	}
    	
    	setAttkDmg(getAttk1().genDmg());

    }

    public void genAttk2() {
        /**
         * Attk2 KICK
         * dmgMod for class Monk only; see below.
        */

    }
}
